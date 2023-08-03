package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCSet extends MachineCode {

    private MachineOperand dest;
    private MachineOperand left;
    private MachineOperand right;

    public MCSet(MachineOperand dest, MachineOperand left, MachineOperand right) {
        this.dest = dest;
        this.left = left;
        this.right = right;
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
     * slt rd, rs1, rs2
     * if rs1 < rs2 then rd = 1 else rd = 0
     * @return
     */
    @Override
    public String toString() {
        return "slt " + dest.getRegister() + ", " + left.getRegister() + ", " + right.getRegister();
    }
}
