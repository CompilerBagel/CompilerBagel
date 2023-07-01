package IRBuider;

import Type.Type;

public class Register implements ValueRef{
    static int tempCounter = 0;
    private Type type;
    private final String identity;
    private final int tempNO;

    public Register(String identity) {
        this.identity = identity;
        this.tempNO = tempCounter++;
    }
    @Override
    public String getText() {
        return "%" + identity + tempNO;
    }

    @Override
    public String getTypeText() {
        return type.getText();
    }
}
