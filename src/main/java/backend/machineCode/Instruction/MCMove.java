package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import java.util.ArrayList;

public class MCMove extends MachineCode {
    private MachineOperand src;
    private MachineOperand dest;
    
    public MCMove(MachineOperand src, MachineOperand dest) {
        this.src = src;
        this.dest = dest;
        this.addDef(dest);
        this.addUse(src);
    }
    
    /**
     * Move.
     * instruction: mv rd, rs1
     * exec: reg[rd] <= reg[rs1] + 0
     * @return str
     */
    @Override
    public String toString() {
        // TODO: mv conditions
        return "mv " + dest.getRegister() + ", " + src.getRegister();
    }
    
    @Override
    public void initUseAndDef() {
        super.initUseAndDef();
        use.add(src);
        def.add(dest);
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (oldOperand.equals(this.src)) {
            this.src = newOperand;
        }
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (oldOperand.equals(this.dest)) {
            this.dest = newOperand;
        }
    }

    @Override
    public boolean isUselessCode() {
        // moving to the same register is useless, like mv a0, a0
        return dest.getRegister().equals(src.getRegister());
    }

}
