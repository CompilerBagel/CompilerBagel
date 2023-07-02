package IRBuilder;

import Type.FloatType;

public class ConstFloatValueRef implements ValueRef{
    private float value;
    private FloatType type;
    @Override
    public String getText() {
        return "" + value;
    }

    @Override
    public String getTypeText() {
        return type.getText();
    }
}
