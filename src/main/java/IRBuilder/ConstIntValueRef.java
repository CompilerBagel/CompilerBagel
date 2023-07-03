package IRBuilder;

import Type.Int32Type;
import Type.Type;

public class ConstIntValueRef implements ValueRef{
    private int value;
    private final Type type;
    public ConstIntValueRef(int value){
        this.value = value;
        this.type = Int32Type.IRInt32Type();
    }
    public ConstIntValueRef(int value,Type type){
        this.value = value;
        this.type = type;
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
