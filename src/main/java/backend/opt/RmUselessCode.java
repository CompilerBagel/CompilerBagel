package backend.opt;

import backend.machineCode.*;
import backend.machineCode.Instruction.MCLoad;
import backend.machineCode.Instruction.MCReturn;
import backend.machineCode.Instruction.MCStore;
import backend.post.reg.PhysicsReg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RmUselessCode {
    private final List<MachineFunction> functions;
    public RmUselessCode(List<MachineFunction> functions) {
        this.functions = functions;
    }

    public void remove() {
        for (MachineFunction function : functions) {
            funcRemove(function);
        }
    }
    public void funcRemove(MachineFunction function) {
        LinkedList<MachineBlock> blocks = function.getMachineBlocks();

        for (MachineBlock block : blocks) {
            List<MachineCode> codes = block.getMachineCodes();
            // Code itself is useless, like mv a0, a0
            codes.removeIf(MachineCode::isUselessCode);

            // Code after ret is useless
            List<MachineCode> removeList = new ArrayList<>();
            Iterator<MachineCode> iterator = codes.iterator();
            MachineCode lastCode = null;
            while(iterator.hasNext()) {
                MachineCode code = iterator.next();
                if(lastCode instanceof MCReturn){
                    removeList.add(code);
                    continue;
                } else if (isRedundancyLS(lastCode, code)){
                    // 1. LD r0, a  2. ST a, R0
                    removeList.add(code);
                    continue;
                }
                lastCode = code;
            }
            codes.removeAll(removeList);
        }

    }

    private boolean isRedundancyLS(MachineCode loadCode, MachineCode storeCode) {
        if (!(loadCode instanceof MCLoad) || !(storeCode instanceof MCStore)) {
            return false;
        }
        PhysicsReg loadReg = getReg(((MCLoad) loadCode).getDest());
        PhysicsReg storeReg = getReg(((MCStore) storeCode).getSrc());

        PhysicsReg loadBaseReg = getReg(((MCLoad) loadCode).getSrc());
        PhysicsReg storeBaseReg = getReg(((MCStore) storeCode).getDest());

        Immeidiate loadImm = (Immeidiate) ((MCLoad) loadCode).getOffset();
        Immeidiate storeImm = (Immeidiate) ((MCStore) storeCode).getOffset();

        return loadReg == storeReg && loadBaseReg == storeBaseReg
                && loadImm.getImmValue() == storeImm.getImmValue();
    }

    private PhysicsReg getReg(MachineOperand op) {
        if (op instanceof PhysicsReg) {
            return (PhysicsReg) op;
        }
        return op.getPhysicsReg();
    }

}
