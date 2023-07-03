package IRBuilder;

public final class IRConstants {
    private IRConstants() {
    }

    public static final String RET = "ret";
    public static final String LOAD = "load";
    public static final String ADD = "add";
    public static final String SUB = "sub";
    public static final String MUL = "mul";
    public static final String DIV = "div";
    public static final String ALLOCA = "alloca";
    public static final String STORE = "store";
    public static final String GLOBAL = "global";
    public static final String BR = "br";
    public static final String ICMP = "icmp";

    // for consistency with the LLVM API, there is no need to enumerate classes
    public static final int IRIntEQ = 0;
    public static final int IRIntNE = 1;
    public static final int IRIntUGT = 2;
    public static final int IRIntUGE = 3;
    public static final int IRIntULT = 4;
    public static final int IRIntULE = 5;
    public static final int IRIntSGT = 6;
    public static final int IRIntSGE = 7;
    public static final int IRIntSLT = 8;
    public static final int IRIntSLE = 9;

    public static final String[] ICMPCodes = {
            "eq", "ne", "ugt", "uge", "ult", "ule", "sgt", "sge", "slt", "sle"
    };
}
