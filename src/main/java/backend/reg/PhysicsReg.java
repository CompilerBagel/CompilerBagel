package backend.reg;

import java.util.HashMap;
import java.util.Map;

public class PhysicsReg extends Reg {
    private String regName;
    private int index;
    public PhysicsReg(final operandType t) {
        super(t);
    }
    public static int regNum = 32;
    private final static HashMap<String, Integer> nameMap = new HashMap<>();
    private final static HashMap<Integer, String> indexMap = new HashMap<>();
    private static final PhysicsReg[] physicsRegs = new PhysicsReg[32];
    private static final boolean[] isAvailable = new boolean[32];

    static {
        nameMap.put("zero", 0);
        nameMap.put("ra", 1);
        nameMap.put("sp", 2);
        nameMap.put("gp", 3);
        nameMap.put("tp", 4);
        nameMap.put("t0", 5);
        nameMap.put("t1", 6);
        nameMap.put("t2", 7);
        nameMap.put("fp", 8);
        nameMap.put("s1", 9);
        nameMap.put("a0", 10);
        nameMap.put("a1", 11);
        nameMap.put("a2", 12);
        nameMap.put("a3", 13);
        nameMap.put("a4", 14);
        nameMap.put("a5", 15);
        nameMap.put("a6", 16);
        nameMap.put("a7", 17);
        nameMap.put("s2", 18);
        nameMap.put("s3", 19);
        nameMap.put("s4", 20);
        nameMap.put("s5", 21);
        nameMap.put("s6", 22);
        nameMap.put("s7", 23);
        nameMap.put("s8", 24);
        nameMap.put("s9", 25);
        nameMap.put("s10", 26);
        nameMap.put("s11", 27);
        nameMap.put("t3", 28);
        nameMap.put("t4", 29);
        nameMap.put("t5", 30);
        nameMap.put("t6", 31);
        
        for (Map.Entry<String, Integer> entry : nameMap.entrySet()) {
            String name = entry.getKey();
            int index = entry.getValue();
            indexMap.put(index, name);
        }

        for (int i = 0; i < regNum; i++) {
            isAvailable[i] = true;
        }

    }
    
    public PhysicsReg(int index) {
        super(operandType.physicsReg);
        this.index = index;
        this.regName = indexMap.get(index);
    }
    
    public PhysicsReg(String regName) {
        super(operandType.physicsReg);
        this.index = nameMap.get(regName);
        this.regName = regName;
    }

    public static PhysicsReg getPhysicReg(int index) {
        if (physicsRegs[index] == null) {
            physicsRegs[index] = new PhysicsReg(index);
        }
        return physicsRegs[index];
    }

    public static PhysicsReg getSpReg() {
        return getPhysicsReg(2);
    }

    public static void setAvailable(int index, boolean available) {
        isAvailable[index] = available;
    }

    public static PhysicsReg getAvailableReg() {
        for (int i = 0; i < regNum; i++) {
            if (isAvailable[i]) {
                isAvailable[i] = false;
                return getPhysicReg(i);
            }
        }
        return null;
    }

    public static void resetAvailableRegs() {
        for (int i = 0; i < regNum; i++) {
            isAvailable[i] = true;
        }
    }

    public static PhysicsReg getPhysicsReg(int index) {
        isAvailable[index] = false;
        return physicsRegs[index];
    }

    public static void giveBackReg(int index) {
        isAvailable[index] = true;
    }

    public static void giveBackRegA() {
        for (int i = 10; i <= 17; i++) {
            isAvailable[i] = true;
        }
    }
    public static boolean isAvailableReg(int index) {
        return isAvailable[index];
    }

    public void giveBack() {
        isAvailable[index] = true;
    }

    public String toString() {
        return regName;
    }

}
