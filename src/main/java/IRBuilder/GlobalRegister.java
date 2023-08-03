package IRBuilder;

import Type.Type;
import backend.reg.Reg;

public class GlobalRegister extends BaseRegister implements ValueRef {
    static int globalCounter = 0;
    private final Type type;
    private final int globalNO;

    public GlobalRegister(String identity, Type type) {
        super(identity, type);
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
