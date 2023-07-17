package backend.machineCode;

import IRBuilder.IRConstants;

import java.util.ArrayList;

public class MCBranch extends MachineCode {
    private MachineBlock target;
    private String cond;
    
    public MCBranch(MachineBlock target, String cond) {
        this.target = target;
        this.cond = cond;
    }
    
    @Override
    public String toString() {
        return "b" + cond + "Block_" + target.getBlockName() + "_" + target.getBlockFunc().getFuncName();
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
    
    @Override
    public ArrayList<MachineOperand> allocatePhyRegs() {
        return null;
    }
}