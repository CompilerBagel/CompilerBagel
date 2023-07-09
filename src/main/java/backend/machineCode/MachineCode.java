package backend.machineCode;

import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class MachineCode {
    // TODO: refactor according to the IR
    public enum InstructionName {
        // jump
        LUI,
        JAL,
        JALR,
        //branch
        BEQ,
        BNE,
        BLT,
        BGE,
        BLTU,
        BGEU,
        // load and store
        LW,
        SW,
        // arithmetic and logic
        SLTI,
        SLTIU,
        ANDI,
        SLLI,
        SRLI,
        SRAI,
        ADD,
        SUB,
        SLL,
        SLT,
        SLTU,
        XOR,
        SRA,
        OR,
        AND,
        // TODO: float to be discussed
        FADD,
        FSUB,
    }
    
    private InstructionName op;
    // TODO: to be discussed according to the optimization
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
    
    // register allocation
    public abstract ArrayList<MachineOperand> allocatePhyRegs();
}
