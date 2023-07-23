package backend.machineCode;

import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class MachineCode {
    private boolean isDead; // 死代码判断
    
    // source operand
    protected ArrayList<MachineOperand> def = new ArrayList<>();
    // target operand
    protected ArrayList<MachineOperand> use = new ArrayList<>();
    public void initUseAndDef() {
        def.clear();
        use.clear();
    }
    // optimization for operand
    public abstract void replaceDef(MachineOperand oldOperand, MachineOperand newOperand);
    public abstract void replaceUse(MachineOperand oldOperand, MachineOperand newOperand);
}
