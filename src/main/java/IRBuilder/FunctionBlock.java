package IRBuilder;

import Type.FunctionType;

import java.util.ArrayList;
import java.util.List;

public class FunctionBlock {
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
        // TODO: params
        stringBuilder.append("define ").append(type.getRetType().getText()).append(" @").append(functionName).append("() {\n");
        for (BaseBlock baseBlock : baseBlocks) {
            stringBuilder.append(baseBlock.getLabel()).append(":\n");
            stringBuilder.append(baseBlock.getCodeBuilder());
        }
        stringBuilder.append("}\n");
        return stringBuilder;
    }
}
