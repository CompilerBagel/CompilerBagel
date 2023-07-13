package backend.machineCode;

import java.util.ArrayList;

public class MCLoad extends MachineCode {
    private MachineOperand src;
    private MachineOperand dest;
    private MachineOperand offset = new MachineOperand(0);
    
    public void setOffset(final MachineOperand offset) {
        this.offset = offset;
    }
    
    /**
     * Load Word.
     * instruction: lw rd, offset(rs1)
     * exec: reg[rd] <= mem[reg[rs1] + offset]
     * @return str
     */
    @Override
    public String toString() {
        return "lw " + dest.toString() + ", " + offset.toString() + "(" + src.toString() + ")";
    }
    
    @Override
    public void initUseAndDef() {
        super.initUseAndDef();
        use.add(src);
        def.add(dest);
        if (!offset.isImm()) {
            use.add(offset);
        }
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (oldOperand.equals(this.dest)) {
            this.dest = newOperand;
        }
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (oldOperand.equals(this.src)) {
            this.src = newOperand;
        }
        if (oldOperand.equals(this.offset)) {
            this.offset = newOperand;
        }
    }
    
    @Override
    public ArrayList<MachineOperand> allocatePhyRegs() {
        ArrayList<MachineOperand> phyRegs = new ArrayList<>();
        if (this.src.isPhysicsReg()) {
            phyRegs.add(this.src);
        }
        if (this.dest.isPhysicsReg()) {
            phyRegs.add(this.dest);
        }
        if (this.offset.isPhysicsReg()) {
            phyRegs.add(this.offset);
        }
        return phyRegs;
    }
}
