package IRBuider;

public class IRBuilder {
    private BaseBlock currentBaseBlock = null;

    /** -------- static methods --------*/
    public static void IRPositionBuilderAtEnd(IRBuilder builder, BaseBlock block) {
        builder.currentBaseBlock = block;
    }

    public static IRBuilder IRCreateBuilder() {
        return new IRBuilder();
    }

    public static void IRBuildRet(IRBuilder builder, ValueRef valueRef) {
        builder.emit("ret " + valueRef.getTypeText() + " " + valueRef.getText());
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()


    /** -------- member methods --------*/
    private IRBuilder() {}

    private void emit(String code) {
        currentBaseBlock.emit(code);
    }

}
