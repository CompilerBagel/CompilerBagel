package backend.machineCode;

import backend.machineCode.Instruction.MCBinaryInteger;
import backend.machineCode.Instruction.MCLi;
import backend.machineCode.Instruction.MCLoad;
import backend.machineCode.Instruction.MCStore;
import backend.post.reg.PhysicsReg;

import java.util.*;

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
    private int overflow = 0;
    private int alignSize = 0;
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
    public void setOverflow(int overflow) {
        this.overflow = overflow;
    }
    public int getOverflow() {
        return overflow;
    }
    public void setAlignSize(int alignSize) {
        this.alignSize = alignSize;
    }
    // block
    public void addMachineBlock(MachineBlock block) {
        machineBlocks.add(block);
    }
    public LinkedList<MachineBlock> getMachineBlocks() {
        return machineBlocks;
    }
    public void allocate() {
        if(allocateList.size() != 0)
            return;
        if (frameSize < 2048) {
            allocateList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(-frameSize), ADDI));;
            allocateList.add(new MCStore(PhysicsReg.getRaReg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize - 8), SD));
            allocateList.add(new MCStore(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize - 16), SD));
            allocateList.add(new MCBinaryInteger(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize), ADDI));
            this.getEntryBlock().addInstrsAtHead(allocateList);
        } else {
            allocateList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(-20), ADDI));;
            allocateList.add(new MCStore(PhysicsReg.getRaReg(), PhysicsReg.getSpReg(), new Immeidiate(12), SD));
            allocateList.add(new MCStore(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(4), SD));
            allocateList.add(new MCBinaryInteger(PhysicsReg.getS0Reg(), PhysicsReg.getSpReg(), new Immeidiate(20), ADDI));

            int calleeSize = frameSize - alignSize - 20;
            allocateList.add(new MCLi(PhysicsReg.getPhysicsReg(5), new Immeidiate(-calleeSize)));
            allocateList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), PhysicsReg.getPhysicsReg(5), ADD));

            allocateList.add(new MCLi(PhysicsReg.getPhysicsReg(5), new Immeidiate(-alignSize)));
            allocateList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), PhysicsReg.getPhysicsReg(5), ADD));

            this.getEntryBlock().addInstrsAtHead(allocateList);
        }
    }
    public void restore(List<MachineBlock> retBlocks, boolean isIntOrFloat) {
        if(restoreList.size() != 0)
            return;
        if (frameSize < 2048) {
            restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getRaReg(), new Immeidiate(frameSize - 8), LD));
            restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getS0Reg(), new Immeidiate(frameSize - 16), LD));
            restoreList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(frameSize), ADDI));;
            for (MachineBlock retBlock : retBlocks.stream().distinct().toList()) {
                retBlock.addInstrsBeforeLast(restoreList, isIntOrFloat);
            }
        } else {
            restoreList.add(new MCLi(PhysicsReg.getPhysicsReg(5), new Immeidiate(alignSize)));
            restoreList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), PhysicsReg.getPhysicsReg(5), ADD));

            int calleeSize = frameSize - alignSize - 20;
            restoreList.add(new MCLi(PhysicsReg.getPhysicsReg(5), new Immeidiate(calleeSize)));
            restoreList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), PhysicsReg.getPhysicsReg(5), ADD));

            restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getRaReg(), new Immeidiate(12), LD));
            restoreList.add(new MCLoad(PhysicsReg.getSpReg(), PhysicsReg.getS0Reg(), new Immeidiate(4), LD));
            restoreList.add(new MCBinaryInteger(PhysicsReg.getSpReg(), PhysicsReg.getSpReg(), new Immeidiate(20), ADDI));;

            for (MachineBlock retBlock : retBlocks.stream().distinct().toList()) {
                retBlock.addInstrsBeforeLast(restoreList, isIntOrFloat);
            }
        }

    }
    public MachineBlock getEntryBlock() {
        return machineBlocks.getFirst();
    }
}
