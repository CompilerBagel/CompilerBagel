package backend;

import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineFunction;
import backend.machineCode.MachineOperand;
import backend.reg.PhysicsReg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static backend.reg.PhysicsReg.*;

public class RegisterAllocate {
    private static final int K = 14; // number of colors
    private List<MachineFunction> functions;
    private boolean[][] graph;

    private final HashMap<MachineOperand, PhysicsReg> allocatedReg = new HashMap<>();

    public RegisterAllocate(List<MachineFunction> functions) {
        this.functions = functions;
    }

    public void allocate() {
        // TODO:
    }

    public void easyAllocate() {
        for (MachineFunction function : functions) {
            funcEasyAllocate(function);
        }
    }

    private void funcEasyAllocate(MachineFunction function) {
        LinkedList<MachineBlock> blocks = function.getMachineBlocks();

        for (MachineBlock block : blocks) {
            List<MachineCode> codes = block.getMachineCodes();
            for (MachineCode code : codes) {

            }
        }
    }

    private PhysicsReg getReg(MachineOperand operand) {
        PhysicsReg reg = allocatedReg.get(operand);
        if (reg != null) {
            return reg;
        }
        // allocate a0~a7
        for (int i = 10; i <= 17; i++) {
            if (isAvailableReg(i)) {
                reg = getPhysicReg(i);
                allocatedReg.put(operand, reg);
                return reg;
            }
        }
        return null; // spill
    }

}
