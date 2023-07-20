package backend;

import IRBuilder.*;
import Type.Type;
import backend.machineCode.*;
import backend.reg.PhysicsReg;
import instruction.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static IRBuilder.IRConstants.*;
import static Type.FloatType.IRFloatType;
import static Type.Int1Type.IRInt1Type;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;
import static backend.machineCode.MachineConstants.*;
import static instruction.BrInstruction.SINGLE;

public class codeGen {
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private static final Type voidType = IRVoidType();
    private static final PhysicsReg spReg = new PhysicsReg("sp");
    private static final PhysicsReg s0Reg = new PhysicsReg("s0");
    private static final PhysicsReg raReg = new PhysicsReg("ra");
    private static IRModule module;
    private List<MachineBlock> blocks = new ArrayList<>();
    private HashMap<FunctionBlock, MachineFunction> funcMap;
    private static HashMap<BaseBlock, MachineBlock> blockMap;
    private HashMap<String, MachineOperand> operandMap;
    private final int tmpImmCount = 0;
    
    
    public static void serializeBlocks(List<MachineBlock> blocks) {
        List<MachineBlock> sequence = new ArrayList<>();
        Set<MachineBlock> done = new HashSet<>(); // 已经序列化的基本块
        
        // 比较器，用于对基本块进行排序
        Comparator<MachineBlock> blockComparator = Comparator.comparingInt(block -> block.getPredList().size());
        
        // 递归函数，用于序列化每个基本块及其后继块
        for (MachineBlock block : blocks) {
            serializeBlock(block, sequence, done, blockComparator);
        }
        
    }
    
    private static void serializeBlock(MachineBlock block, List<MachineBlock> sequence, Set<MachineBlock> done,
                                       Comparator<MachineBlock> blockComparator) {
        if (!done.contains(block)) {
            done.add(block);
            
            // 序列化 falseSucc 和 trueSucc
            MachineBlock falseSucc = block.getFalseSucc();
            MachineBlock trueSucc = block.getTrueSucc();
            
            if (falseSucc != null) {
                serializeBlock(falseSucc, sequence, done, blockComparator);
            }
            
            if (trueSucc != null) {
                serializeBlock(trueSucc, sequence, done, blockComparator);
            }
            
            // 将当前基本块添加到序列中
            sequence.add(block);
            
            // 对后继块进行排序并添加到序列中
            List<MachineBlock> succBlocks = new ArrayList<>();
            if (falseSucc != null) {
                succBlocks.add(falseSucc);
            }
            if (trueSucc != null) {
                succBlocks.add(trueSucc);
            }
            Collections.sort(succBlocks, blockComparator);
            for (MachineBlock succBlock : succBlocks) {
                if (!done.contains(succBlock)) {
                    serializeBlock(succBlock, sequence, done, blockComparator);
                }
            }
        }
    }
    
    public void MachineCodeGen(IRModule irModule) {
        module = irModule;
        funcMap = new HashMap<>();
        blockMap = new HashMap<>();
        operandMap = new HashMap<>();
        List<FunctionBlock> functionBlocks = module.getFunctionBlocks();
        for (FunctionBlock functionBlock : functionBlocks) {
            MachineFunction machineFunction = new MachineFunction(functionBlock.getFunctionName());
            funcMap.put(functionBlock, machineFunction);
        }
        
        for (FunctionBlock func: functionBlocks) {
            varAnalyse(funcMap.get(func), func);
            List<BaseBlock> funcBlocks = func.getBaseBlocks();
            for (BaseBlock block: funcBlocks) {
                MachineBlock machineBlock = new MachineBlock(block.getLabel(), funcMap.get(func));
                blockMap.put(block, machineBlock);
                blocks.add(machineBlock);
                parseBlock(block, machineBlock);
            }
            // TODO: block succ
            serializeBlocks(blocks);
        }
    }

