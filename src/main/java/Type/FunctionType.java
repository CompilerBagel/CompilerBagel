package Type;

import java.util.List;

import static Type.VoidType.IRVoidType;

/**
 * Function: can be represented as (BaseType, ...) : BaseType or (BaseType, ...)
 * e.g. (i32, i32): i32, ()
 * the latter representing a function without a return value.
 */

public class FunctionType implements Type{
    private final Type retType;
    private final List<Type> paramsType;
    private final int paramsCount;
    private static final Type voidType = IRVoidType();

    public FunctionType(List<Type> paramsType, Type retType) {
        this.paramsType = paramsType;
        this.retType = retType;
        this.paramsCount = paramsType.size();
    }

    @Override
    public String getText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');

        if (paramsCount > 0) {
            stringBuilder.append(paramsType.get(0).getText());
        }
        for (int i = 1; i < paramsCount; i++) {
            stringBuilder.append(", ")
                    .append(paramsType.get(i).getText());
        }
        stringBuilder.append(")");

        if (retType != voidType) {
            stringBuilder.append(": ")
                    .append(retType.getText());
        }
        return stringBuilder.toString();
    }

    public String getParamsTable() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        if (paramsCount > 0) {
            stringBuilder.append(paramsType.get(0).getText());
            stringBuilder.append(" ").append("%").append(0);
        }
        for (int i = 1; i < paramsCount; i++) {
            stringBuilder.append(", ")
                    .append(paramsType.get(i).getText())
                    .append(" ").append("%").append(i);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public Type getParamType(int i) {
        return paramsType.get(i);
    }
    public Type getRetType() {
        return retType;
    }
    public List<Type> getParamsType(){return paramsType;}
}
