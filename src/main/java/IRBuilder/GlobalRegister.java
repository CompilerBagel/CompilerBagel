package IRBuilder;

import Type.Type;

public class GlobalRegister implements ValueRef{
    static int globalCounter = 0;
    private final Type type;
    private final String identity;
    private final int globalNO;

    public GlobalRegister(String identity, Type type) {
        this.identity = identity;
        this.globalNO = globalCounter++;
        this.type = type;
    }
    @Override
    public String getText() {
        return "@" + identity + globalNO;
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
