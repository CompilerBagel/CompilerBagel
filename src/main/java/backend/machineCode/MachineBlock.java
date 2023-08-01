package backend.machineCode;

import java.util.ArrayList;
import java.util.List;

public class MachineBlock {
    private String blockName;
    private MachineFunction blockFunc;
    private List<MachineCode> machineCodes;

    private List<MachineBlock> predList = new ArrayList<>(); // 前驱列表
    private MachineBlock trueSucc;
    private MachineBlock falseSucc;
    
    public MachineBlock(String blockName, MachineFunction blockFunc) {
        this.blockName = blockName;
        this.blockFunc = blockFunc;
        machineCodes = new ArrayList<>();
    }

    public List<MachineBlock> getPredList() {
        return predList;
    }

    public MachineBlock getTrueSucc() {
        return trueSucc;
    }
    public MachineBlock getFalseSucc() {
        return falseSucc;
    }
    public List<MachineCode> getMachineCodes() {
        return machineCodes;
    }
    public String getBlockName() {
        return blockName;
    }
    public MachineFunction getBlockFunc() {
        return blockFunc;
    }
    public boolean addInstrsAtHead(List<MachineCode> machineCodes) {
        return this.machineCodes.addAll(0, machineCodes);
    }
    public boolean addInstrsBeforeLast(List<MachineCode> machineCodes) {
        return this.machineCodes.addAll(this.machineCodes.size() - 4, machineCodes);
    }
}