    public void varAnalyse(MachineFunction mfunc, FunctionBlock func) {
        // stack analyse
        Map<String, Integer> offestMap = mfunc.getOffsetMap();
        int stackCount = 0; // 4 byte = 1 count
        stackCount += 4; // ra 8 + s0 8  = 16 byte = 4 count
        if (!func.getRetType().equals(IRVoidType())) {
            stackCount += 1; // for ret value
        }
        offestMap.put("ra", 8);
        offestMap.put("s0", 16);
        List<ValueRef> params = func.getParams();
        for (ValueRef param: params) {
            if (param.getType().equals(IRInt32Type()) || param.getType().equals(IRFloatType())) {
                stackCount ++;
                offestMap.put(param.getText(), stackCount * 4);
            } else {
                stackCount += 2;
                offestMap.put(param.getText(), stackCount * 8);
            }
        }
        int frameSize = stackCount * 4;
        if (frameSize % 16 != 0) {
            frameSize = ((frameSize / 16) + 1) * 16;
            mfunc.setFrameSize(frameSize);
        }
        mfunc.getPreList().add(new MCBinaryInteger(spReg, spReg, new MachineOperand(-frameSize), ADDI));;
        mfunc.getPreList().add(new MCStore(spReg, raReg, new MachineOperand(frameSize - 8), SD));
        mfunc.getPreList().add(new MCStore(spReg, s0Reg, new MachineOperand(frameSize - 16), SD));
        mfunc.getPreList().add(new MCBinaryInteger(s0Reg, spReg, new MachineOperand(frameSize), ADDI));
    }
    
    public void parseBlock(BaseBlock block, MachineBlock machineBlock) {
        List<Instruction> instructions = block.getInstructions();
        for (Instruction instr: instructions) {
            if(instr instanceof  AllocaInstruction){
                parseAllocaInstr((AllocaInstruction) instr, machineBlock);
            }else if(instr instanceof BrInstruction){
                parseBrInstr((BrInstruction) instr, machineBlock);
            }else if (instr instanceof CalculateInstruction) {
                parseCalculateInstr((CalculateInstruction) instr, machineBlock);
            } else if (instr instanceof CallInstruction) {
                parseCallInstr((CallInstruction) instr, machineBlock);
            } else if (instr instanceof CondInstruction){
                parseCondInstr((CondInstruction)instr, machineBlock);
            } else if (instr instanceof GetElemPtrInstruction){
                parseGetElemPtrInstr((GetElemPtrInstruction) instr, machineBlock);
            } else if (instr instanceof LoadInstruction){
                parseLoadInstr((LoadInstruction) instr, machineBlock);
            } else if (instr instanceof PhiInstruction){
                parsePhiInstr((PhiInstruction) instr, machineBlock);
            } else if (instr instanceof RetInstruction) {
                parseReturnInstr((RetInstruction) instr, machineBlock);
            } else if (instr instanceof StoreInstruction){
                parseStoreInstr((StoreInstruction) instr, machineBlock);
            } else if (instr instanceof TypeTransInstruction){
                parseTypeTransInstr((TypeTransInstruction) instr, machineBlock);
            } else if (instr instanceof ZextInstruction){
                parseZextInstr((ZextInstruction) instr, machineBlock);
            } else {
                assert(false);
            }
        }
    }

    public void parseAllocaInstr(AllocaInstruction instr, MachineBlock block){

    }

    public void parseBrInstr(BrInstruction instr, MachineBlock block){
        if(instr.getType() == SINGLE){
            BaseBlock brBlock = (BaseBlock) instr.getOperands().get(0);
            MachineBlock machineBlock = blockMap.get(brBlock);
            MCJump jump = new MCJump(machineBlock.getBlockName());
            block.getMachineCodes().add(jump);
        }else{


        }
    }
    
