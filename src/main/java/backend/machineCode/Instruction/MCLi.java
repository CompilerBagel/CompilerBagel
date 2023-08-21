package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCLi extends MachineCode {
    private MachineOperand dest;
    private MachineOperand imm;
    public MCLi(MachineOperand dest, MachineOperand imm){
        this.dest = dest;
        this.imm = imm;
        this.addDef(dest);
        this.addUse(imm);
    }

    @Override
    public void replaceDef(MachineOperand oldOperand, MachineOperand newOperand){
        if (oldOperand.equals(this.dest)) {
            this.dest = newOperand;
        }
    }

    @Override
    public void replaceUse(MachineOperand oldOperand, MachineOperand newOperand){
        if (oldOperand.equals(this.imm)) {
            this.imm = newOperand;
        }
    }

    @Override
    public boolean isUselessCode() {
        return false;
    }

    @Override
    public String toString(){
        return "li " + dest.getRegister() + ", " + imm.getRegister();
    }
}
