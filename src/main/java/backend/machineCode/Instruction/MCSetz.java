package backend.machineCode.Instruction;

import IRBuilder.IRConstants;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCSetz extends MachineCode {

    private MachineOperand dest;
    private MachineOperand rs;
    private int type;

    public MCSetz(MachineOperand dest, MachineOperand rs, int type) {
        this.dest = dest;
        this.rs = rs;
        this.type = type;
        this.addDef(dest);
        this.addUse(rs);
    }

    @Override
    public void replaceDef(MachineOperand oldOperand, MachineOperand newOperand) {

    }

    @Override
    public void replaceUse(MachineOperand oldOperand, MachineOperand newOperand) {

    }

    @Override
    public String toString() {
        if (type == IRConstants.IRIntEQ) {
            return "seqz " + dest.getRegister() + ", " + rs.getRegister();
        } else {
            return "snez " + dest.getRegister() + ", " + rs.getRegister();
        }
    }
}