    public void parseCalculateInstr(CalculateInstruction instr, MachineBlock block) {
        MachineOperand dest = parseOperand(instr.getOperands().get(0));
        MachineOperand left = parseOperand(instr.getOperands().get(1));
        MachineOperand right = parseOperand(instr.getOperands().get(2));

        if(left.isImm() && right.isImm()){
            int result = 0;
            switch (instr.getType()){
                case IRConstants.ADD:
                    result = left.getImmValue() + right.getImmValue();
                    break;
                case IRConstants.SUB:
                    result = left.getImmValue() - right.getImmValue();
                    break;
                case IRConstants.MUL:
                    result = left.getImmValue() * right.getImmValue();
                    break;
                case IRConstants.SDIV:
                    result = left.getImmValue() / right.getImmValue();
                    break;
                default:
                    assert(false);
                    break;
            }
            MachineOperand src = new MachineOperand(result);
            MCMove move = new MCMove(src, dest);

            setDefUse(src, move);
            setDefUse(dest, move);
            block.getMachineCodes().add(move);

        }else if(left.isImm() || right.isImm()){
            MachineOperand src = left.isImm() ? right : left;
            MachineOperand imm = left.isImm() ? left : right;
            MCBinaryInteger code = null;
            switch (instr.getType()){
                case IRConstants.ADD:
                    code = new MCBinaryInteger(dest, src, imm, ADDIW);
                    break;
                case IRConstants.SUB:
                    code = new MCBinaryInteger(dest, src, imm, SUBIW);
                    break;
                case IRConstants.MUL:
                    // add register to store the imm(mul operand can only be register)
                    BaseRegister mulReg = new BaseRegister("li", int32Type);
                    MachineOperand mulRegOp = parseOperand(mulReg);
                    MCLi mulLi = new MCLi(mulRegOp, imm);
                    block.getMachineCodes().add(mulLi);
                    mulRegOp.setDef(mulLi);
                    imm.addUse(mulLi);
                    code = new MCBinaryInteger(dest, src, mulRegOp, MULW);
                    break;
                case IRConstants.SDIV:
                    BaseRegister divReg = new BaseRegister("li", int32Type);
                    MachineOperand divRegOp = parseOperand(divReg);
                    MCLi divLi = new MCLi(divRegOp, imm);
                    block.getMachineCodes().add(divLi);
                    divRegOp.setDef(divLi);
                    imm.addUse(divLi);
                    code = new MCBinaryInteger(dest, src, imm, DIVW);
                    break;
                default:
                    break;
            }
            assert(code != null);
            setDefUse(dest, code);
            setDefUse(src, code);
            setDefUse(imm, code);

            block.getMachineCodes().add(code);
        }else{
            MachineCode code = null;
            switch (instr.getType()){
                case IRConstants.ADD:
                    code = new MCBinaryInteger(dest, left, right, ADDW);
                    break;
                case IRConstants.SUB:
                    code = new MCBinaryInteger(dest, left, right, SUBW);
                    break;
                case IRConstants.MUL:
                    code = new MCBinaryInteger(dest, left, right, MULW);
                    break;
                case IRConstants.SDIV:
                    code = new MCBinaryInteger(dest, left, right, DIVW);
                    break;
                default:
                    break;
            }
            assert(code != null);
            setDefUse(dest, code);
            setDefUse(left, code);
            setDefUse(right, code);
            block.getMachineCodes().add(code);
        }
    }
    
    public void parseCallInstr(CallInstruction instr, MachineBlock block) {
        BaseRegister dest = (BaseRegister) instr.getOperands().get(0);
        List<ValueRef> params = instr.getParams();
        List<MachineOperand> operands = new ArrayList<>();
        for (ValueRef param: params) {
            BaseRegister vReg = new BaseRegister(param.getText(), param.getType());
            BaseRegister src = new BaseRegister("li", param.getType());
            MCLoad load = new MCLoad(src, vReg, new MachineOperand(0));
            setDefUse(vReg, load);
            block.getMachineCodes().add(load);
            operands.add(vReg);
        }
        MCCall call = new MCCall(funcMap.get(instr.getFunction()), operands);
        setDefUse(dest, call);
        block.getMachineCodes().add(call);
    }

