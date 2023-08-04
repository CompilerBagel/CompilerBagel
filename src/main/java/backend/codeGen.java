package backend;

import IRBuilder.*;
import Type.Type;
import backend.machineCode.*;
import backend.machineCode.Instruction.*;
import backend.machineCode.Label;
import backend.reg.PhysicsReg;
import backend.reg.Reg;
import instruction.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

import Type.PointerType;
import Type.ArrayType;
import utils.FloatTools;

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
    private static final PhysicsReg spReg = PhysicsReg.getSpReg();
    private static final PhysicsReg s0Reg = PhysicsReg.getS0Reg();
    private static final PhysicsReg raReg = PhysicsReg.getRaReg();
    private static final PhysicsReg a0Reg = PhysicsReg.getA0Reg();

    private static IRModule module;
    private List<MachineBlock> blocks = new ArrayList<>();
    private HashMap<FunctionBlock, MachineFunction> funcMap;
    private static HashMap<BaseBlock, MachineBlock> blockMap;
    private HashMap<String, MachineOperand> operandMap;
    private StringBuilder globalSb;
    private Map<String, Symbol> globalMap;

    private final HashMap<ValueRef, Integer> paramOrder = new HashMap<ValueRef, Integer>();

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
        globalMap = module.getGlobalSymbol();
        declareGlobal(globalMap);
        List<FunctionBlock> functionBlocks = module.getFunctionBlocks();
        for (FunctionBlock functionBlock : functionBlocks) {
            MachineFunction machineFunction = new MachineFunction(functionBlock.getFunctionName());
            funcMap.put(functionBlock, machineFunction);
        }

        for (FunctionBlock func : functionBlocks) {
            varAnalyse(funcMap.get(func), func);
            List<BaseBlock> funcBlocks = func.getBaseBlocks();
            for (BaseBlock block : funcBlocks) {
                MachineBlock machineBlock = new MachineBlock(block.getLabel(), funcMap.get(func));
                blockMap.put(block, machineBlock);
                MachineBlock machineBlockTmp = blockMap.get(block);
                assert (machineBlockTmp != null);
                blocks.add(machineBlock);
                parseBlock(block, machineBlock);
                funcMap.get(func).addMachineBlock(machineBlock);
            }
            // TODO: block succ
            serializeBlocks(blocks);
        }
    }

    /**
     * declare global variable
     *
     * @param map
     */
    public void declareGlobal(Map<String, Symbol> map) {
        globalSb = new StringBuilder();
        for (Map.Entry<String, Symbol> entry : map.entrySet()) {
            globalSb.append("    ").append(entry.getKey()).append(":").append("\n");
            List<Float> values = entry.getValue().getInitValue();
            boolean isInt = false;
            if (entry.getValue().getType().equals(IRInt32Type())) {
                isInt = true;
            } else if (entry.getValue().getType() instanceof ArrayType) {
                Type tmp = entry.getValue().getType();
                while (tmp instanceof ArrayType) {
                    tmp = ((ArrayType) tmp).getElementType();
                }
                if (tmp.equals(IRInt32Type()))
                    isInt = true;
            }
            for (Float value : values) {
                if (isInt)
                    globalSb.append("        " + ".word ").append(value.intValue()).append("\n");
                else
                    globalSb.append("        " + ".word ").append(value).append("\n");
            }
        }
    }

    public void varAnalyse(MachineFunction mfunc, FunctionBlock func) {
        // stack analyse
        Map<String, Integer> offestMap = mfunc.getOffsetMap();
        int stackCount = mfunc.getStackCount(); // 4 byte = 1 count
        stackCount += 4; // ra 8 + s0 8  = 16 byte = 4 count
        offestMap.put("ra", 8);
        offestMap.put("s0", 16);
        List<ValueRef> params = func.getParams();
        int i = 0;
        for (ValueRef param : params) {
//            if (param.getType().equals(IRInt32Type()) || param.getType().equals(IRFloatType())) {
//                stackCount ++;
//                offestMap.put(param.getText(), stackCount * 4);
//            } else {
//                stackCount += 2;
//                offestMap.put(param.getText(), stackCount * 8);
//            }
            paramOrder.put(param, i);
            i++;
        }
        if (!func.getType().equals(IRVoidType())) {
            stackCount += 1;
            offestMap.put("ret", stackCount * 4);
        }
        mfunc.setStackCount(stackCount);
        mfunc.setFrameSize(stackAlign(stackCount));
    }

    public void parseBlock(BaseBlock block, MachineBlock machineBlock) {
        List<Instruction> instructions = block.getInstructions();
        for (Instruction instr : instructions) {
            if (instr instanceof AllocaInstruction) {
                parseAllocaInstr((AllocaInstruction) instr, machineBlock);
            } else if (instr instanceof BrInstruction) {
                parseBrInstr((BrInstruction) instr, machineBlock);
            } else if (instr instanceof CalculateInstruction) {
                parseCalculateInstr((CalculateInstruction) instr, machineBlock);
            } else if (instr instanceof CallInstruction) {
                parseCallInstr((CallInstruction) instr, machineBlock);
            } else if (instr instanceof CondInstruction) {
                parseCondInstr((CondInstruction) instr, machineBlock);
            } else if (instr instanceof GetElemPtrInstruction) {
                parseGetElemPtrInstr((GetElemPtrInstruction) instr, machineBlock);
            } else if (instr instanceof LoadInstruction) {
                parseLoadInstr((LoadInstruction) instr, machineBlock);
            } else if (instr instanceof PhiInstruction) {
                parsePhiInstr((PhiInstruction) instr, machineBlock);
            } else if (instr instanceof RetInstruction) {
                parseReturnInstr((RetInstruction) instr, machineBlock);
            } else if (instr instanceof StoreInstruction) {
                parseStoreInstr((StoreInstruction) instr, machineBlock);
            } else if (instr instanceof TypeTransInstruction) {
                parseTypeTransInstr((TypeTransInstruction) instr, machineBlock);
            } else if (instr instanceof ZextInstruction) {
                parseZextInstr((ZextInstruction) instr, machineBlock);
            } else {
                assert (false);
            }
        }

    }

    public void parseAllocaInstr(AllocaInstruction instr, MachineBlock block) {
        MachineFunction mfunc = block.getBlockFunc();
        int stackCount = mfunc.getStackCount();
        Map<String, Integer> offsetMap = mfunc.getOffsetMap();
        ValueRef resRegister = instr.getOperands().get(0);
        String resName = resRegister.getText();
        Type resType = ((PointerType) resRegister.getType()).getBaseType();
        if (resType.equals(IRInt32Type()) || resType.equals(IRFloatType())) {
            stackCount++;
            offsetMap.put(resName, stackCount * 4);
        } else {
            offsetMap.put(resName, stackCount * 4 + 4);
            int arrayLen = (((ArrayType) (instr.getPointedType())).getLength());
            stackCount += arrayLen;
        }
        mfunc.setStackCount(stackCount);
        mfunc.setFrameSize(stackAlign(stackCount));
    }

    private int stackAlign(int stackCount) {
        int realSize = stackCount * 4;
        if (realSize % 16 != 0) {
            return ((realSize / 16) + 1) * 16;
        }
        return realSize;
    }

    public void parseBrInstr(BrInstruction instr, MachineBlock block) {
        if (instr.getType() == SINGLE) {
            BaseBlock brBlock = (BaseBlock) instr.getOperands().get(0);
            MCJump jump = new MCJump(brBlock.getLabel());
            block.getMachineCodes().add(jump);
        } else {
            ValueRef cond = instr.getOperands().get(0);
            BaseBlock trueBlock = (BaseBlock) instr.getOperands().get(1);
            BaseBlock falseBlock = (BaseBlock) instr.getOperands().get(2);
            MachineOperand condReg = parseOperand(cond);
            if (condReg.isImm()) {
                condReg = addLiOperation(condReg, block);
            }
            MachineCode br = new MCBranch(1, condReg, new PhysicsReg(0), trueBlock.getLabel());
            MachineCode jump = new MCJump(falseBlock.getLabel());
            setDefUse(condReg, br);
            block.getMachineCodes().add(br);
            block.getMachineCodes().add(jump);
        }
    }

    public void parseCalculateInstr(CalculateInstruction instr, MachineBlock block) {
        MachineOperand dest = parseOperand(instr.getOperands().get(0));
        MachineOperand left = parseOperand(instr.getOperands().get(1));
        MachineOperand right = parseOperand(instr.getOperands().get(2));

        if (left.isImm()) {
            left = addLiOperation(left, block);
        }
        if (right.isImm()) {
            right = addLiOperation(right, block);
        }

        String machineOp;
        Type varType = ((BaseRegister) left).getType();
        if (varType == int32Type) {
            machineOp = intOperatorMap.get(instr.getType());
        } else {
            machineOp = floatOperatorMap.get(instr.getType());
        }

        if (machineOp != null) {
            MachineCode code = new MCBinaryInteger(dest, left, right, machineOp);
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

        // push a0, a1, a2, ...
        int paramCnt = params.size();
        MachineFunction mcFunc = block.getBlockFunc();
        int stackCount = mcFunc.getStackCount();
        Map<String, Integer> offsetMap = mcFunc.getOffsetMap();
        for (int i = 1; i < Integer.max(paramCnt, 4); i++) {
            stackCount += 2;
            int offset = stackCount * 4;
            offsetMap.put("phyReg_a" + i, offset);
            MCStore store = new MCStore(PhysicsReg.getPhysicsReg(10 + i), s0Reg, new Immeidiate(-offset), SD);
            block.getMachineCodes().add(store);
        }

        mcFunc.setStackCount(stackCount);
        mcFunc.setFrameSize(stackAlign(stackCount));
        int i = 0;
        for (ValueRef param : params) {
            MachineOperand op = parseOperand(param);
            if (op.isImm()) {
                BaseRegister tmp = new BaseRegister("li", param.getType());
                tmp.setPhysicsReg(PhysicsReg.getPhysicsReg(10 + i));
                MCLi li = new MCLi(tmp, op);
                setDefUse(tmp, li);
                block.getMachineCodes().add(li);
                operands.add(tmp);
            } else {
                // BaseRegister vReg = new BaseRegister(param.getText(), param.getType());
                BaseRegister src = new BaseRegister(param.getText(), param.getType());
                src.setPhysicsReg(PhysicsReg.getPhysicsReg(10 + i));
                MCMove mv = new MCMove(op, src);
                setDefUse(src, mv);
                setDefUse(op, mv);
                // setDefUse(vReg, mv);
                block.getMachineCodes().add(mv);
                // operands.add(vReg);
                operands.add(src);
            }
            i++;
        }
        MachineFunction mcFunction = funcMap.get(instr.getFunction());
        MCCall call;
        if (mcFunction != null) {
            call = new MCCall(funcMap.get(instr.getFunction()), operands);
        } else {
            MachineFunction outFunc = new MachineFunction(instr.getFunction().getFunctionName());
            call = new MCCall(outFunc, operands);
        }
        for (MachineOperand operand : operands) {
            operand.addUse(call);
        }
        BaseRegister ret = new BaseRegister("ret", dest.getType());
        MCMove mv = new MCMove(ret, dest);
        ret.setPhysicsReg(a0Reg);
        setDefUse(ret, mv);
        setDefUse(dest, mv);
        setDefUse(dest, call);

        block.getMachineCodes().add(call);

        for (i = 1; i < Integer.max(paramCnt, 4); i++) {
            int offset = offsetMap.get("phyReg_a" + i);
            MCLoad load = new MCLoad(s0Reg, PhysicsReg.getPhysicsReg(10 + i), new Immeidiate(-offset), LD);
            block.getMachineCodes().add(load);
        }

        block.getMachineCodes().add(mv);
    }

    public void parseCondInstr(CondInstruction instr, MachineBlock block) {
        MachineOperand dest = parseOperand(instr.getOperands().get(0));
        MachineOperand left = parseOperand(instr.getOperands().get(1));
        MachineOperand right = parseOperand(instr.getOperands().get(2));

        if (left.isImm()) {
            left = addLiOperation(left, block);
        }
        if (right.isImm()) {
            right = addLiOperation(right, block);
        }

        switch (instr.getIcmpType()) {
            case IRConstants.IRIntSLT, IRConstants.IRIntULT -> {
                MCSet set = new MCSet(dest, left, right);
                block.getMachineCodes().add(set);
                setDefUse(dest, set);
                setDefUse(left, set);
                setDefUse(right, set);
            }
            case IRConstants.IRIntSGT, IRConstants.IRIntUGT -> {
                MCSet set = new MCSet(dest, right, left);
                block.getMachineCodes().add(set);
                setDefUse(dest, set);
                setDefUse(left, set);
                setDefUse(right, set);
            }
            case IRConstants.IRIntEQ -> {
                MachineOperand res = new BaseRegister("tmp", int32Type);
                MCBinaryInteger sub = new MCBinaryInteger(res, left, right, SUBW);
                MCSetz setz = new MCSetz(dest, res, IRConstants.IRIntEQ);
                block.getMachineCodes().add(sub);
                block.getMachineCodes().add(setz);
                setDefUse(dest, setz);
                setDefUse(res, setz);
                setDefUse(left, sub);
                setDefUse(right, sub);
                setDefUse(res, sub);
            }
            case IRConstants.IRIntNE -> {
                MachineOperand res = new BaseRegister("tmp", int32Type);
                MCBinaryInteger sub = new MCBinaryInteger(res, left, right, SUBW);
                MCSetz setz = new MCSetz(dest, res, IRConstants.IRIntNE);
                block.getMachineCodes().add(sub);
                block.getMachineCodes().add(setz);
                setDefUse(dest, setz);
                setDefUse(res, setz);
                setDefUse(left, sub);
                setDefUse(right, sub);
                setDefUse(res, sub);
            }
            case IRConstants.IRIntSGE, IRConstants.IRIntUGE -> {
                MachineOperand res = new BaseRegister("tmp", int32Type);
                MCSet set = new MCSet(res, left, right);
                MCBinaryInteger xor = new MCBinaryInteger(dest, res, new Immeidiate(1), XORI);
                block.getMachineCodes().add(set);
                block.getMachineCodes().add(xor);
                setDefUse(dest, xor);
                setDefUse(res, xor);
                setDefUse(left, set);
                setDefUse(right, set);
                setDefUse(res, set);
            }
            case IRConstants.IRIntSLE, IRConstants.IRIntULE -> {
                MachineOperand res = new BaseRegister("tmp", int32Type);
                MCSet set = new MCSet(res, right, left);
                MCBinaryInteger xor = new MCBinaryInteger(dest, res, new Immeidiate(1), XORI);
                block.getMachineCodes().add(set);
                block.getMachineCodes().add(xor);
                setDefUse(dest, xor);
                setDefUse(res, xor);
                setDefUse(left, set);
                setDefUse(right, set);
                setDefUse(res, set);
            }
        }
    }

    public void parseGetElemPtrInstr(GetElemPtrInstruction instr, MachineBlock block) {
        ValueRef pointer = instr.getPointer();
        Map<String, Integer> offsetMap = block.getBlockFunc().getOffsetMap();

        if (pointer.getType() instanceof PointerType) {
            if (pointer instanceof GlobalRegister) {
                Symbol globalSymbol = globalMap.get(((GlobalRegister) pointer).getIdentity());
                BaseRegister vReg = new BaseRegister(pointer.getText(), pointer.getType());
                MachineOperand label = new Label(globalSymbol.getName(), globalSymbol);
                MCLoad la = new MCLoad(label, vReg, LA);
                operandMap.put(pointer.getText(), vReg);
                block.getMachineCodes().add(la);
                setDefUse(vReg, la);
                setDefUse(label, la);
            }

            if (null != offsetMap.get(pointer.getText())) {
                int base = offsetMap.get(pointer.getText());
                ValueRef indexReg = instr.getOperands().get(3);
                MachineOperand baseReg = new BaseRegister(pointer.getText(), int32Type);
                if (indexReg instanceof ConstIntValueRef) {
                    int offset;
                    int index = ((ConstIntValueRef) (instr.getOperands().get(3))).getValue();
                    Type baseType = ((PointerType) instr.getOperands().get(0).getType()).getBaseType();
                    if (baseType instanceof ArrayType) {
                        int dims = 0;
                        Type tmp = baseType;
                        while (tmp instanceof ArrayType) {
                            tmp = ((ArrayType) tmp).getElementType();
                            dims++;
                        }
                        offset = ((ArrayType) baseType).getOtherDimensionLength(dims, index) * 4;
                    } else {
                        offset = index * 4;
                    }

                    MCBinaryInteger add = new MCBinaryInteger(baseReg, s0Reg, new Immeidiate(-(offset + base)), ADDI);
                    offsetMap.put(instr.getOperands().get(0).getText(), offset + base);
                    block.getMachineCodes().add(add);
                    setDefUse(baseReg, add);
                } else if (indexReg instanceof BaseRegister) {
                    MachineOperand indexOp = parseOperand(indexReg);
                    BaseRegister offset = new BaseRegister("offset", int32Type);
                    MachineOperand tmp4 = addLiOperation(new Immeidiate(4), block);
                    MCBinaryInteger mul = new MCBinaryInteger(offset, indexOp, tmp4, MULW);
                    block.getMachineCodes().add(mul);
                    setDefUse(offset, mul);
                    setDefUse(indexOp, mul);

                    MCBinaryInteger addOffset = new MCBinaryInteger(offset, offset, new Immeidiate(base), ADDI);
                    block.getMachineCodes().add(addOffset);
                    setDefUse(offset, addOffset);

                    MCBinaryInteger sub = new MCBinaryInteger(baseReg, s0Reg, offset, SUB);
                    operandMap.put(instr.getOperands().get(0).getText(), baseReg);
                    block.getMachineCodes().add(sub);
                    setDefUse(baseReg, sub);
                    setDefUse(offset, sub);
                }
            } else {
                MachineOperand base = operandMap.get(pointer.getText());
                ValueRef indexReg = instr.getOperands().get(3);
                MachineOperand baseReg = new BaseRegister(pointer.getText(), pointer.getType());
                if (indexReg instanceof ConstIntValueRef) {
                    int offset;
                    int index = ((ConstIntValueRef) (instr.getOperands().get(3))).getValue();
                    Type baseType = ((PointerType) instr.getOperands().get(0).getType()).getBaseType();
                    if (baseType instanceof ArrayType) {
                        int dims = 0;
                        Type tmp = baseType;
                        while (tmp instanceof ArrayType) {
                            tmp = ((ArrayType) tmp).getElementType();
                            dims++;
                        }
                        offset = ((ArrayType) baseType).getOtherDimensionLength(dims, index) * 4;
                    } else {
                        offset = index * 4;
                    }

                    MCBinaryInteger add = new MCBinaryInteger(baseReg, base, new Immeidiate(offset), ADDI);
                    operandMap.put(instr.getOperands().get(0).getText(), baseReg);
                    block.getMachineCodes().add(add);
                    setDefUse(baseReg, add);
                    setDefUse(base, add);
                } else if (indexReg instanceof BaseRegister) {
                    MachineOperand indexOp = parseOperand(indexReg);
                    BaseRegister offset = new BaseRegister("offset", int32Type);
                    MachineOperand tmp4 = addLiOperation(new Immeidiate(4), block);
                    MCBinaryInteger mul = new MCBinaryInteger(offset, indexOp, tmp4, MULW);
                    block.getMachineCodes().add(mul);
                    setDefUse(offset, mul);
                    setDefUse(indexOp, mul);
                    setDefUse(tmp4, mul);

                    MCBinaryInteger add = new MCBinaryInteger(baseReg, base, offset, ADD);
                    operandMap.put(instr.getOperands().get(0).getText(), baseReg);
                    block.getMachineCodes().add(add);
                    setDefUse(baseReg, add);
                    setDefUse(offset, add);
                    setDefUse(base, add);
                }
            }
        }
    }

    public void parseLoadInstr(LoadInstruction instr, MachineBlock block) {
        MachineOperand dest = parseOperand(instr.getOperands().get(0));
        MachineOperand src = parseOperand(instr.getOperands().get(1));
        if (src.isImm()) {
            src = addLiOperation(src, block);
        }
        if (dest.isImm()) {
            dest = addLiOperation(dest, block);
        }

        Type type = ((BaseRegister) dest).getType();
        String opcode = type == int32Type ? LW : FLW;

        String srcName = src.toString();
        MachineFunction mfunc = block.getBlockFunc();
        Map<String, Integer> offsetMap = mfunc.getOffsetMap();
        if (null != offsetMap.get(srcName)) {
            int offset = offsetMap.get(srcName);
            MCLoad load = new MCLoad(s0Reg, dest, new Immeidiate(-offset), opcode);
            block.getMachineCodes().add(load);
            setDefUse(dest, load);
            setDefUse(src, load);
        } else {
            if (src.isLabel()) {
                MCLoad la = new MCLoad(src, new PhysicsReg("t0"), LA);
                MCLoad ld = new MCLoad(new PhysicsReg("t0"), dest, opcode);
                block.getMachineCodes().add(la);
                block.getMachineCodes().add(ld);
                setDefUse(src, la);
                setDefUse(dest, ld);
            } else if ((instr.getOperands().get(1)).getType() instanceof PointerType) {
                MCLoad lw = new MCLoad(src, dest, opcode);
                block.getMachineCodes().add(lw);
                setDefUse(dest, lw);
                setDefUse(src, lw);
            }
        }
    }

    public void parsePhiInstr(PhiInstruction instr, MachineBlock block) {

    }

    public void parseReturnInstr(RetInstruction instr, MachineBlock block) {
        List<ValueRef> rets = instr.getOperands();
        if (rets.size() != 0) {
            // return not void
            MachineOperand src = parseOperand(rets.get(0)); // rets.get(0) retValueRef
            if (src.isImm()) {
                MCLi li = new MCLi(a0Reg, src);
                block.getMachineCodes().add(li);
                setDefUse(src, li);
                setDefUse(a0Reg, li);
            } else {
                MCBinaryInteger addw = new MCBinaryInteger(a0Reg, src, new Immeidiate(0), ADDI);
                block.getMachineCodes().add(addw);
                setDefUse(src, addw);
                setDefUse(a0Reg, addw);
            }
        }
        MCReturn ret = new MCReturn();
        block.getMachineCodes().add(ret);
    }

    public void parseStoreInstr(StoreInstruction instr, MachineBlock block) {
        MachineOperand src = parseOperand(instr.getOperands().get(1));
        MachineOperand dest = parseOperand(instr.getOperands().get(2));
        Map<String, Integer> offsetMap = block.getBlockFunc().getOffsetMap();
        if (src.isImm()) {
            src = addLiOperation(src, block);
        }
        if (dest.isImm()) {
            dest = addLiOperation(dest, block);
        }

        Type type = ((BaseRegister) src).getType();
        String opcode = type == int32Type ? SW : FSW;

        String destName = dest.toString();
        if (null != offsetMap.get(destName)) {
            int offset = offsetMap.get(destName);
            if (paramOrder.get((BaseRegister) src) != null) {
                int paramOrd = paramOrder.get((BaseRegister) src);
                src = PhysicsReg.getPhysicsReg(10 + paramOrd);
            }

            MCStore store = new MCStore(src, s0Reg, new Immeidiate(-offset), opcode);
            block.getMachineCodes().add(store);
            setDefUse(src, store);
            setDefUse(dest, store);
        } else {
            if (dest.isLabel()) {
                MCLoad la = new MCLoad(dest, new PhysicsReg("t0"), LA);
                MCStore store = new MCStore(src, new PhysicsReg("t0"), new Immeidiate(0), opcode);
                block.getMachineCodes().add(la);
                block.getMachineCodes().add(store);
                setDefUse(src, store);
                setDefUse(dest, la);
            } else if ((instr.getOperands().get(2)).getType() instanceof PointerType) {
                MCStore store = new MCStore(src, dest, opcode);
                block.getMachineCodes().add(store);
                setDefUse(src, store);
                setDefUse(dest, store);
            }
        }
    }

    public void parseTypeTransInstr(TypeTransInstruction instr, MachineBlock block) {

    }

    public void parseZextInstr(ZextInstruction instr, MachineBlock block) {
        MachineOperand rd = parseOperand(instr.getOperands().get(0));
        MachineOperand rs = parseOperand(instr.getOperands().get(1));
        MCMove move = new MCMove(rs, rd);
        block.getMachineCodes().add(move);
        setDefUse(rd, move);
        setDefUse(rs, move);
    }


    public MachineOperand parseOperand(ValueRef operand) {
        if (!operandMap.containsKey(operand.getText())) {
            if (operand instanceof ConstIntValueRef) {
                int integer = Integer.parseInt(operand.getText());
                MachineOperand intOp = new Immeidiate(integer);
                operandMap.put(operand.getText(), intOp);
                return intOp;
            } else if (operand instanceof ConstFloatValueRef) {
                float floatNum = Float.parseFloat(operand.getText());
                MachineOperand floatOp = new Immeidiate(floatNum);
                operandMap.put(operand.getText(), floatOp);
                return floatOp;
            } else if (operand instanceof GlobalRegister) {
                Type baseType = ((PointerType) operand.getType()).getBaseType();
                if (baseType.equals(IRInt32Type())) {
                    Symbol symbol = globalMap.get(((GlobalRegister) operand).getIdentity());
                    if (symbol.getType().equals(IRInt32Type())) {
                        MachineOperand value = new Label(symbol.getName(), symbol);
                        operandMap.put(((GlobalRegister) operand).getIdentity(), value);
                        return value;
                    }
                }
            } else if (operand instanceof BaseRegister) {
                operandMap.put(operand.getText(), (MachineOperand) operand);
                return (MachineOperand) operand;
            }
        } else {
            return operandMap.get(operand.getText());
        }
        return null;
    }

    public void PrintCodeToFile(String dest) {
        StringBuilder builder = new StringBuilder();
        builder.append(".global main\n");
        builder.append(".data\n");
        builder.append(globalSb);
        builder.append(".text\n");
        for (FunctionBlock function : module.getFunctionBlocks()) {
            builder.append(function.getFunctionName()).append(":").append("\n");
            MachineFunction mfun = funcMap.get(function);
            mfun.allocate();
            List<MachineBlock> retBlocks = new ArrayList<>();
            for (BaseBlock retBlock : function.getRetBlocks()) {
                retBlocks.add(blockMap.get(retBlock));
            }
            mfun.restore(retBlocks);
            for (BaseBlock block : function.getBaseBlocks()) {
                MachineBlock machineBlock = blockMap.get(block);
                builder.append(machineBlock.getBlockName()).append(":\n");
                for (MachineCode code : machineBlock.getMachineCodes()) {
                    builder.append("    ");
                    builder.append(code.toString());
                    builder.append("\n");
                }
            }
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(dest));
            out.write(builder.toString());
            out.close();
        } catch (IOException e) {
            System.err.println("failed to print machine code.");
        }
    }

    public List<MachineFunction> getMCFunctions() {
        List<MachineFunction> mcFuncList = new ArrayList<>();
        for (FunctionBlock function : module.getFunctionBlocks()) {
            MachineFunction mcFunc = funcMap.get(function);
            mcFuncList.add(mcFunc);
        }
        return mcFuncList;
    }

    /**
     * tool method
     */

    public void setDefUse(MachineOperand operand, MachineCode code) {
        if (operand.getIsDef()) {
            operand.addUse(code);
        } else {
            operand.setDef(code);
        }
    }

    /**
     * when we meet an imm but need a reg, add this
     *
     * @return
     */
    public MachineOperand addLiOperation(MachineOperand imm, MachineBlock block) {
        /*
          float: lui(high 20 bits) + addiw(low 12bits)
         */
        if (((Immeidiate) imm).isFloatImm()) {

            float num = ((Immeidiate) imm).getImmFloatValue();
            int high = FloatTools.getHigh20(num);
            int low = FloatTools.getLow12(num);
            // lui
            BaseRegister reg = new BaseRegister("lui", int32Type);
            MachineOperand regOp = parseOperand(reg);
            Immeidiate highImm = new Immeidiate(high);
            MCLui lui = new MCLui(regOp, highImm);
            block.getMachineCodes().add(lui);
            regOp.setDef(lui);
            highImm.addUse(lui);

            // addiw
            Immeidiate lowImm = new Immeidiate(low);
            MCBinaryInteger addiw = new MCBinaryInteger(regOp, regOp, lowImm, ADDIW);
            block.getMachineCodes().add(addiw);
            regOp.setDef(addiw);
            regOp.addUse(addiw);
            lowImm.addUse(addiw);
            return regOp;

        } else {
            BaseRegister reg = new BaseRegister("li", int32Type);
            MachineOperand regOp = parseOperand(reg);
            MCLi li = new MCLi(regOp, imm);
            block.getMachineCodes().add(li);
            regOp.setDef(li);
            imm.addUse(li);
            return regOp;
        }
    }
}
