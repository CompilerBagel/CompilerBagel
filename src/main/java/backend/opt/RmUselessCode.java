package backend.opt;

import backend.machineCode.Instruction.MCReturn;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineFunction;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static backend.reg.PhysicsReg.giveBackRegA;

public class RmUselessCode {
    private List<MachineFunction> functions;

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
            Iterator<MachineCode> iterator = codes.iterator();
            MachineCode lastCode = null;
            while(iterator.hasNext()) {
                MachineCode code = iterator.next();
                if(lastCode instanceof MCReturn){
                    iterator.remove();
                    continue;
                }
                lastCode = code;
            }
        }

    }

}
