package IRBuider;

public class BaseBlock {
    public static int baseBlockCounter = 0;
    private final StringBuilder codeBuilder;
    private String label;
    private int baseBlockId;

    /** -------- static methods --------*/
    public static void IRAppendBasicBlock(FunctionBlock function, String label) {
        function.addBaseBlock(new BaseBlock(label));
    }

    /** -------- member methods --------*/
    public BaseBlock(String label) {
        this.codeBuilder = new StringBuilder();
        this.label = label;
        this.baseBlockId = baseBlockCounter++;
    }

    public void emit(String code) {
        codeBuilder.append(code).append("\n");
    }
    @Override
    public String toString() {
        return codeBuilder.toString();
    }
}
