package IRBuilder;

import Type.Type;

public class Register implements ValueRef{
    static int tempCounter = 0;
    private final Type type;
    private final String identity;
    private final int tempNO;

    public Register(String identity, Type type) {
        this.identity = identity;
        this.tempNO = tempCounter++;
        this.type = type;
    }
    @Override
    public String getText() {
        return "%" + identity + tempNO;
    }
    public String getGlobalText(){
        return "@" + identity + tempNO;
    }
    @Override
    public String getTypeText() {
        return type.getText();
    }
}
