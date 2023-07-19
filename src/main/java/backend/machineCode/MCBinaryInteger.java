package backend.machineCode;

import IRBuilder.IRConstants;

import java.util.ArrayList;

public class MCBinaryInteger extends MachineCode {
    private MachineOperand dest;
    private MachineOperand left;
    private MachineOperand right;
    private String binaryOp;
    
    public MCBinaryInteger(MachineOperand dest, MachineOperand left, MachineOperand right, String binaryOp) {
        this.dest = dest;
        this.left = left;
        this.right = right;
        this.binaryOp = binaryOp;
    }
    
    /**
     * add, sub, mul, div
     * @return str
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (binaryOp) {
            case IRConstants.ADD:  // add rd, rs1, rs2
                sb.append("add ");
                break;
            case IRConstants.SUB:  // sub rd, rs1, rs2
                sb.append("sub ");
                break;
            case IRConstants.MUL:  // mul rd, rs1, rs2
                sb.append("mul ");
                break;
            case IRConstants.SDIV:  // div rd, rs1, rs2
                sb.append("div ");
                break;
        }
        
        sb.append(dest.toString()).append(", ").append(left.toString()).append(", ").append(right.toString());
        return sb.toString();
    }
    
    @Override
    public void initUseAndDef() {
        super.initUseAndDef();
        def.add(dest);
        use.add(left);
        use.add(right);
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (dest.equals(oldOperand)) {
            dest = newOperand;
        }
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
        if (left.equals(oldOperand)) {
            left = newOperand;
        }
        if (right.equals(oldOperand)) {
            right = newOperand;
        }
    }
    
    @Override
    public ArrayList<MachineOperand> allocatePhyRegs() {
        ArrayList<MachineOperand> phyRegs = new ArrayList<>();
        if (dest.isPhysicsReg()) {
            phyRegs.add(dest);
        }
        if (left.isPhysicsReg()) {
            phyRegs.add(left);
        }
        if (right.isPhysicsReg()) {
            phyRegs.add(right);
        }
        return phyRegs;
    }
}
