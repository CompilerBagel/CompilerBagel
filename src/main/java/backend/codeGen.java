package backend;

import IRBuilder.BaseBlock;
import IRBuilder.ConstIntValueRef;
import IRBuilder.FunctionBlock;
import IRBuilder.IRConstants;
import IRBuilder.IRModule;
import IRBuilder.ValueRef;
import backend.machineCode.MCMove;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineFunction;
import backend.machineCode.MachineOperand;
import instruction.CalculateInstruction;
import instruction.Instruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class codeGen {
    
    private IRModule module;
    private List<MachineBlock> blocks;
    private HashMap<FunctionBlock, MachineFunction> funcMap;
    private HashMap<BaseBlock, MachineBlock> blockMap;
    private HashMap<String, MachineOperand> operandMap;
    
    public static List<MachineBlock> serializeBlocks(List<MachineBlock> blocks) {
        List<MachineBlock> sequence = new ArrayList<>();
        Set<MachineBlock> done = new HashSet<>(); // 已经序列化的基本块
        
        // 比较器，用于对基本块进行排序
        Comparator<MachineBlock> blockComparator = Comparator.comparingInt(block -> block.getPredList().size());
        
        // 递归函数，用于序列化每个基本块及其后继块
        for (MachineBlock block : blocks) {
            serializeBlock(block, sequence, done, blockComparator);
        }
        
        return sequence;
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
            }
        }
    }
    
    public void parseCalculateInstr(CalculateInstruction instr, MachineBlock block) {
        switch (instr.getType()) {
            case IRConstants.ADD:
            case IRConstants.SUB: {
                MachineOperand dest = parseOperand(instr.getOperands().get(0), block);
                MachineOperand left = parseOperand(instr.getOperands().get(1), block);
                MachineOperand right = parseOperand(instr.getOperands().get(2), block);
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
                MachineOperand dest = parseOperand(instr.getOperands().get(0), block);
                MachineOperand left = parseOperand(instr.getOperands().get(1), block);
                MachineOperand right = parseOperand(instr.getOperands().get(2), block);
                // TODO: MUL
                break;
            } // TODO： DIV
        }
    }
    
    public MachineOperand parseOperand(ValueRef operand, MachineBlock block) {
        if (!operandMap.containsKey(operand.getText())) {
            if (operand instanceof ConstIntValueRef) {
                int integer = Integer.parseInt(operand.getText());
                return new MachineOperand(integer);
            }
        } else {
            return operandMap.get(operand.getText());
        }
        return null;
    }
}
