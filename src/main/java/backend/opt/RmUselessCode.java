package backend.opt;

import backend.machineCode.*;
import backend.machineCode.Instruction.*;
import backend.post.reg.PhysicsReg;

import java.util.*;

import static backend.machineCode.MachineConstants.*;

public class RmUselessCode {
    private final List<MachineFunction> functions;

    public RmUselessCode(List<MachineFunction> functions) {
        this.functions = functions;
    }


    static class StoreQueue {
        List<MachineCode> rmList;
        MachineCode reserveCode;

        StoreQueue() {
            rmList = new ArrayList<>();
            reserveCode = null;
        }

        void push(MachineCode nowCode) {
            if (reserveCode != null) {
                rmList.add(reserveCode);
            }
            reserveCode = nowCode;
        }

        void clear() {
            rmList.clear();
            reserveCode = null;
        }

        List<MachineCode> getRmList() {
            return rmList;
        }
    }

    private HashMap<Integer, StoreQueue> addrStoreCodes;

    /**
     * remove useless codes in all functions
     */
    public void remove() {
        for (MachineFunction function : functions) {
            funcRemove(function);
        }
    }

    /**
     * remove useless in function
     *
     * @param function the MachineFunction to be dealt
     */
    public void funcRemove(MachineFunction function) {
        funcRmUselessStore(function);

        LinkedList<MachineBlock> blocks = function.getMachineBlocks();

        for (MachineBlock block : blocks) {
            List<MachineCode> codes = block.getMachineCodes();
            // Code itself is useless, like mv a0, a0
            codes.removeIf(MachineCode::isUselessCode);

            // Code after ret is useless
            List<MachineCode> removeList = new ArrayList<>();
            Iterator<MachineCode> iterator = codes.iterator();
            MachineCode lastCode = null;
            while (iterator.hasNext()) {
                MachineCode code = iterator.next();
                if (lastCode instanceof MCReturn) {
                    removeList.add(code);
                    continue;
                } else if (lastCode instanceof MCJump && ((MCJump) lastCode).getType().equals(J)){
                    removeList.add(code);
                    continue;
                }
                else if (isRedundancyLS(lastCode, code)) {
                    // 1. LD r0, a  2. ST a, R0
                    removeList.add(code);
                    continue;
                } else if (lastCode instanceof MCLi && code instanceof MCLi) {
                    PhysicsReg lastCodeReg = getReg(((MCLi) lastCode).getDest());
                    PhysicsReg codeReg = getReg(((MCLi) code).getDest());
                    if (lastCodeReg == codeReg) {
                        removeList.add(lastCode);
                    }
                }
                lastCode = code;
            }
            codes.removeAll(removeList);
        }
    }

    private void funcRmUselessStore(MachineFunction function) {
        LinkedList<MachineBlock> blocks = function.getMachineBlocks();

        for (MachineBlock block : blocks) {
            addrStoreCodes = new HashMap<>();
            List<MachineCode> codes = block.getMachineCodes();
            List<MachineCode> removeList = new ArrayList<>();
            for (MachineCode code : codes) {
                // SW in stack space
                if (code instanceof MCStore
                        && getReg(((MCStore) code).getDest()) == PhysicsReg.getS0Reg()
                        && ((MCStore) code).getStoreOp().equals(SW)) {
                    int address = ((Immeidiate) ((MCStore) code).getOffset()).getImmValue();
                    StoreQueue storeQueue = addrStoreCodes.get(address);
                    if (storeQueue == null) {
                        storeQueue = new StoreQueue();
                        addrStoreCodes.put(address, storeQueue);
                    }
                    storeQueue.push(code);
                } else if (code instanceof MCLoad
                        && getReg(((MCLoad) code).getSrc()) == PhysicsReg.getS0Reg()
                        && ((MCLoad) code).getLoadOp().equals(LW)) {
                    int address = ((Immeidiate) ((MCLoad) code).getOffset()).getImmValue();
                    StoreQueue storeQueue = addrStoreCodes.get(address);
                    if (storeQueue == null) continue;
                    removeList.addAll(storeQueue.getRmList());
                    storeQueue.clear();
                }
            }
            codes.removeAll(removeList);
            for (StoreQueue storeQueue : addrStoreCodes.values()) {
                if (storeQueue != null && storeQueue.rmList.size() != 0) {
                    codes.removeAll(storeQueue.rmList);
                    storeQueue.rmList.clear();
                }
            }
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

    /**
     * Get the PhysicsReg allocated to the MachineOperand
     *
     * @param op MachineOperand Object
     * @return allocated PhysicsReg
     */
    private PhysicsReg getReg(MachineOperand op) {
        if (op instanceof PhysicsReg) {
            return (PhysicsReg) op;
        }
        return op.getPhysicsReg();
    }

}
