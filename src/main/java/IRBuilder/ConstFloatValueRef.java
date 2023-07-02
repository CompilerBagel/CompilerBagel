package IRBuilder;

import Type.FloatType;
import Type.Type;

public class ConstFloatValueRef implements ValueRef{
    private final float value;
    private final Type type;

    public ConstFloatValueRef(float value) {
        this.value = value;
        this.type = FloatType.IRFloatType();
    }

    @Override
    public String getText() {
        return "" + value;
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
