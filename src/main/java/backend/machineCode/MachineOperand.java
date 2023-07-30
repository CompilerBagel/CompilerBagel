package backend.machineCode;

import backend.reg.PhysicsReg;

import java.util.ArrayList;
import java.util.List;

public class MachineOperand {
    public enum operandType {
        label,
        imm, // 立即数
        virtualReg, // 虚拟寄存器
        physicsReg // 物理寄存器
    }
    private operandType t;
    private final String identity;
    private PhysicsReg physicsReg = null;
    private boolean isDef = false;
    private MachineCode def = null;
    private List<MachineCode> useList = new ArrayList<>();

    public MachineOperand(operandType t, String identity) {
        this.t = t;
        this.identity = identity;
    }
    
    // type recognition
    public boolean isImm() { return t == operandType.imm;}
    public boolean isVirtualReg() { return t == operandType.virtualReg;}
    public boolean isPhysicsReg() { return t == operandType.physicsReg;}

    //todo: register allocation
    public void setPhysicsReg(PhysicsReg physicsReg) {
        this.physicsReg = physicsReg;
        this.t = operandType.virtualReg;
    }
    public PhysicsReg getPhysicsReg() {
        return physicsReg;
    }
    public void setDef(MachineCode def) {
        this.def = def;
        isDef = true;
    }
    public void addUse(MachineCode use) { this.useList.add(use); }
    public void removeUse(MachineCode use) {this.useList.remove(use);}
    public void replaceDef(MachineCode oldDef, MachineCode newDef) {
        assert(def == oldDef);
        def = newDef;
    }
    public boolean noUser() {
        return useList.size() == 0;
    }
    public void replaceUse(MachineCode oldUse, MachineCode newUse) {
        for (int i = 0; i < useList.size(); i++) {
            // find the oldUse in useList
            if (useList.get(i) == oldUse) {
                useList.set(i, newUse);
                return;
            }
        }
        assert(false);
    }
    public boolean getIsDef() { return isDef; }
    // register allocation
    private double weight = 0.0;
    private boolean isSpilled = false; // 判断是否已经溢出
    public void addWeight(double w) { this.weight += w; }
    public String getIdentity(){
        return identity;
    }

    public String toString(){
        if(physicsReg == null)
            return identity;
        else
            return physicsReg.toString();
    }

    public String getRegister(){
        if(physicsReg == null)
            return identity;
        else
            return physicsReg.toString();
    }
}
