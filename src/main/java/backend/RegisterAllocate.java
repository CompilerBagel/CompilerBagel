package backend;

import backend.machineCode.MachineBlock;
import backend.machineCode.MachineFunction;

import java.util.List;

public class RegisterAllocate {
    private static final int K = 14; // number of colors
    private List<MachineFunction> functions;
    private boolean[][] graph;
    public RegisterAllocate(List<MachineFunction> functions) {
        this.functions = functions;
    }

    public void allocate() {
        // TODO:
    }


}
