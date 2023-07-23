package IRBuilder;

import Type.Type;
import backend.reg.Reg;

public class GlobalRegister extends Reg implements ValueRef {
    static int globalCounter = 0;
    private final Type type;
    private final int globalNO;

    public GlobalRegister(String identity, Type type) {
        super(operandType.virtualReg, identity);
        this.globalNO = globalCounter++;
        this.type = type;
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
