package backend;

import IRBuilder.*;
import Type.Type;
import backend.machineCode.*;
import backend.machineCode.Instruction.*;
import backend.machineCode.Label;
import backend.reg.FloatPhysicsReg;
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
import Type.FunctionType;
import utils.FloatTools;

import static IRBuilder.IRConstants.FpToSi;
import static IRBuilder.IRConstants.MUL;

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
    private static final PhysicsReg zero = PhysicsReg.getPhysicsReg(0);
    private static final PhysicsReg t0Reg = PhysicsReg.getPhysicsReg(5);
    private static final PhysicsReg t1Reg = PhysicsReg.getPhysicsReg(6);
    private static final PhysicsReg t2Reg = PhysicsReg.getPhysicsReg(7);
    public static final FloatPhysicsReg fa0Reg = FloatPhysicsReg.getFa0Reg();

    private static IRModule module;
    private List<MachineBlock> blocks = new ArrayList<>();
    private HashMap<FunctionBlock, MachineFunction> funcMap;
    private static HashMap<BaseBlock, MachineBlock> blockMap;
    private HashMap<String, MachineOperand> operandMap;
    private StringBuilder globalSb;
    private Map<String, Symbol> globalMap;

    private final HashMap<ValueRef, Integer> paramOrder = new HashMap<ValueRef, Integer>();
    private final HashMap<ValueRef, Integer> intParamOrder = new HashMap<ValueRef, Integer>();
    private final HashMap<ValueRef, Integer> floatParamOrder = new HashMap<ValueRef, Integer>();
    private final HashMap<ValueRef, Integer> spillParamOrder = new HashMap<ValueRef, Integer>();

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
            boolean isInt = false;
            if (entry.getValue().isZero()) {
                int len = ((ArrayType) entry.getValue().getType()).getLength() * 4;
                globalSb.append("        .zero ").append(len).append("\n");
            } else {
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
                if (isInt) {
                    List<Integer> values = entry.getValue().getIntinitValue();
                    for (Integer value: values)
                        globalSb.append("        " + ".word ").append(value.intValue()).append("\n");
                } else {
                    List<Float> values = entry.getValue().getInitValue();
                    for (Float value : values) {
                        String hexNumber = "0x" + Integer.toHexString(Float.floatToIntBits(value));
                        globalSb.append("        " + ".word ").append(hexNumber).append("\n");
                    }
                }
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
        int intIndex = 0;
        int floatIndex = 0;
        int spillIndex = 0;
        for (ValueRef param : params) {
            paramOrder.put(param, i);
            i++;
            if (param.getType() == floatType) {
                if (floatIndex > 7) {
                    spillParamOrder.put(param, spillIndex);
                    spillIndex++;
                }
                floatParamOrder.put(param, floatIndex);
                floatIndex++;
            } else {
                if (intIndex > 7) {
                    spillParamOrder.put(param, spillIndex);
                    spillIndex++;
                }
                intParamOrder.put(param, intIndex);
                intIndex++;
            }
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
            mfunc.setStackCount(stackCount);
            mfunc.setAlignSize(stackAlign(stackCount) - stackCount * 4);
            mfunc.setFrameSize(stackAlign(stackCount));
        } else if (resType instanceof ArrayType) {
            // 计算数组长度
            int arrayLen = (((ArrayType) (instr.getPointedType())).getLength());
            stackCount += arrayLen;
            mfunc.setStackCount(stackCount);
            mfunc.setAlignSize(stackAlign(stackCount) - stackCount * 4);
            mfunc.setFrameSize(stackAlign(stackCount));
            offsetMap.put(resName, stackCount * 4);
            // 计算数组所占空间大小
            int arraySize = arrayLen * 4;
            // 如果不是8的倍数，先分配第一个4字节，使其对齐
            if (arraySize % 8 != 0) {
                offsetMap.put(resName, stackCount * 4);
                arraySize -= 4;
                stackCount -= 1;
            }
            if (mfunc.getFrameSize() < 2048) {
                for (; arraySize > 0; arraySize -= 8) {
                    MCStore store = new MCStore(zero, s0Reg, new Immeidiate(-(stackCount * 4)), SD);
                    block.getMachineCodes().add(store);
                    stackCount -= 2;
                }
            } else {
                int overflow = mfunc.getOverflow();
                mfunc.setOverflow(overflow + arraySize);

                MCLi liT0 = new MCLi(t0Reg, new Immeidiate(-(stackCount * 4)));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                MCLi liT1 = new MCLi(t1Reg, new Immeidiate(0));
                MCLi liT2 = new MCLi(PhysicsReg.getPhysicsReg(7), new Immeidiate(arraySize));
                MCCall call = new MCCall(new MachineFunction("bagel_memset"), null);
                block.getMachineCodes().add(liT0);
                block.getMachineCodes().add(add);
                block.getMachineCodes().add(liT1);
                block.getMachineCodes().add(liT2);
                block.getMachineCodes().add(call);
            }
        } else if (resType instanceof PointerType) {
            stackCount += 2;
            offsetMap.put(resName, stackCount * 4);
            mfunc.setStackCount(stackCount);
            mfunc.setAlignSize(stackAlign(stackCount) - stackCount * 4);
            mfunc.setFrameSize(stackAlign(stackCount));
        }
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
            if (instr.getType().equals(IRConstants.ADD)) {
                MCBinaryInteger addi = new MCBinaryInteger(dest, left, right, ADDI);
                block.getMachineCodes().add(addi);
                setDefUse(dest, addi);
                setDefUse(left, addi);
                return;
            } else if (instr.getType().equals(IRConstants.SUB)) {
                MCBinaryInteger subi = new MCBinaryInteger(dest, left, right, SUBI);
                block.getMachineCodes().add(subi);
                setDefUse(dest, subi);
                setDefUse(left, subi);
                return;
            }
        }

        String machineOp;
        Type varType = ((BaseRegister) left).getType();
        if (varType instanceof PointerType) {
            while (varType instanceof PointerType) {
                varType = ((PointerType) varType).getBaseType();
            }
        }
        if (varType == int32Type || varType == int1Type) {
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

        ValueRef destVirtualReg = instr.getOperands().get(0);
        int spillIndex = destVirtualReg.getSpillIndex();
        Type type = destVirtualReg.getType();
        if (spillIndex != -1) {
            if (spillIndex == 0) {
                MCMove move = new MCMove(spReg, t1Reg);
                block.getMachineCodes().add(move);
            }
            if (type == floatType) {
                if (destVirtualReg.getFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(spillIndex * 8), FSW);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), FSW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            } else {
                if (destVirtualReg.getNoFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(spillIndex * 8), SD);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), SD);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            }
        }
    }

    public void parseCallInstr(CallInstruction instr, MachineBlock block) {
        BaseRegister dest = (BaseRegister) instr.getOperands().get(0);
        List<ValueRef> params = instr.getParams();
        List<MachineOperand> operands = new ArrayList<>();

        // push a1, a2, ...
        int paramCnt = params.size();
        MachineFunction mcFunc = block.getBlockFunc();
        int stackCount = mcFunc.getStackCount();
        Map<String, Integer> offsetMap = mcFunc.getOffsetMap();
        for (int i = 1; i < Integer.max(paramCnt + 2, 4) && i < 8; i++) {
            stackCount += 2;
            int offset = stackCount * 4;
            MCStore store;
            if (isLegalImm(-offset)) {
                store = new MCStore(PhysicsReg.getPhysicsReg(10 + i), s0Reg, new Immeidiate(-offset), SD);
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                store = new MCStore(PhysicsReg.getPhysicsReg(10 + i), t0Reg, SD);
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
            }
            offsetMap.put("phyReg_a" + i, offset);
            block.getMachineCodes().add(store);
        }
        // TODO: add if, push t3, t4, t5, t6
        if (paramCnt > 7) {
            for (int i = 1; i <= 4; i++) {
                stackCount += 2;
                int offset = stackCount * 4;
                MCStore store;
                if (isLegalImm(-offset)) {
                    store = new MCStore(PhysicsReg.getPhysicsReg(27 + i), s0Reg, new Immeidiate(-offset), SD);
                } else {
                    MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                    MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                    store = new MCStore(PhysicsReg.getPhysicsReg(27 + i), t0Reg, SD);
                    block.getMachineCodes().add(li);
                    block.getMachineCodes().add(add);
                }
                offsetMap.put("phyReg_t" + (i + 2), offset);
                block.getMachineCodes().add(store);
            }
        }

        // push ft0, ft1
        for (int i = 0; i < 4; i++) {
            stackCount += 2;
            int offset = stackCount * 4;
            MCStore store;
            if (isLegalImm(-offset)) {
                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), s0Reg, new Immeidiate(-offset), FSW);
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), t0Reg, FSW);
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
            }
            offsetMap.put("floatPhyReg_a" + i, offset);
            block.getMachineCodes().add(store);
        }
        // TODO: notice
