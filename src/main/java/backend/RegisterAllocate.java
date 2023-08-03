package backend;

import IRBuilder.BaseRegister;
import Type.Type;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineFunction;
import backend.machineCode.MachineOperand;
import backend.reg.FloatPhysicsReg;
import backend.reg.PhysicsReg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static backend.reg.PhysicsReg.*;
import static Type.FloatType.IRFloatType;
public class RegisterAllocate {
    private static final int K = 14; // number of colors
    private static final int NONE_ALLOCATE = -1;
    private List<MachineFunction> functions;
    private HashMap<MachineOperand, List<MachineOperand>> edges;
    private List<MachineOperand> nodes;
    private final HashMap<MachineOperand, PhysicsReg> allocatedReg = new HashMap<>();
    private final HashMap<MachineOperand, Integer> color = new HashMap<>();
    private static final Type floatType = IRFloatType();

    public RegisterAllocate(List<MachineFunction> functions) {
        this.functions = functions;
    }

    public void allocate() {
        // TODO:
    }

    public void funcAllocate(MachineFunction function) {
        LinkedList<MachineBlock> blocks = function.getMachineBlocks();
        // TODO: giveBack a0~a7

        // Save all operands
        for (MachineBlock block : blocks) {
            List<MachineCode> codes = block.getMachineCodes();
            for (MachineCode code : codes) {
                List<MachineOperand> defs = code.getDef();
                List<MachineOperand> uses = code.getUse();
                for (MachineOperand opt : defs) {
                    if (!nodes.contains(opt)) {
                        nodes.add(opt);
                        color.put(opt, NONE_ALLOCATE);
                    }
                }
            }
        }

        // Aliveness analyze
        // Build Graph

        // Color the nodes
        for (MachineOperand node: nodes) {
            if (color.get(node) == NONE_ALLOCATE) {
                colorDfs(node);
            }
        }
        // Allocate Register


    }

    private void addEdge(MachineOperand x, MachineOperand y) {
        List<MachineOperand> x_edges = edges.get(x);
        x_edges.add(y);
        List<MachineOperand> y_edges = edges.get(y);
        y_edges.add(x);
    }

    private void colorDfs(MachineOperand x) {
        List<MachineOperand> x_edges = edges.get(x);
        if (color.get(x) != NONE_ALLOCATE) {
            return;
        }
        boolean[] occupied = new boolean[8];
        for (int i = 0; i < 8; i++) {
            occupied[i] = false;
        }
        for (MachineOperand adjNode : x_edges) {
            int nodeColor = color.get(adjNode);
            if (nodeColor != NONE_ALLOCATE) {
                occupied[nodeColor] = true;
            }
        }
        int x_color = -1;
        for (int i = 0; i < 8; i++) {
            if (!occupied[i]) {
                x_color = i;
                break;
            }
        }
        if (x_color == -1) {
            // spill;
        } else {
            color.put(x, x_color);
        }
        for (MachineOperand adjNode : x_edges) {
            int nodeColor = color.get(adjNode);
            if (nodeColor == NONE_ALLOCATE) {
                colorDfs(adjNode);
            }
        }
    }

    /**
     *  enter method, call easyAllocate when you need to allocate register
     */
    public void easyAllocate() {
        for (MachineFunction function : functions) {
            funcEasyAllocate(function);
        }
    }

    private void funcEasyAllocate(MachineFunction function) {
        LinkedList<MachineBlock> blocks = function.getMachineBlocks();
        // giveBack a0~a7
        giveBackRegA();
        for (MachineBlock block : blocks) {
            List<MachineCode> codes = block.getMachineCodes();
            for (MachineCode code : codes) {
                List<MachineOperand> defs = code.getDef();
                List<MachineOperand> uses = code.getUse();

                for (MachineOperand use : uses) {
                    use.removeUse(code);
                    if(use.isImm() || use.isLabel()) continue;
                    if (use.getPhysicsReg() != null) {
                        if (use.noUser()) {
                            use.getPhysicsReg().giveBack();
                        }
                        continue;
                    }
                    if (use instanceof PhysicsReg) {
                        if (use.noUser()){
                            ((PhysicsReg)use).giveBack();
                        }
                        continue;
                    }
                    PhysicsReg allocatedReg = getReg(use);
                    if (allocatedReg != null) {
                        use.setPhysicsReg(allocatedReg);
                        allocatedReg.occupy();

                        if (use.noUser()) {
                            allocatedReg.giveBack();
                        }
                    } else {
                        // spill
                    }
                }

                for (MachineOperand def : defs) {
                    def.removeUse(code);
                    if(def.isImm() || def.isLabel()) continue;
                    if (def.getPhysicsReg() != null) {
                        if (def.noUser()) {
                            def.getPhysicsReg().giveBack();
                        }
                        continue;
                    }
                    if (def instanceof PhysicsReg) {
                        if (def.noUser()){
                            ((PhysicsReg)def).giveBack();
                        }
                        continue;
                    }
                    PhysicsReg allocatedReg = getReg(def);
                    if (allocatedReg != null) {   // have register
                        def.setPhysicsReg(allocatedReg);
                        allocatedReg.occupy();
                        if (def.noUser()) {
                            allocatedReg.giveBack();
                        }
                    } else {
                        // spill
                    }
                }

            }
        }
    }

    private PhysicsReg getReg(MachineOperand operand) {
        PhysicsReg reg = allocatedReg.get(operand);
        if (reg != null) {
            return reg;
        }
        if (operand instanceof BaseRegister) {
            if (((BaseRegister) operand).getType() == floatType) {
                // allocate a0 ~ a7
                for (int i = 11; i <= 17; i++) {
                    if (FloatPhysicsReg.isAvailableReg(i)) {
                        reg = FloatPhysicsReg.getPhysicsReg(i);
                        allocatedReg.put(operand, reg);
                        return reg;
                    }
                }
            }
        }
        // allocate a0 ~ a7
        for (int i = 11; i <= 17; i++) {
            if (isAvailableReg(i)) {
                reg = getPhysicsReg(i);
                allocatedReg.put(operand, reg);
                return reg;
            }
        }
        return null; // need to spill
    }


}
