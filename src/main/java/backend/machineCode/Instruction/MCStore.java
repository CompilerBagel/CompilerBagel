package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import java.util.ArrayList;

public class MCStore extends MachineCode {
    private MachineOperand src;
    private MachineOperand offset = new MachineOperand(0);
    private MachineOperand dest;
    private String storeOp;

    public MCStore(MachineOperand src, MachineOperand dest, String storeOp) {
        this.src = src;
        this.dest = dest;
        this.storeOp = storeOp;
        this.offset = new MachineOperand(0);
    }
    
    public MCStore(MachineOperand src, MachineOperand dest, MachineOperand offset, String storeOp) {
        this.src = src;
        this.dest = dest;
        this.offset = offset;
        this.storeOp = storeOp;
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
        return storeOp + " " + dest.toString() + ", " + offset.toString() + "(" + src.toString() + ")";
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
