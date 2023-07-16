package backend;

import IRBuilder.BaseBlock;
import IRBuilder.BaseRegister;
import IRBuilder.ConstIntValueRef;
import IRBuilder.FunctionBlock;
import IRBuilder.IRConstants;
import IRBuilder.IRModule;
import IRBuilder.ValueRef;
import Type.Type;
import backend.machineCode.MCBinaryInteger;
import backend.machineCode.MCCall;
import backend.machineCode.MCMove;
import backend.machineCode.MCReturn;
import backend.machineCode.MCStore;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineFunction;
import backend.machineCode.MachineOperand;
import backend.reg.PhysicsReg;
import instruction.CalculateInstruction;
import instruction.CallInstruction;
import instruction.Instruction;
import instruction.RetInstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Type.FloatType.IRFloatType;
import static Type.Int1Type.IRInt1Type;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;

public class codeGen {
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private static final Type voidType = IRVoidType();
    private static final PhysicsReg spReg = new PhysicsReg("sp");
    private IRModule module;
    private List<MachineBlock> blocks;
    private HashMap<FunctionBlock, MachineFunction> funcMap;
    private HashMap<BaseBlock, MachineBlock> blockMap;
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
    
    public void MachineCodeGen() {
        List<FunctionBlock> functionBlocks = module.getFunctionBlocks();
        for (FunctionBlock functionBlock : functionBlocks) {
            MachineFunction machineFunction = new MachineFunction(functionBlock.getFunctionName());
            funcMap.put(functionBlock, machineFunction);
        }
        
        for (FunctionBlock func: functionBlocks) {
            List<BaseBlock> funcBlocks = func.getBaseBlocks();
            for (BaseBlock block: funcBlocks) {
                MachineBlock machineBlock = new MachineBlock(block.getLabel(), funcMap.get(func));
                blockMap.put(block, machineBlock);
                blocks.add(machineBlock);
                parseBlock(block, machineBlock);
            }
            // TODO: arg relation
            // TODO: block succ
            serializeBlocks(blocks);
        }
    }
    
    public void parseBlock(BaseBlock block, MachineBlock machineBlock) {
        List<Instruction> instructions = block.getInstructions();
        for (Instruction instr: instructions) {
            if (instr instanceof CalculateInstruction) {
                parseCalculateInstr((CalculateInstruction) instr, machineBlock);
            } else if (instr instanceof RetInstruction) {
                parseReturnInstr((RetInstruction) instr, machineBlock);
            } else if (instr instanceof CallInstruction) {
                parseCallInstr((CallInstruction) instr, machineBlock);
            }
        }
    }
    
    public void parseCalculateInstr(CalculateInstruction instr, MachineBlock block) {
        switch (instr.getType()) {
            case IRConstants.ADD:
            case IRConstants.SUB: {
                MachineOperand dest = parseOperand(instr.getOperands().get(0));
                MachineOperand left = parseOperand(instr.getOperands().get(1));
                MachineOperand right = parseOperand(instr.getOperands().get(2));
                int result = 0;
                if (left.isImm() && right.isImm()) {
                    switch (instr.getType()) {
                        case IRConstants.ADD:
                            result = left.getImmValue() + right.getImmValue();
                            break;
                        case IRConstants.SUB:
                            result = left.getImmValue() - right.getImmValue();
                            break;
                    }
                    MachineOperand src = new MachineOperand(result);
                    MCMove move = new MCMove(src, dest);
                    block.getMachineCodes().add(move);
                }
                break;
            }
            case IRConstants.MUL: {
                MachineOperand dest = parseOperand(instr.getOperands().get(0));
                MachineOperand left = parseOperand(instr.getOperands().get(1));
                MachineOperand right = parseOperand(instr.getOperands().get(2));
                // TODO: MUL
                break;
            } // TODO： DIV
        }
    }
    
    public void parseReturnInstr(RetInstruction instr, MachineBlock block) {
        List<ValueRef> rets = instr.getOperands();
        if (rets.size() != 0) {
            MachineOperand src = parseOperand(rets.get(0));
            MCMove move = new MCMove(src, new PhysicsReg("a0"));
            block.getMachineCodes().add(move);
        }
        MCReturn ret = new MCReturn();
        block.getMachineCodes().add(ret);
    }
    
    public void parseCallInstr(CallInstruction instr, MachineBlock block) {
        List<ValueRef> operands = instr.getOperands();
        int aRegIndex = 10; // a0-a7 <-> x10-x17
        int stackCount = 0;
        List<MachineOperand> uses = new ArrayList<>();
        for (ValueRef param: instr.getParams()) {
            Type type = param.getType();
            if (!(type.equals(IRFloatType()))) {
                MachineOperand src = parseOperand(param);
                if (aRegIndex <= 17) {
                    PhysicsReg reg = new PhysicsReg(aRegIndex);
                    MCMove move = new MCMove(src, reg);
                    uses.add(reg);
                    block.getMachineCodes().add(move);
                    aRegIndex ++;
                } else {
                    MachineOperand offset = new MachineOperand(-(stackCount + 1) * 4);
                    MCStore store = new MCStore(src, spReg, offset);
                    if (src.isImm()) { // 如果是立即数，必须先存在虚拟寄存器，然后再压栈
                        BaseRegister virtualReg = new BaseRegister("tmpImm_" + tmpImmCount, IRInt32Type());
                        MCMove move = new MCMove(src, virtualReg);
                        store.setSrc(virtualReg);
                        block.getMachineCodes().add(move);
                    }
                    block.getMachineCodes().add(store);
                    stackCount ++;
                }
            } else {
                // TODO: call with float arg
            }
        }
        
        // 压栈后，修改sp值
        if (aRegIndex > 17) {
            MachineOperand offset = new MachineOperand(stackCount * 4);
            MCBinaryInteger sub = new MCBinaryInteger(spReg, spReg, offset, IRConstants.SUB);
            block.getMachineCodes().add(sub);
        }
        
        MCCall call = new MCCall(funcMap.get((FunctionBlock) operands.get(1)));
        block.getMachineCodes().add(call);
        call.getUse().addAll(uses);
        for (int i = 10; i < 17; i ++) {
            call.getDef().add(new PhysicsReg(i));
        }
        
        // 调用结束，恢复sp值
        if (stackCount != 0) {
            MachineOperand offset = new MachineOperand(stackCount * 4);
            MCBinaryInteger add = new MCBinaryInteger(spReg, spReg, offset, IRConstants.ADD);
            block.getMachineCodes().add(add);
        }
        
        // 如果有返回值，需要用mv指令取出
        if (!instr.isVoid()) {
            MachineOperand dest = parseOperand(operands.get(0));
            MCMove move = new MCMove(new PhysicsReg("a1"), dest);
            block.getMachineCodes().add(move);
        }
    }
    
    public MachineOperand parseOperand(ValueRef operand) {
        if (!operandMap.containsKey(operand.getText())) {
            if (operand instanceof ConstIntValueRef) {
                int integer = Integer.parseInt(operand.getText());
                MachineOperand intOp = new MachineOperand(integer);
                operandMap.put(operand.getText(), intOp);
                return intOp;
            } else if (operand instanceof BaseRegister) {
                operandMap.put(operand.getText(), (MachineOperand) operand);
                return (MachineOperand) operand;
            }
        } else {
            return operandMap.get(operand.getText());
        }
        return null;
    }
}
