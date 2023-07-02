package IRBuilder;

public class BaseBlock {
    public static int baseBlockCounter = 0;
    private final StringBuilder codeBuilder;
    private final String label;
    private final int baseBlockId;

    /** -------- static methods --------*/
    public static BaseBlock IRAppendBasicBlock(FunctionBlock function, String label) {
        BaseBlock baseBlock = new BaseBlock(label);
        function.addBaseBlock(baseBlock);
        return baseBlock;
    }

    /** -------- member methods --------*/
    public BaseBlock(String label) {
        this.codeBuilder = new StringBuilder();
        this.label = label;
        this.baseBlockId = baseBlockCounter++;
    }

    public void emit(String code) {
        codeBuilder.append("  ").append(code).append("\n");
    }

    public void emit(String code, int align) {
        codeBuilder.append("  ").append(code).append(", align ").append(align).append("\n");
    }

    @Override
    public String toString() {
        return codeBuilder.toString();
    }

    public String getLabel() {
        return this.label;
    }

    public StringBuilder getCodeBuilder() {
        return this.codeBuilder;
    }

}
