package backend.machineCode;

public final class MachineConstants {
    public MachineConstants() {
    }

    public static final String ADD = "add";
    public static final String SUB = "sub";
    public static final String ADDW = "addw";
    public static final String SUBW = "subw";
    public static final String ADDI = "addi";
    public static final String SUBI = "subi";
    public static final String ADDIW = "addiw";
    public static final String SUBIW = "subiw";
    public static final String MULW = "mulw";
    public static final String DIVW = "divw";
    public static final String XOR = "xor";
    public static final String XORI = "xori";
    public static final String SW = "sw";
    public static final String SD = "sd";
    public static final String LW = "lw";
    public static final String LA = "la";
    public static final String LD = "ld";
    public static final String J = "j";
    public static final String BNE = "bne";


    public static final int MCEQ = 0;
    public static final int MCNE = 1;
    public static final int MCGTU = 2;
    public static final int MCGEU = 3;
    public static final int MCLTU = 4;
    public static final int MCLEU = 5;
    public static final int MCGT = 6;
    public static final int MCGE = 7;
    public static final int MCLT = 8;
    public static final int MCLE = 9;

    public static final String[] MCCodes = {
            "beq", "bne",
            "bgtu", "bgeu", "bltu", "bleu",
            "bgt", "bge", "blt", "ble"
    };

}