//        for (int i = 28; i < 32; i++) {
//            stackCount += 2;
//            int offset = stackCount * 4;
//            MCStore store;
//            if (isLegalImm(-offset)) {
//                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), s0Reg, new Immeidiate(-offset), FSW);
//            } else {
//                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
//                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
//                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), t0Reg, FSW);
//                block.getMachineCodes().add(li);
//                block.getMachineCodes().add(add);
//            }
//            offsetMap.put("floatPhyReg_a" + i, offset);
//            block.getMachineCodes().add(store);
//        }
//        for (int i = 10; i < 18; i++) {
//            stackCount += 2;
//            int offset = stackCount * 4;
//            MCStore store;
//            if (isLegalImm(-offset)) {
//                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), s0Reg, new Immeidiate(-offset), FSW);
//            } else {
//                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
//                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
//                store = new MCStore(FloatPhysicsReg.getFloatPhysicsReg(i), t0Reg, FSW);
//                block.getMachineCodes().add(li);
//                block.getMachineCodes().add(add);
//            }
//            offsetMap.put("floatPhyReg_a" + i, offset);
//            block.getMachineCodes().add(store);
//        }
        mcFunc.setStackCount(stackCount);
        mcFunc.setFrameSize(stackAlign(stackCount));
        int i = 0;
        int intRegIndex = 0;
        int floatRegIndex = 0;
        int spillIndex = 0;

        /**
          @params_pass
         * 0-7: use a0-a7 or fa0-fa7
         * 8-15: use ld/flw instruction to save in stack
         * >15: already save in stack
         */
        for (ValueRef param : params) {
            boolean outOf15 = false;
            MachineOperand op = parseOperand(param);
            if (op.isImm()) {
                MachineOperand tmp = addLiOperation(op, block);
                if (((Immeidiate) op).isFloatImm()) {
                    if (floatRegIndex > 7) {
                        // TODO: check
                        if (floatRegIndex > 15) {
                            outOf15 = true;
                        }
                        stackCount += 2;
                        if (spillIndex == 0) {
                            MCMove move = new MCMove(spReg, t1Reg);
                            block.getMachineCodes().add(move);
                        }
                        int offset = spillIndex * 8;
                        MCStore fsw;
                        if (isLegalImm(offset)) {
                            fsw = new MCStore(tmp, t1Reg, new Immeidiate(offset), FSW);
                        } else {
                            MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                            MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                            fsw = new MCStore(tmp, t0Reg, FSW);
                            block.getMachineCodes().add(li);
                            block.getMachineCodes().add(add);
                        }
                        setDefUse(tmp, fsw);
                        spillIndex++;
                        block.getMachineCodes().add(fsw);
                    } else {
                        tmp.setPhysicsReg(FloatPhysicsReg.getFloatPhysicsReg(10 + floatRegIndex));
                    }

                    floatRegIndex++;
                } else {
                    if (intRegIndex > 7) {
                        if (intRegIndex > 15) {
                            outOf15 = true;
                        }
                        stackCount += 2;
                        if (spillIndex == 0) {
                            MCMove move = new MCMove(spReg, t1Reg);
                            block.getMachineCodes().add(move);
                        }
                        int offset = spillIndex * 8;
                        MCStore sd;
                        if (isLegalImm(offset)) {
                            sd = new MCStore(tmp, t1Reg, new Immeidiate(offset), SD);
                        } else {
                            MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                            MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                            sd = new MCStore(tmp, t0Reg, SD);
                            block.getMachineCodes().add(li);
                            block.getMachineCodes().add(add);
                        }
                        setDefUse(tmp, sd);
                        spillIndex++;
                        block.getMachineCodes().add(sd);
                        tmp.setPhysicsReg(t2Reg);
                    } else {
                        tmp.setPhysicsReg(PhysicsReg.getPhysicsReg(10 + intRegIndex));
                    }
                    intRegIndex++;
                }
                if (!outOf15) operands.add(tmp);
            } else {
                BaseRegister src = new BaseRegister(param.getText(), param.getType());
                if (src.getType() == floatType) {
                    if (floatRegIndex > 7) {
                        stackCount += 2;
                        if (floatRegIndex > 15) {
                            outOf15 = true;
                            spillIndex++;
                        } else {
                            if (spillIndex == 0) {
                                MCMove move = new MCMove(spReg, t1Reg);
                                block.getMachineCodes().add(move);
                            }
                            int offset = spillIndex * 8;
                            MCStore fsw;
                            if (isLegalImm(offset)) {
                                fsw = new MCStore(op, t1Reg, new Immeidiate(offset), FSW);;
                            } else {
                                MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                                MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                                fsw = new MCStore(op, t0Reg, FSW);;
                                block.getMachineCodes().add(li);
                                block.getMachineCodes().add(add);
                            }
                            setDefUse(op, fsw);
                            spillIndex++;
                            block.getMachineCodes().add(fsw);
                        }
                        floatRegIndex++;
                    } else {
                        // Float number use twice fneg to move params to fa0, fa1, ...
                        src.setPhysicsReg(FloatPhysicsReg.getFloatPhysicsReg(10 + floatRegIndex));
                        BaseRegister negParam = new BaseRegister("negParam", floatType);
                        MCFNeg neg1 = new MCFNeg(negParam, op);
                        MCFNeg neg2 = new MCFNeg(FloatPhysicsReg.getFloatPhysicsReg(10 + floatRegIndex), negParam);
                        floatRegIndex++;
                        setDefUse(op, neg1);
                        setDefUse(negParam, neg1);
                        setDefUse(negParam, neg2);
                        // setDefUse(src, neg2);
                        block.getMachineCodes().add(neg1);
                        block.getMachineCodes().add(neg2);
                    }

                } else {  // intType or pointerType
                    if (intRegIndex > 7) {
                        stackCount += 2;
                        if (intRegIndex > 15) {
                            outOf15 = true;
                            spillIndex++;
                        } else {
                            if (spillIndex == 0) {
                                MCMove move = new MCMove(spReg, t1Reg);
                                block.getMachineCodes().add(move);
                            }
                            int offset = spillIndex * 8;
                            MCStore sd;
                            if (isLegalImm(offset)) {
                                sd = new MCStore(op, t1Reg, new Immeidiate(offset), SD);
                            } else {
                                MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                                MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                                sd = new MCStore(op, t0Reg, SD);
                                block.getMachineCodes().add(li);
                                block.getMachineCodes().add(add);
                            }
                            setDefUse(op, sd);
                            spillIndex++;
                            block.getMachineCodes().add(sd);
                        }
                        intRegIndex++;
                    } else {
                        // Use mv to move params to a0, a1, ...
                        src.setPhysicsReg(PhysicsReg.getPhysicsReg(10 + intRegIndex));
                        MCMove mv = new MCMove(op, PhysicsReg.getPhysicsReg(10 + intRegIndex));
                        intRegIndex++;
                        // setDefUse(src, mv);
                        setDefUse(op, mv);
                        block.getMachineCodes().add(mv);
                    }

                }
                if (!outOf15) operands.add(src);
            }
            i++;
        }
        MachineFunction mcFunction = funcMap.get(instr.getFunction());
        MCCall call;
        String funcName = instr.getFunction().getFunctionName();
        if (funcName.equals("starttime") || funcName.equals("stoptime")) {
            funcName = "_sysy_" + funcName;
        }
        if (mcFunction != null) {
            call = new MCCall(funcMap.get(instr.getFunction()), operands);
        } else {
            MachineFunction outFunc = new MachineFunction(funcName);
            call = new MCCall(outFunc, operands);
        }
        for (MachineOperand operand : operands) {
            operand.addUse(call);
        }

        // Move the a0/fa0 to dest(Operand)
        BaseRegister ret = new BaseRegister("ret", dest.getType());
        MCMove mv = null;
        MCFNeg neg1 = null;
        MCFNeg neg2 = null;
        if (ret.getType() == floatType) {
            BaseRegister negRet = new BaseRegister("negRet", floatType);
            neg1 = new MCFNeg(negRet, ret);
            neg2 = new MCFNeg(dest, negRet);
            negRet.setPhysicsReg(fa0Reg);
            ret.setPhysicsReg(fa0Reg);
            setDefUse(ret, neg1);
            setDefUse(negRet, neg1);
            setDefUse(negRet, neg2);
            setDefUse(dest, neg2);
        } else {
            mv = new MCMove(ret, dest);
            ret.setPhysicsReg(a0Reg);
            setDefUse(ret, mv);
            setDefUse(dest, mv);
        }
        block.getMachineCodes().add(call);

        for (i = 1; i < Integer.max(paramCnt + 2, 4) && i < 8; i++) {
            int offset = offsetMap.get("phyReg_a" + i);
            MCLoad load;
            if (isLegalImm(-offset)) {
                load = new MCLoad(s0Reg, PhysicsReg.getPhysicsReg(10 + i), new Immeidiate(-offset), LD);
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                load = new MCLoad(t0Reg, PhysicsReg.getPhysicsReg(10 + i), LD);
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
            }
            block.getMachineCodes().add(load);
        }
        // TODO: add if pop t3, t4, t5, t6
        if (paramCnt > 7) {
            for (i = 1; i <= 4; i++) {
                int offset = offsetMap.get("phyReg_t" + (i + 2));
                MCLoad load;
                if (isLegalImm(-offset)) {
                    load = new MCLoad(s0Reg, PhysicsReg.getPhysicsReg(27 + i), new Immeidiate(-offset), LD);
                } else {
                    MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                    MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                    load = new MCLoad(t0Reg, PhysicsReg.getPhysicsReg(27 + i), LD);
                    block.getMachineCodes().add(li);
                    block.getMachineCodes().add(add);
                }
                block.getMachineCodes().add(load);
            }
        }

        for (i = 0; i < 4; i++) {
            int offset = offsetMap.get("floatPhyReg_a" + i);
            MCLoad load;
            if (isLegalImm(-offset)) {
                load = new MCLoad(s0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), new Immeidiate(-offset), FLW);
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                load = new MCLoad(t0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), FLW);
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
            }
            block.getMachineCodes().add(load);
        }
