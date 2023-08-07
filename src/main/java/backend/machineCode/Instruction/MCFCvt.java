package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCFCvt extends MachineCode {
    private final MachineOperand src;
    private final MachineOperand dest;
    private final String fCvtOp;

    public MCFCvt(MachineOperand dest, MachineOperand src, String fCvtOp) {
        this.dest = dest;
        this.src = src;
        this.fCvtOp = fCvtOp;
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
        return fCvtOp + " " + dest.getRegister() + ", " + src.getRegister();
    }
}
