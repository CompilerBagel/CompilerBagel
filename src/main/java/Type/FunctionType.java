package Type;

import java.util.ArrayList;
import static Type.VoidType.IRVoidType;

public class FunctionType implements Type{
    private final Type retType;
    private final ArrayList<Type> paramsType;
    private final int paramsCount;
    private static final Type voidType = IRVoidType();

    public FunctionType(ArrayList<Type> paramsType, Type retType) {
        this.paramsType = paramsType;
        this.retType = retType;
        this.paramsCount = paramsType.size();
    }

    @Override
    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');

        if (paramsCount > 0) {
            stringBuilder.append(paramsType.get(0).getName());
        }
        for (int i = 1; i < paramsCount; i++) {
            stringBuilder.append(", ")
                    .append(paramsType.get(i).getName());
        }
        stringBuilder.append(")");
        if (retType != voidType) {
            stringBuilder.append(": ")
                    .append(retType.getName());
        }
        return stringBuilder.toString();
    }

    public Type getParamType(int i) {
        return paramsType.get(i);
    }
    public Type getRetType() {
        return retType;
    }
}
