package backend.machineCode;

import backend.reg.PhysicsReg;

import java.util.ArrayList;

public class MCCall extends MachineCode {
    private MachineFunction function;
    
    public MCCall(MachineFunction function) {
        this.function = function;
    }
    
    @Override
    public String toString() {
        return "call " + function.getFuncName();
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
}
