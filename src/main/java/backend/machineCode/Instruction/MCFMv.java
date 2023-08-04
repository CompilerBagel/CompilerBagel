package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCFMv extends MachineCode {
    private final MachineOperand src;
    private final MachineOperand dest;
    private final String fMvOp;

    public MCFMv(MachineOperand dest, MachineOperand src, String fMvOp) {
        this.src = src;
        this.dest = dest;
        this.fMvOp = fMvOp;
        use.add(src);
        def.add(dest);
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
        return fMvOp + " " + dest.getRegister() + ", " + src.getRegister();
    }
}
