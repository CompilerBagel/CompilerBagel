package backend.machineCode;

import instruction.Instruction;

import java.util.*;

public class MachineFunction {

    // TODO: data structure to be discussed
    private LinkedList<MachineBlock> machineBlocks;
    private String funcName;
    private final ArrayList<MachineOperand> savedRegs;
    private ArrayList<MachineCode> argList;
    private HashMap<MachineCode, MachineCode> argMoveMap; // register change when function call
    private Map<String, Integer> offsetMap;
    private List<MachineCode> preList;
    
    public MachineFunction(String funcName) {
        this.funcName = funcName;
        machineBlocks = new LinkedList<>();
        savedRegs = new ArrayList<>();
        argList = new ArrayList<>();
        argMoveMap = new HashMap<>();
        offsetMap = new HashMap<>();
        preList = new ArrayList<>();
    }
    
    public String getFuncName() {
        return funcName;
    }
    
    public void initSavedRegs() { savedRegs.clear();}
    
    // stack
    private int frameSize; // 栈帧大小
    
    public void setFrameSize(int size) {
        this.frameSize = size;
    }
    public int getFrameSize() {
        return frameSize;
    }
    public Map<String, Integer> getOffsetMap() {
        return offsetMap;
    }

    public LinkedList<MachineBlock> getMachineBlocks() {
        return machineBlocks;
    }
    public List<MachineCode> getPreList() {
        return preList;
    }
}
