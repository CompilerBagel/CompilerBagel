package IRBuilder;

import Type.Type;
import backend.reg.Reg;

public class GlobalRegister extends Reg implements ValueRef {
    static int globalCounter = 0;
    private final Type type;
    private final int globalNO;
    boolean UsedByFunction = false;
    int floatNO = -1;
    int noFloatNO = -1;
    public GlobalRegister(String identity, Type type) {
        super(operandType.virtualReg, identity);
        this.globalNO = globalCounter++;
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
        return "@" + this.getIdentity() + globalNO;
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
