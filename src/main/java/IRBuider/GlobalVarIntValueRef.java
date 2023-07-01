package IRBuider;

import Type.Type;

public class GlobalVarIntValueRef implements ValueRef {
    private String name;
    ConstIntValueRef intValueRef;
    private Register register;
    private Type type;
    GlobalVarIntValueRef(ConstIntValueRef intValueRef,String name, Type type){
        this.name = name;
        this.intValueRef = intValueRef;
        this.type = type;
        this.register = new Register(name,type);
    }
    @Override
    public String getText() {
        return name;
    }
    @Override
    public String getTypeText() {
        return ""+type.getText();
    }
    public String getRegisterText(){
        return register.getText();
    }
}
