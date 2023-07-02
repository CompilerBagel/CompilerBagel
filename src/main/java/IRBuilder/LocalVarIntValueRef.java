package IRBuilder;

import Type.Type;

public class LocalVarIntValueRef implements ValueRef{
    private String name;
    private ConstIntValueRef intValueRef;
    private BaseRegister register;
    private Type type;
    LocalVarIntValueRef(ConstIntValueRef intValueRef,String name, Type type){
        this.name = name;
        this.intValueRef = intValueRef;
        this.type = type;
        this.register = new BaseRegister(name,type);
    }
    @Override
    public String getText() {
        return name;
    }
    @Override
    public String getTypeText() {
        return type.getText();
    }

    @Override
    public Type getType() {
        return type;
    }

    public String getRegisterText(){
        return register.getText();
    }
}
