package IRBuider;

import Type.Int32Type;

public class ConstIntValueRef implements ValueRef{
    private int value;
    private Int32Type type;
    @Override
    public String getText() {
        return "" + value;
    }

    @Override
    public String getTypeText() {
        return type.getText();
    }
}
