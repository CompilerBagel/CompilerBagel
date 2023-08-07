package backend.machineCode;

import IRBuilder.BaseBlock;
import backend.machineCode.Instruction.MCBinaryInteger;
import backend.machineCode.Instruction.MCLoad;
import backend.machineCode.Instruction.MCStore;
import backend.reg.PhysicsReg;
import instruction.Instruction;

import java.util.*;
import java.util.stream.Collectors;

import static backend.machineCode.MachineConstants.*;

public class MachineFunction {

    private String funcName;
    private LinkedList<MachineBlock> machineBlocks;
    private Map<String, Integer> offsetMap;
    private List<MachineCode> allocateList;
    private List<MachineCode> restoreList;
    
    public MachineFunction(String funcName) {
        this.funcName = funcName;
        machineBlocks = new LinkedList<>();
        offsetMap = new HashMap<>();
        allocateList = new ArrayList<>();
        restoreList = new ArrayList<>();
    }
    public String getFuncName() {
        return funcName;
    }

    // stack
    private int frameSize; // 栈大小
    private int stackCount = 0; // real count
    public int getStackCount() {
        return stackCount;
    }
    public void setStackCount(int count) {
        stackCount = count;
    }
    public void setFrameSize(int size) {
        this.frameSize = size;
    }
    public int getFrameSize() {
        return frameSize;
    }
    public Map<String, Integer> getOffsetMap() {
        return offsetMap;
    }
    // block
    public void addMachineBlock(MachineBlock block) {
        machineBlocks.add(block);
    }
    public LinkedList<MachineBlock> getMachineBlocks() {
        return machineBlocks;
    }
    public void allocate() {
        allocateList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(-frameSize), ADDI));;
        allocateList.add(new MCStore(PhysicsReg.getRaReg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize - 8), SD));
        allocateList.add(new MCStore(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize - 16), SD));
        allocateList.add(new MCBinaryInteger(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize), ADDI));
        this.getEntryBlock().addInstrsAtHead(allocateList);
    }
    public void restore(List<MachineBlock> retBlocks) {
        restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getRaReg(), new Immeidiate(frameSize - 8), LD));
        restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getS0Reg(), new Immeidiate(frameSize - 16), LD));
        restoreList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize), ADDI));;
        for (MachineBlock retBlock : retBlocks.stream().distinct().toList()) {
            retBlock.addInstrsBeforeLast(restoreList);
        }
    }
    public MachineBlock getEntryBlock() {
        return machineBlocks.getFirst();
    }
}
