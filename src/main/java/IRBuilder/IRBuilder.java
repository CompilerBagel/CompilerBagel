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
        builder.currentBaseBlock.getFunctionBlock().addRetBlock(builder.currentBaseBlock);
        if(valueRef == null){
            builder.emit(RET + " " + "void");
        }else {
            builder.emit(RET + " " + valueRef.getTypeText() + " " + valueRef.getText());
        }
    }

    // TODO: Add concrete functions that generates IR.You need to call builder.emit()

    public static ValueRef IRBuildCalc(IRBuilder builder, ValueRef lhsValRef, ValueRef rhsValRef, String name, String calcType) {
        if (lhsValRef instanceof ConstIntValueRef && rhsValRef instanceof ConstIntValueRef) {
            int lhs = Integer.valueOf(lhsValRef.getText());
            int rhs = Integer.valueOf(rhsValRef.getText());
            switch (calcType) {
                case ADD:
                    return new ConstIntValueRef(lhs + rhs);
                case SUB:
                    return new ConstIntValueRef(lhs - rhs);
                case MUL:
                    return new ConstIntValueRef(lhs * rhs);
                case SDIV:
                    return new ConstIntValueRef(lhs / rhs);
                default:
                    System.err.println("IRBuildCalc wrong!");
            }
        } else if (lhsValRef instanceof ConstFloatValueRef && rhsValRef instanceof ConstFloatValueRef) {
            float lhs = Float.valueOf(lhsValRef.getText());
            float rhs = Float.valueOf(rhsValRef.getText());
            switch (calcType) {
                case FADD:
                    return new ConstFloatValueRef(lhs + rhs);
                case FSUB:
                    return new ConstFloatValueRef(lhs - rhs);
                case FMUL:
                    return new ConstFloatValueRef(lhs * rhs);
                case FDIV:
                    return new ConstFloatValueRef(lhs / rhs);
                default:
                    System.err.println("IRBuildCalc wrong!");
            }
        }
        ValueRef resRegister;
        Type resType = int32Type;
        if (lhsValRef.getType() == floatType || rhsValRef.getType() == floatType) {
            resType = floatType;
        }
        if (lhsValRef.getType() == int32Type && rhsValRef.getType() == floatType) {
            lhsValRef = typeTrans(builder, lhsValRef, SiToFp);
        } else if (lhsValRef.getType() == floatType && rhsValRef.getType() == int32Type) {
            rhsValRef = typeTrans(builder, rhsValRef, SiToFp);
        }
        resRegister = new BaseRegister(name, resType);
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhsValRef, rhsValRef), builder.currentBaseBlock, calcType));
        builder.emit(resRegister.getText() + " = " + calcType + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText());
        return resRegister;
    }

    public static ValueRef IRBuildNeg(IRBuilder builder, ValueRef valueRef, String name) {
        // appendInstruction in sub
        if(valueRef instanceof ConstIntValueRef){
            return new ConstIntValueRef(- Integer.parseInt(valueRef.getText()));
        }
        if(valueRef instanceof ConstFloatValueRef){
            return new ConstFloatValueRef(- Float.parseFloat(valueRef.getText()));
        }
        if(valueRef instanceof BaseRegister){
            if(valueRef.getType() == floatType){
                return IRBuildCalc(builder, new ConstFloatValueRef(0), valueRef, name, FSUB);
            }else{
                return IRBuildCalc(builder, new ConstIntValueRef(0), valueRef, name, SUB);
            }
        }
        /*if(valueRef.getType() == floatType){
            return IRBuildCalc(builder, new ConstFloatValueRef(0), valueRef, name, FSUB);
        }
        return IRBuildCalc(builder, new ConstIntValueRef(0), valueRef, name, SUB);*/
        return null;
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
        if(valueRef.getType() == type){
            return valueRef;
        }
        if (valueRef instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(valueRef.getText()));
        }
        ValueRef resRegister = new BaseRegister(name, type);
        builder.appendInstr(new ZextInstruction(generateList(resRegister, valueRef, new ConstIntValueRef(0, type)), builder.currentBaseBlock));
        builder.emit(resRegister.getText() + " = " + ZEXT + " " + valueRef.getTypeText() + " " + valueRef.getText() + " to " + type.getText());
        return resRegister;
    }

    public static ValueRef IRBuildXor(IRBuilder builder, ValueRef lhs, ValueRef rhs, String name) {
        if (lhs instanceof ConstIntValueRef) {
            return new ConstIntValueRef(Integer.valueOf(lhs.getText()) ^ 1);
        }
        ValueRef resRegister = new BaseRegister(name, rhs.getType());
        builder.appendInstr(new CalculateInstruction(generateList(resRegister, lhs, rhs), builder.currentBaseBlock, XOR));
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
        PointerType pointerType = null;
        if (valueRef.getType() == int32Type && ((PointerType) pointer.getType()).getBaseType() == floatType) {
            valueRef = typeTrans(builder, valueRef, SiToFp);
            pointerType = new PointerType(valueRef.getType());
        } else if (valueRef.getType() == floatType && ((PointerType) pointer.getType()).getBaseType() == int32Type) {
            // valueRef = typeTrans(builder, valueRef, FpToSi);
            pointerType = new PointerType(valueRef.getType());
        } else {
            pointerType = new PointerType(valueRef.getType());
        }
        resRegister = new BaseRegister("temp", pointerType);
        builder.appendInstr(new StoreInstruction(generateList(resRegister, valueRef, pointer), builder.currentBaseBlock));
        builder.emit(STORE + " " + valueRef.getTypeText() + " " + valueRef.getText() +
                ", " + pointer.getTypeText() + " " + pointer.getText(), 4);
        return resRegister;
    }

    public static ValueRef typeTrans(IRBuilder builder, ValueRef origin, int type) {
        PointerType pointerType = null;
        ValueRef resRegister = null;
        if (type == FpToSi && origin.getType() == floatType) {
            resRegister = new BaseRegister("conv", int32Type);
            builder.emit(resRegister.getText() + " = fptosi float " +
                    origin.getText() + " to i32");
            builder.appendInstr(new TypeTransInstruction(generateList(origin, resRegister), builder.currentBaseBlock
                    , FpToSi));
        } else if (type == SiToFp && origin.getType() == int32Type) {
            resRegister = new BaseRegister("conv", floatType);
            builder.emit(resRegister.getText() + " = sitofp i32 "
                    + origin.getText() + " to float");
            builder.appendInstr(new TypeTransInstruction(generateList(origin, resRegister), builder.currentBaseBlock,
                    SiToFp));
        } else {
            System.err.println("typeTrans wrong!");
        }
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
        //todo: array type
        Type baseType = new PointerType(type);
        ValueRef resRegister = new GlobalRegister(globalVarName, baseType);
        Symbol globalSym = new Symbol(globalVarName, type);
        module.addGlobalSymbol(globalVarName, globalSym);
        module.emitWithoutLF(resRegister.getText() + " = " + GLOBAL + " " + ((PointerType) resRegister.getType()).getBaseType().getText() + " ");
        return resRegister;
    }

    public static ValueRef IRAddLocal(IRModule module, Type type, String localVarName){
        Type baseType = new PointerType(type);
        ValueRef resRegister = new GlobalRegister(localVarName, baseType);
        //symbol
        module.emitWithoutLF(resRegister.getText() + " = " + LOCAL + " " + ((PointerType) resRegister.getType()).getBaseType().getText() + " ");
        return resRegister;
    }

    public static void IRSetInitializer(IRModule module, ValueRef globalVar, ValueRef constRef, String globalName) {
        if(constRef instanceof ConstIntValueRef) {
            int initValue = Integer.valueOf(constRef.getText());
            module.getGlobalSymbol(globalName).setIntInitValue(initValue);
        }
        if(constRef instanceof ConstFloatValueRef){
            Float initValue = Float.valueOf(constRef.getText());
            module.getGlobalSymbol(globalName).setInitValue(initValue);
        }
        module.emit(constRef.getText());
    }



    public static void IRSetInitializer(IRModule module, ValueRef valueRef, List<ValueRef> constValueRefList, String globalVarName) {
        boolean flag = true;
        if(constValueRefList.size()>0) {
            if (constValueRefList.get(0).getType() == floatType) {
                List<Float> initValue = new ArrayList<>();
                for (int i = 0; i < constValueRefList.size(); i++) {
                    if (!Objects.equals(constValueRefList.get(i).getText(), "0")) {
                        flag = false;
                        initValue.add(Float.valueOf(constValueRefList.get(i).getText()));
                    }
                }
                module.getGlobalSymbol(globalVarName).setInitValue(initValue);
                module.getGlobalSymbol(globalVarName).setZero(flag);
            } else if (constValueRefList.get(0).getType() == int32Type) {
                List<Integer> initValue = new ArrayList<>();
                for (int i = 0; i < constValueRefList.size(); i++) {
                    if (!Objects.equals(constValueRefList.get(i).getText(), "0")) {
                        flag = false;
                        initValue.add(Integer.valueOf(constValueRefList.get(i).getText()));
                    }
                }
                module.getGlobalSymbol(globalVarName).setIntinitValue(initValue);
                module.getGlobalSymbol(globalVarName).setZero(flag);
            }
        }
        if (flag) {
            module.emit("zeroinitializer");
            return;
        }
        Type elementType = ((PointerType) valueRef.getType()).getBaseType();
        StringBuilder emitStr = new StringBuilder();
        int paramCount = 1;
        List<Integer> paramList = new ArrayList<Integer>();
        while (elementType instanceof ArrayType) {
            paramCount *= ((ArrayType) elementType).getElementNumber();
            paramList.add(((ArrayType) elementType).getElementNumber());
            elementType = ((ArrayType) elementType).getElementType();
        }
        String typeStr = null;
        if (elementType == int32Type) {
            typeStr = int32Type.getText();
        } else {
            typeStr = floatType.getText();
        }
        int lastLength = paramList.get(paramList.size() - 1);
        elementType = new ArrayType(elementType, lastLength);
        int temp = constValueRefList.size() / lastLength;
        int counter = 0;
        int counter1 = 0;
        for(int i = 0;i<paramList.size()-1;i++){
            emitStr.append("[");
        }
        for (int i = 0; i < temp; i++) {
            if(paramList.size()!=1){
                emitStr.append(elementType.getText());
            }

            boolean flg = true;
            for(int j = 0; j < lastLength; j++){
                if(!constValueRefList.get(counter1++).getText().equals("0")){
                    flg = false;
                    break;
                }
            }
            if (flg){
                emitStr.append(" zeroinitializer,");
                counter =counter1;
            }else {
                emitStr.append(" [");
                for (int j = 0; j < lastLength; j++) {


                    if (j == lastLength - 1) {
                        emitStr.append(typeStr + " " + constValueRefList.get(counter++).getText());
                        break;
                    }
                    emitStr.append(typeStr + " " + constValueRefList.get(counter++).getText() + ", ");
                }
                counter1 = counter;
                if(i==temp-1){
                    emitStr.append("]");
                }else{
                    emitStr.append("], ");
                }

            }
        }
        for(int i = 0;i<paramList.size()-1;i++){
            emitStr.append("]");
        }
        if(constValueRefList.size()>0) {
            if (constValueRefList.get(0).getType() == floatType) {
                List<Float> initValues = new ArrayList<>();
                for (ValueRef constFloat : constValueRefList) {
                    initValues.add(((ConstFloatValueRef) constFloat).getValue());
                }
                module.getGlobalSymbol(((GlobalRegister) valueRef).getIdentity()).setInitValue(initValues);
            } else if (constValueRefList.get(0).getType() == int32Type) {
                List<Integer> initValues = new ArrayList<>();
                for (ValueRef constInt : constValueRefList) {
                    initValues.add(((ConstIntValueRef) constInt).getValue());
                }
                module.getGlobalSymbol(((GlobalRegister) valueRef).getIdentity()).setIntinitValue(initValues);
            }
        }
        module.emit(emitStr.toString());
    }

    public static void IRBuildBr(IRBuilder builder, BaseBlock block) {
        builder.appendInstr(new BrInstruction(generateList(block), builder.currentBaseBlock));
        builder.emit(BR + " label %" + block.getLabel());
    }

    public static void IRBuildCondBr(IRBuilder builder, ValueRef condition, BaseBlock ifTrue, BaseBlock ifFalse) {
        builder.appendInstr(new BrInstruction(generateList(condition, ifTrue, ifFalse), builder.currentBaseBlock));
        builder.emit(BR + " " + condition.getTypeText() + " " + condition.getText() + ", " +
                "label %" + ifTrue.getLabel() + ", label %" + ifFalse.getLabel());
    }

    public static ValueRef IRBuildICmp(IRBuilder builder, int icmpType, ValueRef lhs, ValueRef rhs, String text) {
        Type resType = int32Type;
        if (lhs.getType() == floatType || rhs.getType() == floatType) {
            resType = floatType;
        }
        if (lhs.getType() == int32Type && rhs.getType() == floatType) {
            lhs = typeTrans(builder, lhs, SiToFp);
        } else if (lhs.getType() == floatType && rhs.getType() == int32Type) {
            rhs = typeTrans(builder, rhs, SiToFp);
        }
        ValueRef resRegister = new BaseRegister(text, int1Type);
        builder.appendInstr(new CondInstruction(generateList(resRegister, lhs, rhs), builder.currentBaseBlock, icmpType));
        builder.emit(resRegister.getText() + " = " + ICMP + " " + ICMPCodes[icmpType] + " "
                + lhs.getTypeText() + " " + lhs.getText() + ", " + rhs.getText());
        return resRegister;
    }

    public static ValueRef IRBuildAnd(IRBuilder builder, ValueRef lhs, ValueRef rhs, String text){
        ValueRef resRegister = new BaseRegister(text, lhs.getType());
        builder.emit(resRegister.getText() + " = " + AND + " " + lhs.getTypeText()+ " " + lhs.getText() + ", " + rhs.getText());
        return resRegister;

    }

    public static ValueRef IRBuildOr(IRBuilder builder, ValueRef lhs, ValueRef rhs, String text){
        ValueRef resRegister = new BaseRegister(text, lhs.getType());
        builder.emit(resRegister.getText() + " = " + OR + " " + lhs.getTypeText() + " " + lhs.getText() + ", " + rhs.getText());
        return resRegister;

    }


    public static ValueRef IRBuildGEP(IRBuilder builder, ValueRef valueRef, List<ValueRef> indexs, int indexSize, String varName) {
        Type baseType = ((PointerType) valueRef.getType()).getBaseType();
        Type resType;
        if (baseType instanceof ArrayType && indexSize != 1) {
            resType = ((ArrayType) baseType).getElementType();
        } else {
            resType = baseType;
        }
//        Type assign = baseType;
//        while(assign instanceof PointerType || assign instanceof ArrayType){
//            if(assign instanceof PointerType){
//                assign = ((PointerType) assign).getBaseType();
//            }
//            if(assign instanceof ArrayType){
//                assign = ((ArrayType) assign).getElementType();
//            }
//        }
        resType = new PointerType(resType);

        ValueRef resRegister = new BaseRegister(varName, resType);

        StringBuilder indexStrBuilder = new StringBuilder();
        List<ValueRef> operands = new ArrayList<>();
        operands.add(resRegister);
        operands.add(valueRef);

        for (ValueRef index : indexs) {
            operands.add(index);
            indexStrBuilder.append(", ").append(int32Type.getText())
                    .append(" ").append(index.getText());
        }
        builder.appendInstr(new GetElemPtrInstruction(operands, builder.currentBaseBlock));
        builder.emit(resRegister.getText() + " = " + GETPTR + " " + baseType.getText() + ", "
                + valueRef.getTypeText() + " " + valueRef.getText() + indexStrBuilder.toString());
        return resRegister;
    }

    public static ValueRef IRBuildCall(IRBuilder builder, FunctionBlock function, List<ValueRef> args, int argc, String varName) {
        Type retType = ((FunctionType) function.getType()).getRetType();
        ValueRef resRegister = new BaseRegister(varName+"_call", retType);
        StringBuilder stringBuilder = new StringBuilder();
        List<ValueRef> operands = new ArrayList<>();
        operands.add(resRegister);
        operands.add(function);
        if (retType != voidType) {
            stringBuilder.append(resRegister.getText()).append(" = ");
        }
        stringBuilder.append(CALL).append(" ").append(function.getRetType().getText())
                .append(" ").append(function.getText()).append("(");
        if (argc > 0) {
            operands.add(args.get(0));
            stringBuilder.append(args.get(0).getTypeText()).append(" ").append(args.get(0).getText());
        }
        for (int i = 1; i < argc; i++) {
            operands.add(args.get(i));
            stringBuilder.append(", ")
                    .append(args.get(i).getTypeText())
                    .append(" ").append(args.get(i).getText());
        }
        builder.appendInstr(new CallInstruction(operands, builder.currentBaseBlock));
        builder.currentBaseBlock.addSuccBaseBlock(function);
        function.addCaller((ValueRef) builder.currentBaseBlock);
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

    private static List<ValueRef> generateList(ValueRef valueRef) {
        List<ValueRef> list = new ArrayList<>();
        list.add(valueRef);
        return list;
    }

    private static List<ValueRef> generateList(ValueRef origin, ValueRef res) {
        List<ValueRef> list = new ArrayList<>();
        list.add(origin);
        list.add(res);
        return list;
    }

    private static List<ValueRef> generateList(ValueRef resRegister, ValueRef left, ValueRef right) {
        List<ValueRef> list = new ArrayList<>();
        list.add(resRegister);
        list.add(left);
        list.add(right);
        return list;
    }
}
