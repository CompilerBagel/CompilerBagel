package IRBuilder;

import Type.Type;
import Type.*;
import static IRBuilder.IRConstants.*;
import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;

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
        if (valueRef instanceof ConstIntValueRef || valueRef instanceof ConstFloatValueRef) {
            builder.emit(RET + " " + valueRef.getTypeText() + " " + valueRef.getText());
        } else if (valueRef instanceof LocalVarIntValueRef) {
            String registerName = builder.loadVariable(valueRef);
            builder.emit(RET + " " + valueRef.getTypeText() + " " + registerName);
        } else if (valueRef instanceof GlobalVarIntValueRef) {
            builder.emit(RET + " " + valueRef.getTypeText() + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText());
        }
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildAdd(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        String lhsValRefRegisterStr = builder.loadVariable(lhsValRef);
        String rhsValRefRegisterStr = builder.loadVariable(rhsValRef);
        Register resRegister;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resRegister = new Register(name, floatType);
        } else {
            resRegister = new Register(name, int32Type);
        }
        builder.emit(resRegister.getText() + " = add " + resRegister.getTypeText() + " " + lhsValRefRegisterStr + ", " + rhsValRefRegisterStr);
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

    private void emit(String code, int align) {
        currentBaseBlock.emit(code, align);
    }

    private String loadVariable(ValueRef valueRef) {
        if (valueRef instanceof LocalVarIntValueRef) {
            Register register = new Register(valueRef.getTypeText(), Int32Type.IRInt32Type());
            emit(register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
                    + valueRef.getTypeText() + "*" + " " + ((LocalVarIntValueRef) valueRef).getRegisterText(), 4);
            return register.getText();
        } else if (valueRef instanceof GlobalVarIntValueRef) {
            Register register = new Register(valueRef.getTypeText(), Int32Type.IRInt32Type());
            emit(register.getText() + " " + "= load " + valueRef.getTypeText() + ", "
                    + valueRef.getTypeText() + "*" + " " + ((GlobalVarIntValueRef) valueRef).getRegisterText(), 4);
            return register.getText();
        } else if (valueRef instanceof ConstIntValueRef) {
            return valueRef.getText();
        }
        //TODO: Add more
        return null;
    }
}
