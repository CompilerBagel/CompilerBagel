package backend.machineCode;

import backend.reg.PhysicsReg;

import java.util.ArrayList;
import java.util.List;

public class MCCall extends MachineCode {
    private MachineFunction function;
    private List<MachineOperand> params;
    
    public MCCall(MachineFunction function, List<MachineOperand> params) {
        this.function = function;
        this.params = params;
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
