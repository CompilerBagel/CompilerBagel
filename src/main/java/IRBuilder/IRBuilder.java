package IRBuilder;


import Type.Type;

import static IRBuilder.IRConstants.*;
import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;
import Type.PointerType;
public class IRBuilder {
    private BaseBlock currentBaseBlock = null;
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();

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
        builder.emit(RET + " " + valueRef.getTypeText() + " " + valueRef.getText());
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildAdd(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + ADD + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + lhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildSub(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name){
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + SUB + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + lhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildAlloca(IRBuilder builder, Type type , String name){
        ValueRef resRegister;
        PointerType pointerType = new PointerType(type);
        resRegister = new BaseRegister(name, pointerType);
        builder.emit(resRegister.getText() + " = " + ALLOCA + type.getText() + ", " ,4);
        return resRegister;
    }

    public static void IRBuildStore(IRBuilder builder, ValueRef valueRef, ValueRef pointer){
        builder.emit(STORE + " " + valueRef.getTypeText() + " " + valueRef.getText() +
                ", " + pointer.getTypeText() + " " + pointer.getText() + ", ",4);
    }

    public static ValueRef IRAddGlobal(IRModule module, Type type, String globalVarName) {
        // TODO:
        //   ArrayType
        ValueRef resRegister;
        resRegister = new GlobalRegister(globalVarName , type);
        module.emitWithoutLF(resRegister.getText() + " = " + GLOBAL + resRegister.getTypeText());
        return resRegister;
    }

    public static void IRSetInitializer(IRModule module, ValueRef valueRef){
        module.emit(valueRef.getText());
    }

    /**
     * -------- member methods --------
     */
    private IRBuilder() {
    }

    private void emit(String code) {
        currentBaseBlock.emit(code);
    }

    private void emit(String code, int align) {
        currentBaseBlock.emit(code, align);
    }

    private String loadVariable(ValueRef valueRef) {
//        if (valueRef instanceof LocalVarIntValueRef) {
//            BaseRegister register = new BaseRegister(valueRef.getTypeText(), int32Type);
//            emit(register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
//                    + valueRef.getTypeText() + "*" + " " + ((LocalVarIntValueRef) valueRef).getRegisterText(), 4);
//            return register.getText();
//        } else if (valueRef instanceof GlobalVarIntValueRef) {
//            BaseRegister register = new BaseRegister(valueRef.getTypeText(), int32Type);
//            emit(register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
//                    + valueRef.getTypeText() + "*" + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText(), 4);
//            return register.getText();
//        } else if (valueRef instanceof ConstIntValueRef) {
//            return valueRef.getText();
//        }
        return null;
    }

}
