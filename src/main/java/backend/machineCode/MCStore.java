package backend.machineCode;

import java.util.ArrayList;

public class MCStore extends MachineCode {
    private MachineOperand src;
    private MachineOperand offset = new MachineOperand(0);
    private MachineOperand dest;

    public MCStore(MachineOperand src, MachineOperand dest) {
        this.src = src;
        this.dest = dest;
    }
    
    public MCStore(MachineOperand src, MachineOperand dest, MachineOperand offset) {
        this.src = src;
        this.dest = dest;
        this.offset = offset;
    }
    public void setOffset(final MachineOperand offset) {
        this.offset = offset;
    }
    public void setSrc(MachineOperand src) {
        this.src = src;
    }
    
    /**
     * Store Word.
     * instruction: sw rs2, offset(rs1)
     * exec: mem[reg[rs1] + offset] <= reg[rs2]
     * @return str
     */
    @Override
    public String toString() {
        return "sw " + src.toString() + ", " + offset.toString() + "(" + dest.toString() + ")";
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
        if (oldOperand.equals(this.offset)) {
            this.offset = newOperand;
        }
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (oldOperand.equals(this.src)) {
            this.src = newOperand;
        }
    }
}
