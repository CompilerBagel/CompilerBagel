package backend.machineCode;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Data
public class MachineFunction {
    // TODO: data structure to be discussed
    private LinkedList<MachineBlock> machineBlocks;
    private String funcName;
    private ArrayList<MachineOperand> savedRegs;
    private ArrayList<MachineCode> argList;
    private HashMap<MachineCode, MachineCode> argMoveMap; // register change when function call
    
    public void initSavedRegs() { savedRegs.clear();}
    
    // stack
    private int frameSize; // 栈帧大小
    
    public void moveFrame(int size) { frameSize += size; }
}
