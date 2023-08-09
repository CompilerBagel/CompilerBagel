package backend.machineCode;

import IRBuilder.IRConstants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public static final String MULI = "muli";
    public static final String DIVW = "divw";
    public static final String XOR = "xor";
    public static final String XORI = "xori";
    public static final String REM = "rem";
    public static final String REMU = "remu";
    public static final String SLLW = "sllw";
    public static final String SLLIW = "slliw";
    public static final String SRL = "srl";
    public static final String SRAW = "sraw";
    public static final String ANDI = "andi";
    public static final String AND = "and";
    public static final String SW = "sw";
    public static final String SD = "sd";
    public static final String LW = "lw";
    public static final String LA = "la";
    public static final String LD = "ld";
    public static final String J = "j";
    public static final String BNE = "bne";
    public static final String FADD_S = "fadd.s";
    public static final String FSUB_S = "fsub.s";
    public static final String FMUL_S = "fmul.s";
    public static final String FDIV_S = "fdiv.s";
    public static final String FLW = "flw";
    public static final String FSW = "fsw";
    public static final String FEQ = "feq.s";
    public static final String FLT = "flt.s";
    public static final String FLE = "fle.s";
    public static final String FCVT_S_W = "fcvt.s.w";
    public static final String FCVT_W_S = "fcvt.w.s";
    public static final String FMV_X_W = "fmv.x.w";
    public static final String FMV_W_X = "fmv.w.x";
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

    public static Map<String, String> intOperatorMap = Stream.of(new String[][] {
            { IRConstants.ADD, ADDW },
            { IRConstants.SUB, SUBW },
            { IRConstants.MUL, MULW },
            { IRConstants.SDIV, DIVW },
            { IRConstants.XOR, XOR },
            { IRConstants.SREM, REM },
            { IRConstants.AND, AND},
            { IRConstants.SHL, SLLW},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static Map<String, String> floatOperatorMap = Stream.of(new String[][] {
            { IRConstants.FADD, FADD_S },
            { IRConstants.FSUB, FSUB_S },
            { IRConstants.FMUL, FMUL_S },
            { IRConstants.FDIV, FDIV_S },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

}