//        for (i = 28; i < 32; i++) {
//            int offset = offsetMap.get("floatPhyReg_a" + i);
//            MCLoad load;
//            if (isLegalImm(-offset)) {
//                load = new MCLoad(s0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), new Immeidiate(-offset), FLW);
//            } else {
//                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
//                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
//                load = new MCLoad(t0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), FLW);
//                block.getMachineCodes().add(li);
//                block.getMachineCodes().add(add);
//            }
//            block.getMachineCodes().add(load);
//        }
//        for (i = 10; i < 18; i++) {
//            int offset = offsetMap.get("floatPhyReg_a" + i);
//            MCLoad load;
//            if (isLegalImm(-offset)) {
//                load = new MCLoad(s0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), new Immeidiate(-offset), FLW);
//            } else {
//                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
//                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
//                load = new MCLoad(t0Reg, FloatPhysicsReg.getFloatPhysicsReg(i), FLW);
//                block.getMachineCodes().add(li);
//                block.getMachineCodes().add(add);
//            }
//            block.getMachineCodes().add(load);
//        }
        if (mv != null) block.getMachineCodes().add(mv);
        if (neg1 != null) block.getMachineCodes().add(neg1);
        if (neg2 != null) block.getMachineCodes().add(neg2);
        mcFunc.setStackCount(stackCount);
        mcFunc.setFrameSize(stackAlign(stackCount));

        // As param
        ValueRef destVirtualReg = instr.getOperands().get(0);
        spillIndex = destVirtualReg.getSpillIndex();
        Type type = destVirtualReg.getType();
        if (spillIndex != -1) {
            if (spillIndex == 0) {
                MCMove move = new MCMove(spReg, t1Reg);
                block.getMachineCodes().add(move);
            }
            if (type == floatType) {
                if (destVirtualReg.getFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), FSW);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), FSW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            } else {
                if (destVirtualReg.getNoFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), SD);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), SD);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            }
        }
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

        Type varType = ((BaseRegister) left).getType();

        switch (instr.getIcmpType()) {
            case IRConstants.IRIntSLT, IRConstants.IRIntULT -> {
                MachineCode set;
                if (varType == int32Type) {
                    set = new MCSet(dest, left, right);
                } else {
                    set = new MCFCmp(dest, left, right, FLT);
                }
                block.getMachineCodes().add(set);
                setDefUseThreeOp(dest, left, right, set);
            }
            case IRConstants.IRIntSGT, IRConstants.IRIntUGT -> {
                MachineCode set;
                if (varType == int32Type) {
                    set = new MCSet(dest, right, left);
                } else {
                    set = new MCFCmp(dest, right, left, FLT);
                }
                block.getMachineCodes().add(set);
                setDefUseThreeOp(dest, left, right, set);
            }
            case IRConstants.IRIntEQ -> {
                if (varType == int32Type) {
                    MachineOperand res = new BaseRegister("tmp", int32Type);
                    MCBinaryInteger sub = new MCBinaryInteger(res, left, right, SUBW);
                    MCSetz setz = new MCSetz(dest, res, IRConstants.IRIntEQ);
                    block.getMachineCodes().add(sub);
                    block.getMachineCodes().add(setz);
                    setDefUse(dest, setz);
                    setDefUse(res, setz);
                    setDefUseThreeOp(res, left, right, sub);
                } else {
                    MachineCode feq = new MCFCmp(dest, left, right, FEQ);
                    block.getMachineCodes().add(feq);
                    setDefUseThreeOp(dest, left, right, feq);
                }
            }
            case IRConstants.IRIntNE -> {
                MachineOperand res = new BaseRegister("tmp", int32Type);
                if (varType == int32Type) {
                    MCBinaryInteger sub = new MCBinaryInteger(res, left, right, SUBW);
                    MCSetz setz = new MCSetz(dest, res, IRConstants.IRIntNE);
                    block.getMachineCodes().add(sub);
                    block.getMachineCodes().add(setz);
                    setDefUse(dest, setz);
                    setDefUse(res, setz);
                    setDefUseThreeOp(res, left, right, sub);
                } else {
                    MachineCode feq = new MCFCmp(res, left, right, FEQ);
                    MCBinaryInteger xor = new MCBinaryInteger(dest, res, new Immeidiate(1), XORI);
                    block.getMachineCodes().add(feq);
                    block.getMachineCodes().add(xor);
                    setDefUseThreeOp(res, left, right, feq);
                    setDefUse(dest, xor);
                    setDefUse(res, xor);
                }
            }
            case IRConstants.IRIntSGE, IRConstants.IRIntUGE -> {
                if (varType == int32Type) {
                    MachineOperand res = new BaseRegister("tmp", int32Type);
                    MCSet set = new MCSet(res, left, right);
                    MCBinaryInteger xor = new MCBinaryInteger(dest, res, new Immeidiate(1), XORI);
                    block.getMachineCodes().add(set);
                    block.getMachineCodes().add(xor);
                    setDefUse(dest, xor);
                    setDefUse(res, xor);
                    setDefUseThreeOp(res, left, right, set);
                } else {
                    MachineCode set = new MCFCmp(dest, right, left, FLE);
                    block.getMachineCodes().add(set);
                    setDefUseThreeOp(dest, left, right, set);
                }

            }
            case IRConstants.IRIntSLE, IRConstants.IRIntULE -> {
                if (varType == int32Type) {
                    MachineOperand res = new BaseRegister("tmp", int32Type);
                    MCSet set = new MCSet(res, right, left);
                    MCBinaryInteger xor = new MCBinaryInteger(dest, res, new Immeidiate(1), XORI);
                    block.getMachineCodes().add(set);
                    block.getMachineCodes().add(xor);
                    setDefUse(dest, xor);
                    setDefUse(res, xor);
                    setDefUseThreeOp(res, left, right, set);
                } else {
                    MachineCode set = new MCFCmp(dest, left, right, FLE);
                    block.getMachineCodes().add(set);
                    setDefUseThreeOp(dest, left, right, set);
                }
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
                    MachineOperand dest = parseOperand(instr.getOperands().get(0));
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

                    MCLi li = new MCLi(t0Reg, new Immeidiate(-(base - offset)));
                    MCBinaryInteger add = new MCBinaryInteger(dest, s0Reg, t0Reg, ADD);
                    offsetMap.put(instr.getOperands().get(0).getText(), base - offset);
                    block.getMachineCodes().add(li);
                    block.getMachineCodes().add(add);
                    setDefUse(dest, add);
                } else if (indexReg instanceof BaseRegister) {
                    MachineOperand indexOp = parseOperand(indexReg);
                    BaseRegister offset = new BaseRegister("offset", int32Type);
                    Type baseType = ((PointerType) instr.getOperands().get(0).getType()).getBaseType();
                    if (baseType instanceof ArrayType) { // multi layer
                        int dims = 0;
                        Type tmp = baseType;
                        while (tmp instanceof ArrayType) {
                            tmp = ((ArrayType) tmp).getElementType();
                            dims++;
                        }

                        int otherDimLen = ((ArrayType) baseType).getOtherDimensionLength(dims, 1) * 4;
                        MCLi li = new MCLi(t0Reg, new Immeidiate(otherDimLen));
                        MCBinaryInteger mul = new MCBinaryInteger(offset, indexOp, t0Reg, MULW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(mul);
                        setDefUse(offset, mul);
                        setDefUse(indexOp, mul);
                    } else { // 1 layer
                        MCLi li = new MCLi(t0Reg, new Immeidiate(4));
                        MCBinaryInteger mul = new MCBinaryInteger(offset, indexOp, t0Reg, MULW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(mul);
                        setDefUse(offset, mul);
                        setDefUse(indexOp, mul);
                    }

                    MCLi li1 = new MCLi(t0Reg, new Immeidiate(base));
                    MCBinaryInteger addOffset = new MCBinaryInteger(offset, t0Reg, offset, SUB);
                    block.getMachineCodes().add(li1);
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
                ValueRef indexReg;
                if (instr.getOperands().size() < 4) {
                    indexReg = instr.getOperands().get(2);
                } else indexReg = instr.getOperands().get(3);
                MachineOperand baseReg = new BaseRegister(pointer.getText(), pointer.getType());
                if (indexReg instanceof ConstIntValueRef) {
                    int offset;
                    int index = ((ConstIntValueRef) (indexReg)).getValue();
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

                    MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                    MCBinaryInteger add = new MCBinaryInteger(baseReg, base, t0Reg, ADD);
                    operandMap.put(instr.getOperands().get(0).getText(), baseReg);
                    block.getMachineCodes().add(li);
                    block.getMachineCodes().add(add);
                    setDefUse(baseReg, add);
                    setDefUse(base, add);
                } else if (indexReg instanceof BaseRegister) {
                    MachineOperand indexOp = parseOperand(indexReg);
                    BaseRegister offsetReg = new BaseRegister("offsetReg", int32Type);
                    int offset = 0;
                    Type baseType = ((PointerType) instr.getOperands().get(0).getType()).getBaseType();
                    if (baseType instanceof ArrayType) {
                        int dims = 0;
                        Type tmp = baseType;
                        while (tmp instanceof ArrayType) {
                            tmp = ((ArrayType) tmp).getElementType();
                            dims++;
                        }
                        offset = ((ArrayType) baseType).getOtherDimensionLength(dims, 1) * 4;
                    } else {
                        offset = 4;
                    }

                    MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                    MCBinaryInteger mul = new MCBinaryInteger(offsetReg, indexOp, t0Reg, MUL);
                    block.getMachineCodes().add(li);
                    block.getMachineCodes().add(mul);
                    setDefUse(offsetReg, mul);
                    setDefUse(indexOp, mul);

                    MCBinaryInteger add = new MCBinaryInteger(baseReg, base, offsetReg, ADD);
                    operandMap.put(instr.getOperands().get(0).getText(), baseReg);
                    block.getMachineCodes().add(add);
                    setDefUse(baseReg, add);
                    setDefUse(offsetReg, add);
                    setDefUse(base, add);
                }
            }
        }
        // TODO: check
        ValueRef destVirtualReg = instr.getOperands().get(0);
        MachineOperand dest = parseOperand(destVirtualReg);
        int spillIndex = destVirtualReg.getSpillIndex();
        Type type = destVirtualReg.getType();

        if (spillIndex != -1) {
            if (spillIndex == 0) {
                MCMove move = new MCMove(spReg, t1Reg);
                block.getMachineCodes().add(move);
            }
            if (type == floatType) {
                if (destVirtualReg.getFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), FSW);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), FSW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            } else {
                if (destVirtualReg.getNoFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), SD);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), SD);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
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
        String opcode = type == floatType ? FLW : LW;

        String srcName = src.toString();
        MachineFunction mfunc = block.getBlockFunc();
        Map<String, Integer> offsetMap = mfunc.getOffsetMap();
        if (null != offsetMap.get(srcName)) {
            MCLoad load;
            int offset = offsetMap.get(srcName);
            if (isLegalImm(-offset)) {
                if ((instr.getOperands().get(0)).getType() instanceof PointerType) {
                    load = new MCLoad(s0Reg, dest, new Immeidiate(-offset), LD);
                } else {
                    load = new MCLoad(s0Reg, dest, new Immeidiate(-offset), opcode);
                }
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
                if ((instr.getOperands().get(0)).getType() instanceof PointerType) {
                    load = new MCLoad(t0Reg, dest, new Immeidiate(0), LD);
                } else {
                    load = new MCLoad(t0Reg, dest, new Immeidiate(0), opcode);
                }
            }
            block.getMachineCodes().add(load);
            setDefUse(dest, load);
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
        ValueRef destVirtualReg = instr.getOperands().get(0);
        int spillIndex = destVirtualReg.getSpillIndex();

        if (spillIndex != -1) {
            if (spillIndex == 0) {
                MCMove move = new MCMove(spReg, t1Reg);
                block.getMachineCodes().add(move);
            }
            if (type == floatType) {
                if (destVirtualReg.getFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), FSW);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), FSW);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            } else {
                if (destVirtualReg.getNoFloatNO() > 15) {
                    int offset = spillIndex * 8;
                    MCStore sd;
                    if (isLegalImm(offset)) {
                        sd = new MCStore(dest, t1Reg, new Immeidiate(offset), SD);
                    } else {
                        MCLi li = new MCLi(t0Reg, new Immeidiate(offset));
                        MCBinaryInteger add = new MCBinaryInteger(t0Reg, t1Reg, t0Reg, ADD);
                        sd = new MCStore(dest, t0Reg, new Immeidiate(0), SD);
                        block.getMachineCodes().add(li);
                        block.getMachineCodes().add(add);
                    }
                    setDefUse(dest, sd);
                    block.getMachineCodes().add(sd);
                }
            }
        }
    }

    public void parsePhiInstr(PhiInstruction instr, MachineBlock block) {

    }

    public void parseReturnInstr(RetInstruction instr, MachineBlock block) {
        List<ValueRef> rets = instr.getOperands();
        if (null != rets.get(0)) {
            // return not void
            MachineOperand src = parseOperand(rets.get(0)); // rets.get(0) retValueRef
            if (src.isImm()) {
                if (((Immeidiate) src).isFloatImm()) {
                    src = addLiOperation(src, block);
                    src.setPhysicsReg(fa0Reg);
                } else {
                    MCLi li = new MCLi(a0Reg, src);
                    block.getMachineCodes().add(li);
                    setDefUse(src, li);
                    setDefUse(a0Reg, li);
                }

            } else {
                Type type = ((BaseRegister) src).getType();
                if (type == int32Type) {
                    MCBinaryInteger addw = new MCBinaryInteger(a0Reg, src, new Immeidiate(0), ADDI);
                    block.getMachineCodes().add(addw);
                    setDefUse(src, addw);
                    setDefUse(a0Reg, addw);
                } else {
                    BaseRegister negRet = new BaseRegister("negRet", floatType);
                    BaseRegister ret = new BaseRegister("ret", floatType);
                    MCFNeg neg1 = new MCFNeg(negRet, src);
                    MCFNeg neg2 = new MCFNeg(ret, negRet);
                    negRet.setPhysicsReg(fa0Reg);
                    ret.setPhysicsReg(fa0Reg);
                    setDefUse(src, neg1);
                    setDefUse(negRet, neg1);
                    setDefUse(negRet, neg2);
                    setDefUse(ret, neg2);
                    block.getMachineCodes().add(neg1);
                    block.getMachineCodes().add(neg2);
                }
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
                // Storing function parameters on the stack
                int paramOrd = paramOrder.get((BaseRegister) src);

                if (((BaseRegister) src).getType() == floatType) {
                    int floatOrd = floatParamOrder.get((BaseRegister) src);
                    if (floatOrd > 7) {
                        // load from stack
                        int spillOrder = spillParamOrder.get((BaseRegister) src);
                        BaseRegister ldReg = new BaseRegister("paramLd", floatType);
                        int spillOffset = spillOrder * 8;
                        MCLoad ld;
                        if (isLegalImm(spillOffset)) {
                            ld = new MCLoad(s0Reg, ldReg, new Immeidiate(spillOffset), FLW);
                        } else {
                            MCLi li = new MCLi(t0Reg, new Immeidiate(spillOffset));
                            MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                            ld = new MCLoad(t0Reg, ldReg, FLW);
                            block.getMachineCodes().add(li);
                            block.getMachineCodes().add(add);
                        }
                        setDefUse(ldReg, ld);
                        block.getMachineCodes().add(ld);
                        src = ldReg;
                    } else {
                        // load from fa0-fa7
                        src = FloatPhysicsReg.getFloatPhysicsReg(10 + floatOrd);
                    }

                } else {
                    int intOrd = intParamOrder.get((BaseRegister) src);
                    if (intOrd > 7) {
                        // load from stack
                        int spillOrder = spillParamOrder.get((BaseRegister) src);
                        BaseRegister ldReg = new BaseRegister("paramLd", int32Type);

                        int spillOffset = spillOrder * 8;
                        MCLoad ld;
                        if (isLegalImm(spillOffset)) {
                            ld = new MCLoad(s0Reg, ldReg, new Immeidiate(spillOffset), LD);
                        } else {
                            MCLi li = new MCLi(t0Reg, new Immeidiate(spillOffset));
                            MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                            ld = new MCLoad(t0Reg, ldReg, LD);
                            block.getMachineCodes().add(li);
                            block.getMachineCodes().add(add);
                        }
                        setDefUse(ldReg, ld);
                        block.getMachineCodes().add(ld);
                        src = ldReg;

                    } else {
                        // load from a0-a7
                        src = PhysicsReg.getPhysicsReg(10 + intOrd);
                    }

                }
            }

            MCStore store;
            if (isLegalImm(-offset)) {
                if ((instr.getOperands().get(1)).getType() instanceof PointerType) {
                    store = new MCStore(src, s0Reg, new Immeidiate(-offset), SD);
                } else {
                    store = new MCStore(src, s0Reg, new Immeidiate(-offset), opcode);
                }
            } else {
                MCLi li = new MCLi(t0Reg, new Immeidiate(-offset));
                MCBinaryInteger add = new MCBinaryInteger(t0Reg, s0Reg, t0Reg, ADD);
                if ((instr.getOperands().get(1)).getType() instanceof PointerType) {
                    store = new MCStore(src, t0Reg, new Immeidiate(0), SD);
                } else {
                    store = new MCStore(src, t0Reg, new Immeidiate(0), opcode);
                }
                block.getMachineCodes().add(li);
                block.getMachineCodes().add(add);
            }

            block.getMachineCodes().add(store);
            setDefUse(src, store);
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
        MachineOperand src = parseOperand(instr.getOperands().get(0));
        MachineOperand dest = parseOperand(instr.getOperands().get(1));
        int optType = instr.getOptType();
        String fCvtOp = optType == FpToSi ? FCVT_W_S : FCVT_S_W;
        if (src.isImm()) {
            src = addLiOperation(src, block);
        }
        MCFCvt fCvt = new MCFCvt(dest, src, fCvtOp);
        block.getMachineCodes().add(fCvt);
        setDefUse(dest, fCvt);
        setDefUse(src, fCvt);
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
                } else if (baseType.equals(floatType)) {
                    Symbol symbol = globalMap.get(((GlobalRegister) operand).getIdentity());
                    if (symbol.getType().equals(floatType)) {
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
        builder.append(".global bagel_memset\n" +
                "bagel_memset:\n" +
                "    Block0:\n" +
                "        beq t2, zero, Block1\n" +
                "        sd t1, 0(t0)\n" +
                "        addi t2, t2, -8\n" +
                "        addi t0, t0, 8\n" +
                "        j Block0\n" +
                "    Block1:\n" +
                "        ret\n");
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
            Type retType = ((FunctionType) function.getType()).getRetType();
            mfun.restore(retBlocks, retType.equals(IRInt32Type()) || retType.equals(floatType));
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

    public void setDefUseThreeOp(MachineOperand dest, MachineOperand left, MachineOperand right, MachineCode code) {
        setDefUse(dest, code);
        setDefUse(left, code);
        setDefUse(right, code);
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

            if (low >= 2048) {
                BaseRegister regLow = new BaseRegister("li", int32Type);
                MCLi liLow = new MCLi(regLow, new Immeidiate(low));
                regLow.setDef(liLow);
                block.getMachineCodes().add(liLow);
                MCBinaryInteger add = new MCBinaryInteger(regOp, regOp, regLow, ADD);
                block.getMachineCodes().add(add);
                regOp.setDef(add);
                regOp.addUse(add);
                regLow.addUse(add);
            } else {
                // addiw
                Immeidiate lowImm = new Immeidiate(low);
                MCBinaryInteger addiw = new MCBinaryInteger(regOp, regOp, lowImm, ADDIW);
                block.getMachineCodes().add(addiw);
                regOp.setDef(addiw);
                regOp.addUse(addiw);
                lowImm.addUse(addiw);
            }
            // fmv.w.x
            BaseRegister resReg = new BaseRegister("res", floatType);
            MachineOperand resOp = parseOperand(resReg);
            MCFMv fmv = new MCFMv(resOp, regOp, FMV_W_X);
            block.getMachineCodes().add(fmv);
            setDefUse(resOp, fmv);
            setDefUse(regOp, fmv);
            return resOp;
        } else {
            BaseRegister reg = new BaseRegister("li_" + ((Immeidiate) imm).getImmValue(), int32Type);
            MCLi li = new MCLi(reg, imm);
            block.getMachineCodes().add(li);
            setDefUse(reg, li);
            setDefUse(imm, li);
            return reg;
        }
    }

    private boolean isLegalImm(int imm) {
        return imm >= -2048 && imm <= 2047;
    }
}
