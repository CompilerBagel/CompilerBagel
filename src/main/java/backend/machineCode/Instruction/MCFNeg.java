package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCFNeg extends MachineCode {
    private final MachineOperand src;
    private final MachineOperand dest;

    public MCFNeg(MachineOperand dest, MachineOperand src) {
        this.src = src;
        this.dest = dest;
        def.add(dest);
        use.add(src);
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

    @Override
    public String toString() {
        return "fneg.s " + dest.getRegister() + ", " + src.getRegister();
    }
}
