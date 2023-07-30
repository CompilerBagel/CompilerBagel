package backend.machineCode;


import java.util.ArrayList;

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
    public void addDef(MachineOperand operand) {
        def.add(operand);
    }
    public void addUse(MachineOperand operand) {
        use.add(operand);
    }
    public ArrayList<MachineOperand> getDef() {
        return def;
    }
    public ArrayList<MachineOperand> getUse() {
        return use;
    }
    // optimization for operand
    public abstract void replaceDef(MachineOperand oldOperand, MachineOperand newOperand);
    public abstract void replaceUse(MachineOperand oldOperand, MachineOperand newOperand);
}
