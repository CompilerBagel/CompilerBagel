package IRBuilder;

import Type.Type;
import backend.post.reg.Reg;

public class BaseRegister extends Reg implements ValueRef  {
    static int tempCounter = 0;
    private final Type type;
    private final int tempNO;
    boolean UsedByFunction = false;
    int floatNO = -1;
    int noFloatNO = -1;
    private int spillIndex = -1;

    public BaseRegister(String identity, Type type) {
        super(operandType.virtualReg, identity);
        this.tempNO = tempCounter++;
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
        return "%" + this.getIdentity() + tempNO;
    }
    @Override
    public String getTypeText() {
        return type.getText();
    }
    @Override
    public Type getType() {
        return type;
    }
    @Override
    public String toString() {
        return "%" + this.getIdentity() + tempNO;
    }
}
