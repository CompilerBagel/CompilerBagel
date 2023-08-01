package backend.reg;

import java.util.HashMap;
import java.util.Map;

public class FloatPhysicsReg extends Reg {
    private String regName;
    private int index;
    public static int regNum = 32;
    private final static HashMap<String, Integer> nameMap = new HashMap<>();
    private final static HashMap<Integer, String> indexMap = new HashMap<>();
    private static final FloatPhysicsReg[] floatPhysicsRegs = new FloatPhysicsReg[32];
    private static final boolean[] isAvailable = new boolean[32];

    static {
        nameMap.put("ft0", 0);
        nameMap.put("ft1", 1);
        nameMap.put("ft2", 2);
        nameMap.put("ft3", 3);
        nameMap.put("ft4", 4);
        nameMap.put("ft5", 5);
        nameMap.put("ft6", 6);
        nameMap.put("ft7", 7);
        nameMap.put("fs0", 8);
        nameMap.put("fs1", 9);
        nameMap.put("fa0", 10);
        nameMap.put("fa1", 11);
        nameMap.put("fa2", 12);
        nameMap.put("fa3", 13);
        nameMap.put("fa4", 14);
        nameMap.put("fa5", 15);
        nameMap.put("fa6", 16);
        nameMap.put("fa7", 17);
        nameMap.put("fs2", 18);
        nameMap.put("fs3", 19);
        nameMap.put("fs4", 20);
        nameMap.put("fs5", 21);
        nameMap.put("fs6", 22);
        nameMap.put("fs7", 23);
        nameMap.put("fs8", 24);
        nameMap.put("fs9", 25);
        nameMap.put("fs10", 26);
        nameMap.put("fs11", 27);
        nameMap.put("ft8", 28);
        nameMap.put("ft9", 29);
        nameMap.put("ft10", 30);
        nameMap.put("ft11", 31);

        for (Map.Entry<String, Integer> entry : nameMap.entrySet()) {
            String name = entry.getKey();
            int index = entry.getValue();
            indexMap.put(index, name);
        }

        for (int i = 0; i < regNum; i++) {
            isAvailable[i] = true;
        }

    }

    public static FloatPhysicsReg getFloatPhysicsReg(int index) {
        if (floatPhysicsRegs[index] == null) {
            floatPhysicsRegs[index] = new FloatPhysicsReg(index);
        }
        isAvailable[index] = false;
        return floatPhysicsRegs[index];
    }

    public FloatPhysicsReg(int index) {
        super(operandType.physicsReg, indexMap.get(index));
        this.index = index;
        this.regName = indexMap.get(index);
    }

    public FloatPhysicsReg(String regName) {
        super(operandType.physicsReg, regName);
        this.index = nameMap.get(regName);
        this.regName = regName;
    }

    public void giveBack() {
        isAvailable[index] = true;
    }

    public FloatPhysicsReg(operandType t, String identity) {
        super(t, identity);
    }

    @Override
    public String toString() {
        return regName;
    }

}
