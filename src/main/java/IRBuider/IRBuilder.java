package IRBuider;

import Type.Type;
import Type.*;

public class IRBuilder {
    private BaseBlock currentBaseBlock = null;

    /**
     * -------- static methods --------
     */
    public static void IRPositionBuilderAtEnd(IRBuilder builder, BaseBlock block) {
        builder.currentBaseBlock = block;
    }

    public static IRBuilder IRCreateBuilder() {
        return new IRBuilder();
    }

    public static void IRBuildRet(IRBuilder builder, ValueRef valueRef) {
        if (valueRef instanceof ConstIntValueRef || valueRef instanceof ConstFloatValueRef) {
            builder.emit("  ret " + valueRef.getTypeText() + " " + valueRef.getText());
        } else if (valueRef instanceof LocalVarIntValueRef) {
            String registerName = builder.loadVariable(valueRef);
            builder.emit("  ret " + valueRef.getTypeText() + " " + registerName);
        } else if (valueRef instanceof GlobalVarIntValueRef) {
            builder.emit("  ret " + valueRef.getTypeText() + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText());
        }
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildAdd(IRBuilder builder, ValueRef LvalRef, ValueRef RvalRef, String name) {
        String LvalRefRegisterStr = builder.loadVariable(LvalRef);
        String RvalRefRegisterStr = builder.loadVariable(RvalRef);
        Register resRegister;
        if(LvalRef.getTypeText().equals("float") || RvalRef.getTypeText().equals("float")){
            resRegister = new Register(name,FloatType.IRFloatType());
        }else{
            resRegister = new Register(name,Int32Type.IRInt32Type());
        }
        builder.emit("  "+resRegister.getText()+" = add "+resRegister.getTypeText()+" "+LvalRefRegisterStr+", "+RvalRefRegisterStr);
        return resRegister;
    }


    /**
     * -------- member methods --------
     */
    private IRBuilder() {
    }

    private void emit(String code) {
        currentBaseBlock.emit(code);
    }

    private String loadVariable(ValueRef valueRef) {
        if (valueRef instanceof LocalVarIntValueRef) {
            Register register = new Register(valueRef.getTypeText(), Int32Type.IRInt32Type());
            emit("  " + register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
                    + valueRef.getTypeText() + "*" + " " + ((LocalVarIntValueRef) valueRef).getRegisterText()
                    + "," + "align 4");
            return register.getText();
        }else if(valueRef instanceof GlobalVarIntValueRef){
            Register register = new Register(valueRef.getTypeText(), Int32Type.IRInt32Type());
            emit("  " + register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
                    + valueRef.getTypeText() + "*" + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText()
                    + "," + "align 4");
            return register.getText();
        }else if(valueRef instanceof ConstIntValueRef) {
            return valueRef.getText();
        }
        //TODO: Add more
        return null;
    }
}
