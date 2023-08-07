package IRBuilder;

import Type.FloatType;
import Type.Type;

public class ConstFloatValueRef implements ValueRef{
    private final float value;
    private final Type type;
    private boolean UsedByFunction = false;
    private int floatNO = -1;
    private int noFloatNO = -1;
    private int spillIndex = -1;

    public ConstFloatValueRef(float value) {
        this.value = value;
        this.type = FloatType.IRFloatType();
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
    public int getSpillIndex() {
        return spillIndex;
    }

    @Override
    public void setSpillIndex(int spillIndex) {
        this.spillIndex = spillIndex;
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
