package IRBuilder;

import Type.ArrayType;
import Type.FunctionType;
import Type.PointerType;
import Type.Type;
import instruction.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static IRBuilder.IRConstants.*;
import static Type.FloatType.IRFloatType;
import static Type.Int1Type.IRInt1Type;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;

public class IRBuilder {
    private BaseBlock currentBaseBlock = new BaseBlock("root");
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private static final Type voidType = IRVoidType();

    /**
     * -------- static methods --------
     */
    public static void IRPositionBuilderAtEnd(IRBuilder builder, BaseBlock block) {
        builder.currentBaseBlock.addSuccBaseBlock(block);
        block.addPredBaseBlock(builder.currentBaseBlock);
        builder.currentBaseBlock = block;
    }

    public static IRBuilder IRCreateBuilder() {
        return new IRBuilder();
    }

    public static void IRBuildRet(IRBuilder builder, ValueRef valueRef) {
        builder.currentBaseBlock.appendInstr(new RetInstruction(generateList(valueRef), builder.currentBaseBlock));
        builder.emit(RET + " " + valueRef.getTypeText() + " " + valueRef.getText());
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildAdd(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            // todo: immediate number?
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
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, ADD));
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
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, SUB));
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
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, MUL));
        builder.emit(resRegister.getText() + " = " + MUL + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }


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
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, DIV));
        builder.emit(resRegister.getText() + " = " + DIV + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildNeg(IRBuilder builder, ValueRef valueRef, String name) {
        // todo: neg
        return IRBuildSub(builder, new ConstIntValueRef(0), valueRef, name);
    }

    public static ValueRef IRBuildSRem(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhsValRef.getText()) % Integer.valueOf(rhsValRef.getText()));
        }
        ValueRef resRegister = new BaseRegister(name, int32Type);
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, SREM));
        builder.emit(resRegister.getText() + " = " + SREM + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildZExt(IRBuilder builder, ValueRef valueRef, Type type, String name) {
        if (valueRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(valueRef.getText()));
        }
        ValueRef resRegister = new BaseRegister(name, type);
        builder.appendInstr(new ZextInstruction(generateList(resRegister, valueRef, new ConstIntValueRef(0, type)), builder.currentBaseBlock));
        builder.emit(resRegister.getText() + " = " + ZEXT + " " + int1Type.getText() + " " + valueRef.getText() + " to " + type.getText());
        return resRegister;
    }

    public static ValueRef IRBuildXor(IRBuilder builder, ValueRef lhs, ValueRef rhs, String name) {
        if (lhs instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhs.getText()) ^ 1);
        }
        ValueRef resRegister = new BaseRegister(name, rhs.getType());
        // todo: Xor
        builder.emit(resRegister.getText() + " = " + XOR + " " + rhs.getTypeText() + " " + lhs.getText() + ", true");
        return resRegister;
    }

    public static ValueRef IRBuildAlloca(IRBuilder builder, Type type, String name) {
        ValueRef resRegister;
        PointerType pointerType = new PointerType(type);
        resRegister = new BaseRegister(name, pointerType);
        builder.appendInstr(new AllocaInstruction(generateList(resRegister), builder.currentBaseBlock));
        builder.emit(resRegister.getText() + " = " + ALLOCA + " " + type.getText(), 4);
        return resRegister;
    }

    public static ValueRef IRBuildStore(IRBuilder builder, ValueRef valueRef, ValueRef pointer) {
        ValueRef resRegister;
        PointerType pointerType = new PointerType(valueRef.getType());
        resRegister = new BaseRegister("temp", pointerType);
        builder.appendInstr(new StoreInstruction(generateList(valueRef, pointer), builder.currentBaseBlock));
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
        List<ValueRef> operands = new ArrayList<>();
        operands.add(resRegister);
        operands.add(pointer);
        builder.appendInstr(new LoadInstruction(operands, builder.currentBaseBlock));
        return resRegister;
    }

    public static ValueRef IRAddGlobal(IRModule module, Type type, String globalVarName) {
        // TODO: ArrayType
        // todo: addGlobalIns
        Type baseType = new PointerType(type);
        ValueRef resRegister = new GlobalRegister(globalVarName, baseType);
        module.emitWithoutLF(resRegister.getText() + " = " + GLOBAL + " " +((PointerType)resRegister.getType()).getBaseType().getText() + " ");
        return resRegister;
    }

    public static void IRSetInitializer(IRModule module, ValueRef GlobalVar , ValueRef ConstRef) {
        // todo: addInit
        module.emit(ConstRef.getText());
    }

    public static void IRSetInitializer(IRModule module, ValueRef valueRef , List<ValueRef> constValueRefList){
        boolean flag = true;
        for(int i = 0;i < constValueRefList.size();i++){
            if(!Objects.equals(constValueRefList.get(i).getText(), "0")){
                flag = false;
                break;
            }
        }
        if(flag){
            module.emit("zeroInitializer");
            return;
        }

        Type baseType= ((PointerType) valueRef.getType()).getBaseType();
        StringBuilder emitStr = new StringBuilder();
        Type elementType = ((ArrayType) baseType).getElementType();
        int paramCount = 1;
        List<Integer> paramList = new ArrayList<Integer>();
        while(elementType instanceof ArrayType){
            paramCount *= ((ArrayType) elementType).getElementNumber();
            paramList.add(((ArrayType) elementType).getElementNumber());
            emitStr.append(elementType.getText());
            elementType = ((ArrayType) elementType).getElementType();
        }
        String typeStr = null;
        if(elementType == int32Type){
            typeStr = int32Type.getText();
        }else{
            typeStr = floatType.getText();
        }
        int lastLength = paramList.get(paramList.size()-1);
        elementType = new ArrayType(elementType , lastLength);
        int temp = constValueRefList.size() / lastLength;
        int counter = 0;
        for(int i = 0;i<temp;i++){
            emitStr.append(elementType.getText() + " [");
            for(int j = 0;j<lastLength;j++){
                if(j==lastLength-1){
                    emitStr.append(typeStr+" "+constValueRefList.get(counter++).getText());
                    break;
                }
                emitStr.append(typeStr+" "+constValueRefList.get(counter++).getText()+", ");
            }
            emitStr.append("], ");
        }
        module.emit(emitStr.toString());
    }

    public static void IRBuildBr(IRBuilder builder, BaseBlock block) {
        builder.appendInstr(new BrInstruction(generateList((ValueRef) block), builder.currentBaseBlock));
        builder.emit(BR + " label %" + block.getLabel());
    }

    public static void IRBuildCondBr(IRBuilder builder, ValueRef condition, BaseBlock ifTrue, BaseBlock ifFalse) {
        builder.appendInstr(new BrInstruction(generateList((condition, (ValueRef) ifTrue, (ValueRef) ifFalse)), builder.currentBaseBlock);
        builder.emit(BR + " " + condition.getTypeText() + " " + condition.getText() + ", " +
                "label %" + ifTrue.getLabel() + ", label %" + ifFalse.getLabel());
    }

    public static ValueRef IRBuildICmp(IRBuilder builder, int icmpType, ValueRef lhs, ValueRef rhs, String text) {
        ValueRef resRegister = new BaseRegister(text, int1Type);
        builder.appendInstr(new CondInstruction(generateList(resRegister, lhs, rhs), builder.currentBaseBlock, icmpType));
        builder.emit(resRegister.getText() + " = " + ICMP + " " + ICMPCodes[icmpType] + " "
                + lhs.getTypeText() + " " + lhs.getText() + ", " + rhs.getText());
        return resRegister;
    }

    public static ValueRef IRBuildGEP(IRBuilder builder, ValueRef valueRef, List<ValueRef> indexs, int indexSize, String varName) {
        // todo: gep
        Type baseType = ((PointerType) valueRef.getType()).getBaseType();
        Type resType;
        if (baseType instanceof ArrayType) {
            resType = ((ArrayType) baseType).getElementType();
        } else {
            resType = baseType;
        }
        ValueRef resRegister = new BaseRegister(varName, resType);
        StringBuilder indexStrBuilder = new StringBuilder();
        for (ValueRef index : indexs) {
            indexStrBuilder.append(", ").append(int32Type.getText())
                    .append(" ").append(index.getText());
        }
        builder.emit(resRegister.getText() + " = " + GETPTR + " " + baseType.getText() + ", "
                + valueRef.getTypeText() + " " + valueRef.getText() + indexStrBuilder.toString());
        return resRegister;
    }

    public static ValueRef IRBuildCall(IRBuilder builder, FunctionBlock function, List<ValueRef> args, int argc, String varName) {
        // todo: call
        Type retType = ((FunctionType) function.getType()).getRetType();
        ValueRef resRegister = new BaseRegister(varName, retType);
        StringBuilder stringBuilder = new StringBuilder();
        if (retType != voidType) {
            stringBuilder.append(resRegister.getText()).append(" = ");
        }
        stringBuilder.append(CALL).append(" ").append(function.getRetType().getText())
                .append(" ").append(function.getText()).append("(");
        if (argc > 0) {
            stringBuilder.append(args.get(0).getTypeText()).append(" ").append(args.get(0).getText());
        }
        for (int i = 1; i < argc; i++) {
            stringBuilder.append(", ")
                    .append(args.get(i).getTypeText())
                    .append(" ").append(args.get(i).getText());
        }
        stringBuilder.append(")");
        builder.emit(stringBuilder.toString());
        return resRegister;
    }

    public static ValueRef IRGetParam(FunctionBlock function, int i) {
        return function.getParam(i);
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

    private void appendInstr(Instruction instr) {
        currentBaseBlock.appendInstr(instr);
    }

    private static List<ValueRef> generateList(ValueRef valueRef){
        List<ValueRef> list = new ArrayList<>();
        list.add(valueRef);
        return list;
    }

    private static List<ValueRef> generateList(ValueRef left, ValueRef right){
        List<ValueRef> list = new ArrayList<>();
        list.add(left);
        list.add(right);
        return list;
    }

    private static List<ValueRef> generateList(ValueRef resRegister, ValueRef left, ValueRef right){
        List<ValueRef> list = new ArrayList<>();
        list.add(resRegister);
        list.add(left);
        list.add(right);
        return list;
    }
}
