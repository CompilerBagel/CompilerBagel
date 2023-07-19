package backend.machineCode;

import IRBuilder.IRConstants;

import java.util.ArrayList;

import static backend.machineCode.MachineConstants.*;

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
        String ret = binaryOp + " " + dest.toString() + ", " + left.toString() + ", " + right.toString();
        return ret;
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


}
