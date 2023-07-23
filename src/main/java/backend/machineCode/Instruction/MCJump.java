package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCJump extends MachineCode {
    String label;

    public MCJump(String label) {
        this.label = label;
    }

    @Override
    public void replaceDef(MachineOperand oldOperand, MachineOperand newOperand) {
        ;
    }

    @Override
    public void replaceUse(MachineOperand oldOperand, MachineOperand newOperand) {
        ;
    }

    @Override
    public String toString(){
        return "j\t" + label;
    }
}
