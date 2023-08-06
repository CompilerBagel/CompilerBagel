package backend.machineCode;

import backend.machineCode.Instruction.MCReturn;

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
        /*if(this.machineCodes.size() < 3) {
            return this.machineCodes.addAll(0, machineCodes);
        }
        return this.machineCodes.addAll(this.machineCodes.size() - 3, machineCodes);*/
        for (int i = this.machineCodes.size() - 1; i >= 0; i --) {
            if (this.machineCodes.get(i) instanceof MCReturn) {
                if (i == 0) {
                    return this.machineCodes.addAll(0, machineCodes);
                }
                if (i == this.machineCodes.size() - 1 && !(this.machineCodes.get(i - 2) instanceof MCReturn)) {
                    return this.machineCodes.addAll(this.machineCodes.size() - 1, machineCodes);
                }
                if (this.machineCodes.get(i - 1) instanceof MCReturn) {
                    return this.machineCodes.addAll(i - 1, machineCodes);
                }
                if (this.machineCodes.get(i - 2) instanceof MCReturn) {
                    return this.machineCodes.addAll(i - 2, machineCodes);
                }
            }
        }
        return false;
    }
}
