package backend.machineCode.Instruction;

import IRBuilder.IRConstants;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import java.util.ArrayList;

import static backend.machineCode.MachineConstants.*;

public class MCBinaryInteger extends MachineCode {
    private MachineOperand dest;
    private MachineOperand left;
    private MachineOperand right;
    private final String binaryOp;
    
    public MCBinaryInteger(MachineOperand dest, MachineOperand left, MachineOperand right, String binaryOp) {
        this.dest = dest;
        this.left = left;
        this.right = right;
        this.addDef(dest);
        this.addUse(left);
        this.addUse(right);
        this.binaryOp = binaryOp;
    }
    
    /**
     * add, sub, mul, div
     * @return str
     */
    @Override
    public String toString() {
        return binaryOp + " " + dest.getRegister() + ", " + left.getRegister() + ", " + right.getRegister();
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
    public boolean isUselessCode() {
        return false;
    }
}
