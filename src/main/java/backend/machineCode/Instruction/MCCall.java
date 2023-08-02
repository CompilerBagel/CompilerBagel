package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineFunction;
import backend.machineCode.MachineOperand;
import backend.reg.PhysicsReg;

import java.util.ArrayList;
import java.util.List;

public class MCCall extends MachineCode {
    private MachineFunction function;
    private List<MachineOperand> params;
    
    public MCCall(MachineFunction function, List<MachineOperand> params) {
        this.function = function;
        this.params = params;
        for (MachineOperand param : params) {
            this.addUse(param);
        }
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

    @Override
    public boolean isUselessCode() {
        return false;
    }
}
