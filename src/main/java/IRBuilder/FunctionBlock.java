package IRBuilder;

import Type.FunctionType;

import java.util.ArrayList;
import java.util.List;

public class FunctionBlock {
    private List<BaseBlock> baseBlocks;
    private FunctionType type;
    private String functionName;
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
        for (BaseBlock baseBlock : baseBlocks) {
            stringBuilder.append(baseBlock.getLabel());
            stringBuilder.append(baseBlock.getCodeBuilder());
        }
        return stringBuilder;
    }
}
