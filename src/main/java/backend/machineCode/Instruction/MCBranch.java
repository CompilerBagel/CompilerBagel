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
    static int branchNumber = 0;
    private MachineOperand dest;
    private MachineOperand rs1;
    private MachineOperand rs2;
    private String label;

    
    public MCBranch(int type, MachineOperand dest, MachineOperand rs1, MachineOperand rs2) {
        this.type = type;
        this.dest = dest;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.addDef(dest);
        this.addUse(rs1);
        this.addUse(rs2);
        branchNumber ++;
        this.label = "Branch" + String.valueOf(branchNumber);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MCCodes[type] + " " + rs1.getRegister() + ", " + rs2.getRegister() + ", " + label + "\n");
        sb.append("    " + "j\t" + label + "_else\n");

        sb.append(label + ":\n");
        sb.append("    " + "li " + dest.getRegister() + ", 1\n");
        sb.append("    " + "j\t" + label + "_end\n");

        sb.append(label + "_else:\n");
        sb.append("    " + "li " + dest.getRegister() + ", 0\n");

        sb.append(label + "_end:");

        return sb.toString();
    }
    
    @Override
    public void replaceDef(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
    
    @Override
    public void replaceUse(final MachineOperand oldOperand, final MachineOperand newOperand) {
    
    }
}
