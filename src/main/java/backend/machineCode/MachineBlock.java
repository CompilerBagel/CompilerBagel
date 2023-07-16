package backend.machineCode;

import java.util.ArrayList;
import java.util.List;

public class MachineBlock {
    private String blockName;
    private MachineFunction blockFunc;
    
    private MachineCode startCode;
    private MachineCode endCode;
    private List<MachineCode> machineCodes;
    private boolean isEntryBlock; // 是否为入口基本块
    private boolean isExitBlock; // 是否为出口基本块
    
    private List<MachineBlock> predList = new ArrayList<>(); // 前驱列表
    private MachineBlock trueSucc;
    private MachineBlock falseSucc;
    
    public MachineBlock(String blockName, MachineFunction blockFunc) {
        this.blockName = blockName;
        this.blockFunc = blockFunc;
    }
    
    public MachineBlock getTrueSucc() {
        return trueSucc;
    }
    
    public MachineBlock getFalseSucc() {
        return falseSucc;
    }
    
    public List<MachineBlock> getPredList() {
        return predList;
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
}
