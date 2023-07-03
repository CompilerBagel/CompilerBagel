package IRBuilder;


import Type.PointerType;
import Type.Type;

import static IRBuilder.IRConstants.*;
import static Type.FloatType.IRFloatType;
import static Type.Int1Type.IRInt1Type;
import static Type.Int32Type.IRInt32Type;

public class IRBuilder {
    private BaseBlock currentBaseBlock = null;
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();

    private static final Type int1Type = IRInt1Type();

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
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhsValRef.getText()) + Integer.valueOf(rhsValRef.getText()));
        } else if (lhsValRef instanceof ConstFloatValueRef && rhsValRef instanceof ConstFloatValueRef) {
            return new ConstFloatValueRef(Float.valueOf(lhsValRef.getText()) + Float.valueOf(rhsValRef.getText()));
        }
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + ADD + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildSub(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhsValRef.getText()) - Integer.valueOf(rhsValRef.getText()));
        } else if (lhsValRef instanceof ConstFloatValueRef && rhsValRef instanceof ConstFloatValueRef) {
            return new ConstFloatValueRef(Float.valueOf(lhsValRef.getText()) - Float.valueOf(rhsValRef.getText()));
        }
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + SUB + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    // Author: huangwei021230
    public static ValueRef IRBuildMul(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhsValRef.getText()) * Integer.valueOf(rhsValRef.getText()));
        } else if (lhsValRef instanceof ConstFloatValueRef && rhsValRef instanceof ConstFloatValueRef) {
            return new ConstFloatValueRef(Float.valueOf(lhsValRef.getText()) * Float.valueOf(rhsValRef.getText()));
        }
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + MUL + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    // Author: huangwei021230
    public static ValueRef IRBuildDiv(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhsValRef.getText()) / Integer.valueOf(rhsValRef.getText()));
        } else if (lhsValRef instanceof ConstFloatValueRef && rhsValRef instanceof ConstFloatValueRef) {
            return new ConstFloatValueRef(Float.valueOf(lhsValRef.getText()) / Float.valueOf(rhsValRef.getText()));
        }
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        resRegister = new BaseRegister(name, resType);
        builder.emit(resRegister.getText() + " = " + DIV + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildAlloca(IRBuilder builder, Type type, String name) {
        ValueRef resRegister;
        PointerType pointerType = new PointerType(type);
        resRegister = new BaseRegister(name, pointerType);
        builder.emit(resRegister.getText() + " = " + ALLOCA + " " + type.getText(), 4);
        return resRegister;
    }

    public static ValueRef IRBuildStore(IRBuilder builder, ValueRef valueRef, ValueRef pointer) {
        ValueRef resRegister;
        PointerType pointerType = new PointerType(valueRef.getType());
        resRegister = new BaseRegister("temp", pointerType);
        builder.emit(STORE + " " + valueRef.getTypeText() + " " + valueRef.getText() +
                ", " + pointer.getTypeText() + " " + pointer.getText(), 4);
        return resRegister;
    }

    /**
     * Fetch the value from memory
     *
     * @param pointer a valueRef that is a pointer
     * @param varName the name of variable
     */
    public static ValueRef IRBuildLoad(IRBuilder builder, ValueRef pointer, String varName) {
        Type baseType = ((PointerType) pointer.getType()).getBaseType();
        ValueRef resRegister = new BaseRegister(varName, baseType);
        builder.emit(resRegister.getText() + " = " + LOAD + " " + baseType.getText() + ", "
                + pointer.getTypeText() + " " + pointer.getText(), 4);
        return resRegister;
    }

    public static ValueRef IRAddGlobal(IRModule module, Type type, String globalVarName) {
        // TODO:
        //   ArrayType
        ValueRef resRegister;
        resRegister = new GlobalRegister(globalVarName, type);
        module.emitWithoutLF(resRegister.getText() + " = " + GLOBAL + " " + resRegister.getTypeText() + " ");
        return resRegister;
    }

    public static void IRSetInitializer(IRModule module, ValueRef valueRef) {
        module.emit(valueRef.getText());
    }

    public static void IRBuildBr(IRBuilder builder, BaseBlock block) {
        builder.emit(BR + " label %" + block.getLabel());
    }

    public static void IRBuildCondBr(IRBuilder builder, ValueRef condition, BaseBlock ifTrue, BaseBlock ifFalse) {
        builder.emit(BR + " " + condition.getTypeText() + " " + condition.getText() + ", " +
                "label %" + ifTrue.getLabel() + ", label %" + ifFalse.getLabel());
    }

    public static ValueRef IRBuildICmp(IRBuilder builder, int icmpType, ValueRef lhs, ValueRef rhs, String text) {
        ValueRef resRegister = new BaseRegister(text, int1Type);
        builder.emit(resRegister.getText() + " = " + ICMP + " " + ICMPCodes[icmpType] + " "
                + lhs.getTypeText() + " " + lhs.getText() + ", " + rhs.getText());
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
