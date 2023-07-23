package IRBuilder;

import Type.Type;
import backend.reg.Reg;

public class BaseRegister extends Reg implements ValueRef  {
    static int tempCounter = 0;
    private final Type type;
    private final int tempNO;

    public BaseRegister(String identity, Type type) {
        super(operandType.virtualReg, identity);
        this.tempNO = tempCounter++;
        this.type = type;
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
