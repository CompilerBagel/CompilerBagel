package backend.machineCode.Instruction;

import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import java.util.ArrayList;

public class MCReturn extends MachineCode {
    @Override
    public String toString() {
        return "ret";
    }
    
    @Override
    public void initUseAndDef() {
        super.initUseAndDef();
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
