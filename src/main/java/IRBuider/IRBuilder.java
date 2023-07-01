package IRBuider;

import Type.Type;
import Type.*;
public class IRBuilder {
    private BaseBlock currentBaseBlock = null;

    /** -------- static methods --------*/
    public static void IRPositionBuilderAtEnd(IRBuilder builder, BaseBlock block) {
        builder.currentBaseBlock = block;
    }

    public static IRBuilder IRCreateBuilder() {
        return new IRBuilder();
    }

    public static void IRBuildRet(IRBuilder builder, ValueRef valueRef) {
        if(valueRef instanceof ConstIntValueRef || valueRef instanceof ConstFloatValueRef) {
            builder.emit("  ret " + valueRef.getTypeText() + " " + valueRef.getText());
        }else if(valueRef instanceof LocalVarIntValueRef){
            String registerName = builder.loadVariable(valueRef);
            builder.emit("  ret " + valueRef.getTypeText() + " " + registerName);
        }else if(valueRef instanceof  GlobalVarIntValueRef){
            builder.emit("  ret " + valueRef.getTypeText() + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText());
        }
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildAdd(IRBuilder builder, ValueRef LvalRef, ValueRef RvalRef , String name){
        builder.emit("");
        return null;
    }


    /** -------- member methods --------*/
    private IRBuilder() {}

    private void emit(String code) {
        currentBaseBlock.emit(code);
    }

    private String loadVariable(ValueRef valueRef){
        if(valueRef instanceof LocalVarIntValueRef){
            Type int32Type = Int32Type.IRInt32Type();
            Register register = new Register(valueRef.getTypeText(), int32Type);
            emit("  "+register.getText()+" "+"= load "+valueRef.getTypeText()+", "
                    +valueRef.getTypeText()+"*"+" "+((LocalVarIntValueRef) valueRef).getRegisterText()
                    +","+ "align 4");
            return register.getText();
        }
        //TODO: Add more
        return null;
    }
}
