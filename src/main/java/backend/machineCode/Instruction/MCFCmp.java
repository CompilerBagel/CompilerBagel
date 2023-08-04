package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCFCmp extends MachineCode {
    private final MachineOperand dest;   // rd
    private final MachineOperand left;   // rs1
    private final MachineOperand right;  // rs2
    private final String fCmpOp;  // FLE, FLT, FEQ

    public MCFCmp(MachineOperand dest, MachineOperand left, MachineOperand right, String fCmpOp) {
        this.dest = dest;
        this.left = left;
        this.right = right;
        this.fCmpOp = fCmpOp;
        this.addDef(dest);
        this.addUse(left);
        this.addUse(right);
    }

    @Override
    public void replaceDef(MachineOperand oldOperand, MachineOperand newOperand) {

    }

    @Override
    public void replaceUse(MachineOperand oldOperand, MachineOperand newOperand) {

    }

    @Override
    public boolean isUselessCode() {
        return false;
    }

    /**
     * fle.s rd, rs1, rs2
     * if rs1 <= rs2 then rd = 1 else rd = 0
     * <p>
     * flt.s rd, rs1, rs2
     * if rs1 < rs2 then rd = 1 else rd = 0
     * <p>
     * feq.s rd, rs1, rs2
     * if rs1 == rs2 then rd = 1 else rd = 0
     * @return int
     */
    @Override
    public String toString() {
        return fCmpOp + " " + dest.getRegister() + ", " + left.getRegister() + ", " + right.getRegister();
    }
}
