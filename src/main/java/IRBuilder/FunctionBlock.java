package IRBuilder;

import Type.FunctionType;
import Type.Type;

import java.util.ArrayList;
import java.util.List;

public class FunctionBlock implements ValueRef{
    private final List<BaseBlock> baseBlocks;
    private final FunctionType type;
    private final String functionName;
    private final List<ValueRef> paramsValueRef;
    private List<ValueRef> caller; // call the function
    private List<ValueRef> callee; // called by the function

    public FunctionBlock(String functionName, FunctionType type) {
        this.functionName = functionName;
        this.type = type;
        this.baseBlocks = new ArrayList<>();
        this.paramsValueRef = new ArrayList<>();
        int count = 0;
        for (Type paramType : type.getParamsType()) {
            ValueRef valRegister = new BaseRegister(functionName + (count++), paramType);
            paramsValueRef.add(valRegister);
        }
    }

    public ValueRef getParam(int i) {
        return paramsValueRef.get(i);
    }

    public void addBaseBlock(BaseBlock baseBlock) {
        baseBlocks.add(baseBlock);
    }
    
    public String getFunctionName() {
        return functionName;
    }
    
    public List<BaseBlock> getBaseBlocks() {
        return baseBlocks;
    }

    public StringBuilder genIRCodes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("define ").append(type.getRetType().getText()).append(" @").append(functionName)
                .append(getParamsTable()).append(" {\n");
        for (BaseBlock baseBlock : baseBlocks) {
            stringBuilder.append(baseBlock.getLabel()).append(":\n");
            stringBuilder.append(baseBlock.getCodeBuilder());
        }
        stringBuilder.append("}\n");
        return stringBuilder;
    }

    private String getParamsTable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        int paramsCount = type.getParamsCount();
        if (paramsCount > 0) {
            stringBuilder.append(type.getParamType(0).getText());
            stringBuilder.append(" ").append(paramsValueRef.get(0).getText());
        }
        for (int i = 1; i < paramsCount; i++) {
            stringBuilder.append(", ")
                    .append(type.getParamType(i).getText())
                    .append(" ").append(paramsValueRef.get(i).getText());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
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

    public Type getRetType() {
        return type.getRetType();
    }

    public void addCaller(ValueRef callBlock){
        caller.add(callBlock);
    }

    public void addCallee(ValueRef callFunction){
        callee.add(callFunction);
    }

    public List<ValueRef> getCaller(){ return caller;}

    public List<ValueRef> getCallee(){ return callee;}
}
