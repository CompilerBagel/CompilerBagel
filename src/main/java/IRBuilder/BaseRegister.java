package IRBuilder;

import Type.Type;
import backend.reg.Reg;

public class BaseRegister extends Reg implements ValueRef  {
    static int tempCounter = 0;
    private final Type type;
    private final String identity;
    private final int tempNO;

    public BaseRegister(String identity, Type type) {
        super(operandType.virtualReg);
        this.identity = identity;
        this.tempNO = tempCounter++;
        this.type = type;
    }
    @Override
    public String getText() {
        return "%" + identity + tempNO;
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
