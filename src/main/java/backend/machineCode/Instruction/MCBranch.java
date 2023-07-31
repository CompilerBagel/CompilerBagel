package backend.machineCode.Instruction;

import IRBuilder.IRConstants;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;

import java.util.ArrayList;

import static backend.machineCode.MachineConstants.MCCodes;

/**
 * beq, bne, blt, bge, bltu, bgeu
 * type rs1, rs2, label
 */
public class MCBranch extends MachineCode {
    private int type;
    private MachineOperand rs1;
    private MachineOperand rs2;
    private String label;

    public MCBranch(int type, MachineOperand rs1, MachineOperand rs2, String label) {
        this.type = type;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.label = label;
        this.addUse(rs1);
        this.addUse(rs2);
    }
    
    @Override
    public String toString() {
        return MCCodes[type] + " " + rs1.getRegister() + ", " + rs2.getRegister() + ", " + label;
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
}
