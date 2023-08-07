package IRBuilder;

import Type.Int32Type;
import Type.Type;

public class ConstIntValueRef implements ValueRef{
    private int value;
    private final Type type;
    boolean UsedByFunction = false;
    int floatNO = -1;
    int noFloatNO = -1;
    public ConstIntValueRef(int value){
        this.value = value;
        this.type = Int32Type.IRInt32Type();
    }
    public ConstIntValueRef(int value,Type type){
        this.value = value;
        this.type = type;
    }
    @Override
    public void setUsedByFunction(boolean usedByFunction) {
        UsedByFunction = usedByFunction;
    }
    @Override
    public boolean getUsedByFunction(){
        return this.UsedByFunction;
    }

    @Override
    public int getFloatNO() {
        return floatNO;
    }

    @Override
    public int getNoFloatNO() {
        return noFloatNO;
    }
    @Override
    public void setNoFloatNO(int noFloatNO) {
        this.noFloatNO = noFloatNO;
    }
    @Override
    public void setFloatNO(int floatNO) {
        this.floatNO = floatNO;
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
