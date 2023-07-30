package backend.machineCode.Instruction;

import backend.machineCode.Immeidiate;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCStore extends MachineCode {
    private MachineOperand src;
    private MachineOperand offset;
    private MachineOperand dest;
    private final String storeOp;

    public MCStore(MachineOperand src, MachineOperand dest, String storeOp) {
        this.src = src;
        this.dest = dest;
        this.storeOp = storeOp;
        this.offset = new Immeidiate(0);
        this.addDef(dest);
        this.addUse(src);
    }
    
    public MCStore(MachineOperand src, MachineOperand dest, MachineOperand offset, String storeOp) {
        this.src = src;
        this.dest = dest;
        this.offset = offset;
        this.storeOp = storeOp;
        this.addDef(dest);
        this.addUse(src);
        this.addUse(offset);
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
        return storeOp + " " + src.getRegister() + ", " + offset.getRegister() + "(" + dest.getRegister() + ")";
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
