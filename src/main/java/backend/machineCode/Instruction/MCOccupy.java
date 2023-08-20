package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCOccupy extends MachineCode {
    private  MachineOperand dest;

    public MCOccupy(MachineOperand dest) {
        this.dest = dest;
        this.addDef(dest);
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
        return "";
    }
}
