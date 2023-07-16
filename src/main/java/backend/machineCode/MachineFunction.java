package backend.machineCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MachineFunction {
    // TODO: data structure to be discussed
    private LinkedList<MachineBlock> machineBlocks;
    private String funcName;
    private ArrayList<MachineOperand> savedRegs;
    private ArrayList<MachineCode> argList;
    private HashMap<MachineCode, MachineCode> argMoveMap; // register change when function call
    
    public MachineFunction(String funcName) {
        this.funcName = funcName;
        machineBlocks = new LinkedList<>();
        savedRegs = new ArrayList<>();
        argList = new ArrayList<>();
        argMoveMap = new HashMap<>();
    }
    
    public String getFuncName() {
        return funcName;
    }
    
    public void initSavedRegs() { savedRegs.clear();}
    
    // stack
    private int frameSize; // 栈帧大小
    
    public void moveFrame(int size) { frameSize += size; }
}
