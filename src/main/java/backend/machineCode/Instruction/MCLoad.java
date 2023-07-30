package backend.machineCode.Instruction;

import backend.machineCode.Immeidiate;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

public class MCLoad extends MachineCode {
    private MachineOperand src;
    private MachineOperand dest;
    private MachineOperand offset;

    public MCLoad(MachineOperand src, MachineOperand dest, MachineOperand offset) {
        this.src = src;
        this.dest = dest;
        this.offset = offset;
        this.addDef(dest);
        this.addUse(src);
        this.addUse(offset);
    }

    public MCLoad(MachineOperand src, MachineOperand dest){
        this.src = src;
        this.dest = dest;
        this.offset = new Immeidiate(0);
    }
    
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
        return "lw " + dest.getRegister() + ", " + offset.getRegister() + "(" + src.getRegister() + ")";
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


}
