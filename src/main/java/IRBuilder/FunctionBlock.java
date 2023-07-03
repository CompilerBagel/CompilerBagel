package IRBuilder;

import Type.FunctionType;
import Type.Type;

import java.util.ArrayList;
import java.util.List;

public class FunctionBlock implements ValueRef{
    private final List<BaseBlock> baseBlocks;
    private final FunctionType type;
    private final String functionName;
    public FunctionBlock(String functionName, FunctionType type) {
        this.functionName = functionName;
        this.type = type;
        this.baseBlocks = new ArrayList<>();
    }

    public void addBaseBlock(BaseBlock baseBlock) {
        baseBlocks.add(baseBlock);
    }

    public StringBuilder genIRCodes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("define ").append(type.getRetType().getText()).append(" @").append(functionName)
                .append(type.getParamsTable()).append(" {\n");
        for (BaseBlock baseBlock : baseBlocks) {
            stringBuilder.append(baseBlock.getLabel()).append(":\n");
            stringBuilder.append(baseBlock.getCodeBuilder());
        }
        stringBuilder.append("}\n");
        return stringBuilder;
    }

    @Override
    public String getText() {
        return "@" + functionName;
    }

    @Override
    public String getTypeText() {
        return type.getText();
    }

    @Override
    public Type getType() {
        return type;
    }
}
