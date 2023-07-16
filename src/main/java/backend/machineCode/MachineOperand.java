package backend.machineCode;

import lombok.Data;

@Data
public class MachineOperand {
    public enum operandType {
        imm, // 立即数
        virtualReg, // 虚拟寄存器
        physicsReg, // 物理寄存器
    }
    
    private operandType t;
    private int immValue;
    private float immFloatValue;
    private boolean isImmFloat = false;
    
    public MachineOperand(operandType t) { this.t = t;}
    public MachineOperand(int immValue) {
        this.t = operandType.imm;
        this.immValue = immValue;
    }
    
    // type recognition
    public boolean isImm() { return t == operandType.imm;}
    public boolean isVirtualReg() { return t == operandType.virtualReg;}
    public boolean isPhysicsReg() { return t == operandType.physicsReg;}
    
    // register allocation
    // TODO: color graph algorithm
    private double weight = 0.0;
    private boolean isSpilled = false; // 判断是否已经溢出
    
    public void addWeight(double w) { this.weight += w; }
}
