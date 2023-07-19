package backend.machineCode;

import java.util.ArrayList;

public class MCMove extends MachineCode {
    private MachineOperand src;
    private MachineOperand dest;
    
    public MCMove(MachineOperand src, MachineOperand dest) {
        this.src = src;
        this.dest = dest;
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
        return "mv " + dest.toString() + ", " + src.toString();
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
    public ArrayList<MachineOperand> allocatePhyRegs() {
        ArrayList<MachineOperand> phyRegs = new ArrayList<>();
/*        if (this.src.isPhysicsReg()) {
            phyRegs.add(this.src);
        }
        if (this.dest.isPhysicsReg()) {
            phyRegs.add(this.dest);
        }*/
        return phyRegs;
    }
}
