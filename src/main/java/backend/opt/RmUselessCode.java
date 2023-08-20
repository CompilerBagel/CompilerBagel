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
        //funcRmUselessStore(function);

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
                } else if (lastCode instanceof MCJump && ((MCJump) lastCode).getType().equals(J)) {
                    removeList.add(code);
                    continue;
                } else if (isRedundancyLS(lastCode, code)) {
                    // 1. LD r0, a  2. ST a, R0
                    removeList.add(code);
                    continue;
                } else if (isRedundancyLoad(lastCode, code)) {
                    // 1. ST a, R0 2. LD R0, a
                    removeList.add(code);
                    continue;
                } else if (isRepeatCode(lastCode, code)) {
                    removeList.add(lastCode);
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

    private boolean isSameRegAndAddress(MachineCode loadCode, MachineCode storeCode) {
        PhysicsReg loadReg = getReg(((MCLoad) loadCode).getDest());
        PhysicsReg storeReg = getReg(((MCStore) storeCode).getSrc());

        PhysicsReg loadBaseReg = getReg(((MCLoad) loadCode).getSrc());
        PhysicsReg storeBaseReg = getReg(((MCStore) storeCode).getDest());

        Immeidiate loadImm = (Immeidiate) ((MCLoad) loadCode).getOffset();
        Immeidiate storeImm = (Immeidiate) ((MCStore) storeCode).getOffset();

        return loadReg == storeReg && loadBaseReg == storeBaseReg
                && loadImm.getImmValue() == storeImm.getImmValue();
    }

    /** cases like:
     *     LD a , r0
     *     ST r0, a
     * @return if loadCode can be removed, return true
     */
    private boolean isRedundancyLS(MachineCode loadCode, MachineCode storeCode) {
        if (!(loadCode instanceof MCLoad) || !(storeCode instanceof MCStore)) {
            return false;
        }
        return isSameRegAndAddress(loadCode, storeCode);
    }

    /** cases like:
     *     ST r0, a
     *     LD a , r0
     * @return if loadCode can be removed, return true
     */
    private boolean isRedundancyLoad(MachineCode storeCode, MachineCode loadCode) {
        if (!(loadCode instanceof MCLoad) || !(storeCode instanceof MCStore)) {
            return false;
        }
        return isSameRegAndAddress(loadCode, storeCode);
    }

    /**
     * two adjacent code may repeat like `li a0, 1;  li a0, 2`
     * @param firstCode first MachineCode
     * @param secondCode second MachineCode
     * @return whether two code repeat, if repeat return true
     */
    private boolean isRepeatCode(MachineCode firstCode, MachineCode secondCode) {
        if (firstCode instanceof MCLoad && secondCode instanceof MCLoad) {
            PhysicsReg firstReg = getReg(((MCLoad) firstCode).getDest());
            PhysicsReg secondReg = getReg(((MCLoad) secondCode).getDest());
            return firstReg == secondReg;
        } else if (firstCode instanceof MCStore && secondCode instanceof MCStore) {
            PhysicsReg firstBaseReg = getReg(((MCStore) firstCode).getSrc());
            PhysicsReg secondBaseReg = getReg(((MCStore) secondCode).getSrc());

            Immeidiate firstImm = (Immeidiate) ((MCStore) firstCode).getOffset();
            Immeidiate secondImm = (Immeidiate) ((MCStore) secondCode).getOffset();
            return firstBaseReg == secondBaseReg
                    && firstImm.getImmValue() == secondImm.getImmValue();
        } else if (firstCode instanceof MCLi && secondCode instanceof MCLi) {
            PhysicsReg firstReg = getReg(((MCLi) firstCode).getDest());
            PhysicsReg secondReg = getReg(((MCLi) secondCode).getDest());
            return firstReg == secondReg;
        } else {
            return false;
        }
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
