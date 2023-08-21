package backend.machineCode.Instruction;

import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import static backend.machineCode.MachineConstants.BNE;
import static backend.machineCode.MachineConstants.J;

public class MCJump extends MachineCode {
    String label;
    MachineOperand lCmp;
    MachineOperand rCmp;

    String type;

    public MCJump(String label) {
        this.label = label;
        this.type = J;
    }

    public MCJump(MachineOperand lCmp, MachineOperand rCmp, String lable){
        this.lCmp = lCmp;
        this.rCmp = rCmp;
        this.addUse(lCmp);
        this.addUse(rCmp);
        this.label = lable;
        this.type = BNE;
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
    public boolean isUselessCode() {
        return false;
    }

    @Override
    public String toString(){
        if(type.equals(J)) {
            return "j\t" + label;
        }else{
            return "bne\t" + lCmp.getRegister() + ", " + rCmp.getRegister() + ", " + label;
        }
    }

    public String getType() {
        return type;
    }
}