    public void parseCondInstr(CondInstruction instr, MachineBlock block){

    }

    public void parseGetElemPtrInstr(GetElemPtrInstruction instr, MachineBlock block) {

    }

    public void parseLoadInstr(LoadInstruction instr, MachineBlock block) {
        MachineOperand dest = parseOperand(instr.getOperands().get(0));
        MachineOperand src = parseOperand(instr.getOperands().get(1));
        // TODO: calculate offset (temp 0)
        MCLoad load = new MCLoad(dest, src, new MachineOperand(0));
        block.getMachineCodes().add(load);
        setDefUse(dest, load);
        setDefUse(src, load);
    }

    public void parsePhiInstr(PhiInstruction instr, MachineBlock block){

    }

    public void parseReturnInstr(RetInstruction instr, MachineBlock block) {
        List<ValueRef> rets = instr.getOperands();
        if (rets.get(0) != null) {
            MachineOperand src = parseOperand(rets.get(0));
            MCMove move = new MCMove(src, new PhysicsReg("a0"));
            block.getMachineCodes().add(move);
        }
        MCReturn ret = new MCReturn();
        block.getMachineCodes().add(ret);
    }

    public void parseStoreInstr(StoreInstruction instr, MachineBlock block) {
        MachineOperand src = parseOperand(instr.getOperands().get(1));
        MachineOperand dest = parseOperand(instr.getOperands().get(2));
        //todo: calculate offset
        //MachineOperand offest = new MachineOperand(0);
        MCStore store = new MCStore(src, dest, SW);
        block.getMachineCodes().add(store);
        setDefUse(src, store);
        setDefUse(dest, store);
    }

    public void parseTypeTransInstr(TypeTransInstruction instr, MachineBlock block){

    }

    public void parseZextInstr(ZextInstruction instr, MachineBlock block){

    }


    public MachineOperand parseOperand(ValueRef operand) {
        if (!operandMap.containsKey(operand.getText())) {
            if (operand instanceof ConstIntValueRef) {
                int integer = Integer.parseInt(operand.getText());
                MachineOperand intOp = new MachineOperand(integer);
                intOp.setIdentity(operand.getText());
                operandMap.put(operand.getText(), intOp);
                return intOp;
            } else if (operand instanceof BaseRegister) {
                operandMap.put(operand.getText(), (MachineOperand) operand);
                MachineOperand machineOperand = (MachineOperand) operand;
                machineOperand.setIdentity(operand.getText());
                return machineOperand;
            }
            // todo: globalRegister
        } else {
            MachineOperand op = operandMap.get(operand.getText());
            return op;
        }
        return null;
    }

    public void setDefUse(MachineOperand operand, MachineCode code){
        if(operand.getIsDef()){
            operand.addUse(code);
        }else{
            operand.setDef(code);
        }
    }

    public void PrintCodeToFile(String dest) {
        StringBuilder builder = new StringBuilder();
        for(FunctionBlock function: module.getFunctionBlocks()){
            builder.append(function.getFunctionName()).append(":").append("\n");
            for (MachineCode code: funcMap.get(function).getPreList()) {
                builder.append("    ");
                builder.append(code.toString());
                builder.append("\n");
            }
            for(BaseBlock block: function.getBaseBlocks()){
                MachineBlock machineBlock = blockMap.get(block);
                builder.append(machineBlock.getBlockName()).append(":\n");
                for(MachineCode code: machineBlock.getMachineCodes()){
                    builder.append("    ");
                    builder.append(code.toString());
                    builder.append("\n");
                }
            }
        }
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(dest));
            out.write(builder.toString());
            out.close();
        }catch (IOException e){
            System.err.println("failed to print machine code.");
        }
    }
}
