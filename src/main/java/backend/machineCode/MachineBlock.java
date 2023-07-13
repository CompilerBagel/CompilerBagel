package backend.machineCode;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MachineBlock {
    private String blockName;
    private MachineFunction blockFunc;
    
    private MachineCode startCode;
    private MachineCode endCode;
    private boolean isEntryBlock; // 是否为入口基本块
    private boolean isExitBlock; // 是否为出口基本块
    
    private ArrayList<MachineBlock> predList = new ArrayList<>(); // 前驱列表
    private MachineBlock trueSucc;
    private MachineBlock falseSucc;
}
