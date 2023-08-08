import IRBuilder.*;
import Scope.GlobalScope;
import Scope.LocalScope;
import Scope.Scope;
import Type.ArrayType;
import Type.FunctionType;
import Type.PointerType;
import Type.Type;
import antlr.*;

import java.lang.reflect.Field;
import java.util.*;

import static IRBuilder.BaseBlock.IRAppendBasicBlock;
import static IRBuilder.IRBuilder.*;
import static IRBuilder.IRConstants.*;
import static IRBuilder.IRModule.IRAddFunction;
import static IRBuilder.IRModule.IRModuleCreateWithName;
import static Type.FloatType.IRFloatType;
import static Type.Int1Type.IRInt1Type;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;

public class IRGenVisitor extends SysYParserBaseVisitor<ValueRef> {
    IRModule module = IRModuleCreateWithName("module");
    IRBuilder builder = IRCreateBuilder();
    private final Map<String,Integer> ConstIntVarMap = new LinkedHashMap<>();
    private final Map<String, Float> ConstFloatVarMap = new LinkedHashMap<>();
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private static final Type voidType = IRVoidType();
    private GlobalScope globalScope = null;
    private Scope currentScope = null;
    private BaseBlock currentBlock = null;
    private int localScopeCounter = 0;
    private FunctionBlock currentFunction = null;
    private Stack<BaseBlock> conditionStack = new Stack<>();
    private Stack<BaseBlock> afterStack = new Stack<>();
    private ValueRef intZero = new ConstIntValueRef(0);
    private ValueRef floatZero = new ConstFloatValueRef(0);
    private boolean arrayAddr = false;
    private boolean tempBool = false;
    public IRModule getModule() {
        return module;
    }

    @Override
    public ValueRef visitProgram(SysYParser.ProgramContext ctx) {
        globalScope = new GlobalScope("globalScope", null);
        currentScope = globalScope;
        addLibs(globalScope);
        return super.visitProgram(ctx);
    }

    private void addLibs(GlobalScope globalScope){
        FunctionType getIntType = new FunctionType(new ArrayList<>(), int32Type);
        globalScope.define("getint", addLib("getint", getIntType), getIntType);

        FunctionType getchType = new FunctionType(new ArrayList<>(), int32Type);
        globalScope.define("getch", addLib("getch", getchType), getchType);

        FunctionType getFloatType = new FunctionType(new ArrayList<>(), floatType);
        globalScope.define("getfloat", addLib("getfloat", getFloatType), getFloatType);

        List<Type> getArrayTypeParams = new ArrayList<>();
        getArrayTypeParams.add(new PointerType(int32Type));
        FunctionType getArrayType = new FunctionType(getArrayTypeParams, int32Type);
        globalScope.define("getarray", addLib("getarray", getArrayType), getArrayType);

        List<Type> getFArrayTypeParams = new ArrayList<>();
        getFArrayTypeParams.add(new PointerType(floatType));
        FunctionType getFArrayType = new FunctionType(getFArrayTypeParams, int32Type);
        globalScope.define("getfarray", addLib("getfarray", getFArrayType), getFArrayType);

        List<Type> putIntTypeParams = new ArrayList<>();
        putIntTypeParams.add(int32Type);
        FunctionType putIntType = new FunctionType(putIntTypeParams, voidType);
        globalScope.define("putint", addLib("putint", putIntType), putIntType);

        List<Type> putChTypeParams = new ArrayList<>();
        putChTypeParams.add(int32Type);
        FunctionType putChType = new FunctionType(putChTypeParams, voidType);
        globalScope.define("putch", addLib("putch", putChType), putChType);

        List<Type> putArrayTypeParams = new ArrayList<>();
        putArrayTypeParams.add(int32Type);
        putArrayTypeParams.add(new PointerType(int32Type));
        FunctionType putArrayType = new FunctionType(putArrayTypeParams, voidType);
        globalScope.define("putarray", addLib("putarray", putArrayType), putArrayType);

        List<Type> putFloatTypeParams = new ArrayList<>();
        putFloatTypeParams.add(floatType);
        FunctionType putFloatType = new FunctionType(putFloatTypeParams, voidType);
        globalScope.define("putfloat", addLib("putfloat", putFloatType), putFloatType);

        List<Type> putFArrayTypeParams = new ArrayList<>();
        putFArrayTypeParams.add(int32Type);
        putFArrayTypeParams.add(new PointerType(floatType));
        FunctionType putFArrayType = new FunctionType(putFArrayTypeParams, voidType);
        globalScope.define("putfarray", addLib("putfarray", putFArrayType), putFArrayType);

        // todo: putf

        FunctionType beforeMainType = new FunctionType(new ArrayList<>(), voidType);
        globalScope.define("before_main", addLib("before_main", beforeMainType), beforeMainType);

        FunctionType afterMainType = new FunctionType(new ArrayList<>(), voidType);
        globalScope.define("after_main", addLib("after_main", afterMainType), afterMainType);

        List<Type> startTimeTypeParams = new ArrayList<>();
        startTimeTypeParams.add(int32Type);
        FunctionType startTimeType = new FunctionType(startTimeTypeParams, voidType);
        globalScope.define("start_time", addLib("start_time", startTimeType), startTimeType);

        List<Type> endTimeTypeParams = new ArrayList<>();
        endTimeTypeParams.add(int32Type);
        FunctionType endTimeType = new FunctionType(endTimeTypeParams, voidType);
        globalScope.define("end_time", addLib("end_time", endTimeType), endTimeType);

    }

    private FunctionBlock addLib(String libName, FunctionType libType){
        FunctionBlock function = new FunctionBlock(libName, libType);
        // Symbol funcSym = new Symbol(libName, libType);
        // module.addGlobalSymbol(libName, funcSym);
        return function;
    }

    private Type defineType(String typeName) {
        if (typeName.equals("int")) return int32Type;
        else if (typeName.equals("float")) return floatType;
        return IRVoidType();
    }

    @Override
    public ValueRef visitFuncDef(SysYParser.FuncDefContext ctx) {
        String funcName = ctx.IDENT().getText();
        Type returnType;
        int paramsCount = 0;
        if (ctx.funcFParams() != null) paramsCount = ctx.funcFParams().funcFParam().size();
        List<Type> paramsType = new ArrayList<Type>(paramsCount);
        String returnTypeName = ctx.funcType().getText();
        returnType = defineType(returnTypeName); //funcType(int, float, void)
        // func params analyze
        for (int i = 0; i < paramsCount; i++) {
            String paramTypeName = ctx.funcFParams().funcFParam(i).bType().getText();
            if (ctx.funcFParams().funcFParam(i).L_BRACKT(0) != null) {
                if(ctx.funcFParams().funcFParam(i).L_BRACKT().size()>1){
                    Type baseType = defineType(paramTypeName);
                    for(int j = 1;j<ctx.funcFParams().funcFParam(i).L_BRACKT().size();j++){
                        ValueRef tempValue= ctx.funcFParams().funcFParam(i).exp(j-1).accept(this);
                        if(tempValue instanceof ConstIntValueRef){
                            baseType = new ArrayType(baseType, Integer.parseInt(((ConstIntValueRef) tempValue).getText()));
                        }else{
                            baseType = new ArrayType(baseType,Integer.parseInt(ctx.funcFParams().funcFParam(i).exp(j-1).getText()));
                        }
                    }
                    paramsType.add(new PointerType(baseType));
                }else {
                    paramsType.add(new PointerType(defineType(paramTypeName)));
                }
            } else {
                paramsType.add(defineType(paramTypeName));
            }
        }
        FunctionType functionType = new FunctionType(paramsType, returnType);
        currentFunction = IRAddFunction(module, funcName, functionType);
        IRPositionBuilderAtEnd(builder, IRAppendBasicBlock(currentFunction, funcName + "Entry"));
        globalScope.define(funcName, currentFunction, functionType);
        currentScope = new LocalScope(funcName + "Scope", currentScope);
        for (int i = 0; i < paramsCount; i++) {
            String paramName = ctx.funcFParams().funcFParam(i).IDENT().getText();
            String paramTypeName = ctx.funcFParams().funcFParam(i).bType().getText();

            Type paramType = currentFunction.getParam(i).getType();
//            if (ctx.funcFParams().funcFParam(i).L_BRACKT().size() > 0) {
//                paramType = new PointerType(paramType);
//            }
            ValueRef paramPointer = IRBuildAlloca(builder, paramType, paramName);
            ValueRef param = IRGetParam(currentFunction, i);
            IRBuildStore(builder, param, paramPointer);
            currentScope.define(paramName, paramPointer, paramType);
        }

        ValueRef ret = super.visitFuncDef(ctx);
        currentScope = currentScope.getEnclosingScope();

        if (returnType.equals(voidType)) {
            IRBuildRet(builder, null);
        } else {
            IRBuildRet(builder, intZero);
        }

        return ret;
    }

    @Override
    public ValueRef visitBlock(SysYParser.BlockContext ctx) {
        String scopeName = "localScope" + localScopeCounter;
        localScopeCounter++;
        currentScope = new LocalScope(scopeName, currentScope);
        ValueRef ret = super.visitBlock(ctx);
        currentScope = currentScope.getEnclosingScope();
        return ret;
    }

    @Override
    public ValueRef visitConstDecl(SysYParser.ConstDeclContext ctx) {
        String typeName = ctx.bType().getText();
        ValueRef assign;
        for (SysYParser.ConstDefContext constDefContext : ctx.constDef()) {
            Type type = defineType(typeName);
            ValueRef constVariable;
            String constName = constDefContext.IDENT().getText();
            if(constName.length()>20){
                constName = constName.substring(0,20);
            }
            // 初始化assign的值
            if (typeName.equals("int")) assign = intZero;
            else assign = floatZero;
            // 求type，反向加载数组，迭代求解type
            List<Integer> paramCount = new ArrayList<>();
            for (SysYParser.ConstExpContext constExpContext : constDefContext.constExp()) {
                paramCount.add(Integer.parseInt(constExpContext.getText()));
            }
            for (int i = paramCount.size() - 1; i >= 0; i--) {
                type = new ArrayType(type, paramCount.get(i));
            }
            init = new ArrayList<>();
            if (type instanceof ArrayType) elementDimension = ((ArrayType) type).getElementDimension();
            curDim = 0;
            if (currentScope instanceof GlobalScope) {
                constVariable = IRAddGlobal(module, type, constName);
                if (paramCount.size() == 0) {
                    if (constDefContext.ASSIGN() != null) assign = constDefContext.constInitVal().accept(this);
                    if(assign.getType()!=type){
                        if(assign.getType() == floatType){
                            assign = new ConstIntValueRef((int)(Float.parseFloat(assign.getText())));
                        }else if(assign.getType() == int32Type){
                            assign = new ConstFloatValueRef(Float.parseFloat(assign.getText()));
                        }
                    }
                    if(assign instanceof ConstIntValueRef){
                        ConstIntVarMap.put(constVariable.getText(),Integer.parseInt(((ConstIntValueRef)assign).getText()));
                    }
                    if (assign instanceof ConstFloatValueRef) {
                        ConstFloatVarMap.put(constVariable.getText(),Float.parseFloat(((ConstFloatValueRef)assign).getText()));
                    }


                    IRSetInitializer(module, constVariable, assign, constName);
                } else {
                    if(constDefContext.ASSIGN() != null) visitConstInitVal(constDefContext.constInitVal());
                    IRSetInitializer(module, constVariable, init,constName);
                }
            } else {
                constVariable = IRBuildAlloca(builder, type, constName);
                if (paramCount.size() == 0) {
                    if (constDefContext.ASSIGN() != null) assign = constDefContext.constInitVal().accept(this);
                    if(assign.getType()!=type){
                        if(assign.getType() == floatType){
                            assign = typeTrans(builder,assign,FpToSi);
                        }else if(assign.getType() == int32Type){
                            assign = typeTrans(builder,assign,SiToFp);
                        }
                    }
                    ValueRef storeRes = IRBuildStore(builder, assign, constVariable);
                    if(assign instanceof ConstIntValueRef) {
                        ConstIntVarMap.put(constVariable.getText(), Integer.parseInt(((ConstIntValueRef) assign).getText()));
                    }
                    if (assign instanceof ConstFloatValueRef) {
                        ConstFloatVarMap.put(constVariable.getText(),Float.parseFloat(((ConstFloatValueRef)assign).getText()));
                    }
                } else {
                    //TODO: 正确性验证
                    if (constDefContext.ASSIGN() != null) {
                        if (constDefContext.constInitVal().constInitVal().size() != 0) {
                            if (constDefContext.ASSIGN() != null) visitConstInitVal(constDefContext.constInitVal());

                            List<ValueRef> arrayPtr = new ArrayList<ValueRef>(elementDimension.size());
                            for (int i = 0; i < init.size(); i++) {

                                int totalCount = init.size();
                                int temp = 1;
                                int counter = i;
                                if (!Objects.equals(init.get(i).getText(), "0")) {
                                    arrayPtr.add(new ConstIntValueRef(0));
                                    for (int j = 0; j < elementDimension.size(); j++) {
                                        totalCount /= elementDimension.get(j);
                                        arrayPtr.add(new ConstIntValueRef(counter / totalCount));
                                        counter -= (counter / totalCount) * totalCount;
                                    }
                                    int counter1 = 1;
                                    List<ValueRef> paramList = new ArrayList<ValueRef>();
                                    paramList.add(intZero);
                                    paramList.add(arrayPtr.get(counter1++));
                                    ValueRef elementPtr = IRBuildGEP(builder, constVariable, paramList, 2, "array");
                                    for (int j = 0; j < elementDimension.size() - 1; j++) {
                                        paramList.clear();
                                        paramList.add(intZero);
                                        paramList.add(arrayPtr.get(counter1++));
                                        elementPtr = IRBuildGEP(builder, elementPtr, paramList, 2, "array");
                                    }

                                    IRBuildStore(builder, init.get(i), elementPtr);
                                    arrayPtr.clear();
                                }
                            }
                        }
                    }
                }
            }
            currentScope.define(constName, constVariable, type);
        }
        return null;
    }

    // 处理数组的赋值
    List<ValueRef> init = new ArrayList<>(); // 存数组的初始值
    List<Integer> elementDimension; // 数组每层的维度 a[4][6][3] {4, 6, 3}
    Integer curDim; // 当前位于的层数 curDim = 0/1/2

    @Override
    public ValueRef visitVarDecl(SysYParser.VarDeclContext ctx) {
        String typeName = ctx.bType().getText();
        ValueRef assign;
        for (SysYParser.VarDefContext varDefContext : ctx.varDef()) {
            Type type = defineType(typeName);
            ValueRef variable;
            String variableName = varDefContext.IDENT().getText();
            if(variableName.length()>20){
                variableName = variableName.substring(0,20);
            }
            // 初始化assign的值
            if (typeName.equals("int")) assign = intZero;
            else assign = floatZero;
            // 求type，反向加载数组，迭代求解type
            List<Integer> paramCount = new ArrayList<>();
            for (SysYParser.ConstExpContext constExpContext : varDefContext.constExp()) {
                ValueRef temp = this.visit(constExpContext.exp());

                paramCount.add(Integer.parseInt(temp.getText()));
            }
            for (int i = paramCount.size() - 1; i >= 0; i--) {
                type = new ArrayType(type, paramCount.get(i));
            }
            init = new ArrayList<>();
            if (type instanceof ArrayType) elementDimension = ((ArrayType) type).getElementDimension();
            curDim = 0;
            if (currentScope instanceof GlobalScope) {
                variable = IRAddGlobal(module, type, variableName);
                if (paramCount.size() == 0) {

                    if (varDefContext.ASSIGN() != null) assign = varDefContext.initVal().accept(this);
                    if(assign.getType()!=type){
                        if(assign.getType() == floatType){
                            assign = typeTrans(builder,assign,FpToSi);
                        }else if(assign.getType() == int32Type){
                            assign = typeTrans(builder,assign,SiToFp);
                        }
                    }
                    IRSetInitializer(module, variable, assign, variableName);
                } else {
                    if (varDefContext.ASSIGN() != null) visitInitVal(varDefContext.initVal());
//                    for (int i = 0; i < init.size(); i++) System.err.println(init.get(i).getText());
                    IRSetInitializer(module, variable, init,variableName);
                }
            } else {
                variable = IRBuildAlloca(builder, type, variableName);
                //TODO: check

                if (paramCount.size() == 0) {
                    if (varDefContext.ASSIGN() != null) assign = varDefContext.initVal().accept(this);
                    if(assign.getType()!=type){
                        if(assign.getType() == floatType){
                            assign = typeTrans(builder,assign,FpToSi);
                        }else if(assign.getType() == int32Type){
                            assign = typeTrans(builder,assign,SiToFp);
                        }
                    }
                    IRBuildStore(builder, assign, variable);
                } else {

                    //TODO: 正确性验证
                    if (varDefContext.ASSIGN() != null) {
                        if (varDefContext.initVal().initVal().size() != 0) {
                            if (varDefContext.ASSIGN() != null) visitInitVal(varDefContext.initVal());

                            List<ValueRef> arrayPtr = new ArrayList<ValueRef>(elementDimension.size());
                            for (int i = 0; i < init.size(); i++) {

                                int totalCount = init.size();
                                int temp = 1;
                                int counter = i;
                                if(!Objects.equals(init.get(i).getText(), "0")) {
                                    arrayPtr.add(new ConstIntValueRef(0));
                                    for (int j = 0; j < elementDimension.size(); j++) {
                                        totalCount /= elementDimension.get(j);
                                        arrayPtr.add(new ConstIntValueRef(counter / totalCount));
                                        counter -= (counter / totalCount) * totalCount;
                                    }
                                    int counter1 = 1;
                                    List<ValueRef> paramList = new ArrayList<ValueRef>();
                                    paramList.add(intZero);
                                    paramList.add(arrayPtr.get(counter1++));
                                    ValueRef elementPtr = IRBuildGEP(builder, variable, paramList, 2, "array");
                                    for (int j = 0; j < elementDimension.size() - 1; j++) {
                                        paramList.clear();
                                        paramList.add(intZero);
                                        paramList.add(arrayPtr.get(counter1++));
                                        elementPtr = IRBuildGEP(builder, elementPtr, paramList, 2, "array");
                                    }

                                    IRBuildStore(builder, init.get(i), elementPtr);
                                    arrayPtr.clear();
                                }
                            }
                        }
                    }
                }
            }
            currentScope.define(variableName, variable, type);
        }
        return null;
    }

    @Override
    public ValueRef visitConstInitVal(SysYParser.ConstInitValContext ctx) {
        if (ctx.constExp() != null) return ctx.constExp().exp().accept(this);
        // 处理initVal嵌套的情况
        int layerDim = curDim;
        boolean dump = false;
        int count = 0; // 本层已有的数目
        int fullCount = 0; // 需要进到上一层的数目
        for (SysYParser.ConstInitValContext initValContext : ctx.constInitVal()) {
            if (initValContext.constExp() != null) {
                // 没有{}的处理
                if (!dump) {
                    dump = true;
                    curDim = elementDimension.size() - 1;
                    count = 0;
                    fullCount = elementDimension.get(curDim);
                }
                init.add(initValContext.constExp().exp().accept(this));
            } else {
                int tmpDim = curDim;
                curDim = tmpDim + 1;
                visitConstInitVal(initValContext);
                curDim = tmpDim;
            }
            count++;
            if (count == fullCount) {
                dump = false;
                curDim--;
                if (curDim <= layerDim) curDim = layerDim;
                fullCount = elementDimension.get(curDim);
                int layerParamCount = 1;
                for (int i = curDim + 1; i < elementDimension.size(); i++) layerParamCount *= elementDimension.get(i);
                count = init.size() / layerParamCount;
            }
        }
        int totalParamCount = 1;
        for (int i = layerDim; i < elementDimension.size(); i++) {
            totalParamCount *= elementDimension.get(i);
        }
        boolean empty = (ctx.constInitVal().size() == 0);
        if (init.size() % totalParamCount != 0 || empty) {
            for (int i = init.size() % totalParamCount; i < totalParamCount; i++) init.add(intZero);
        }
        return null;
    }

    @Override
    public ValueRef visitInitVal(SysYParser.InitValContext ctx) {
        // 单独处理exp()
        if (ctx.exp() != null) return ctx.exp().accept(this);
        // 处理initVal嵌套的情况
        int layerDim = curDim;
        boolean dump = false;
        int count = 0; // 本层已有的数目
        int fullCount = 0; // 需要进到上一层的数目
        for (SysYParser.InitValContext initValContext : ctx.initVal()) {
            if (initValContext.exp() != null) {
                // 没有{}的处理
                if (!dump) {
                    dump = true;
                    curDim = elementDimension.size() - 1;
                    count = 0;
                    fullCount = elementDimension.get(curDim);
                }
                init.add(initValContext.exp().accept(this));
            } else {
                int tmpDim = curDim;
                curDim = tmpDim + 1;
                visitInitVal(initValContext);
                curDim = tmpDim;
            }
            count++;
            if (count == fullCount) {
                dump = false;
                curDim--;
                if (curDim <= layerDim) curDim = layerDim;
                fullCount = elementDimension.get(curDim);
                int layerParamCount = 1;
                for (int i = curDim + 1; i < elementDimension.size(); i++) layerParamCount *= elementDimension.get(i);
                count = init.size() / layerParamCount;
            }
        }
        int totalParamCount = 1;
        for (int i = layerDim; i < elementDimension.size(); i++) {
            totalParamCount *= elementDimension.get(i);
        }
        boolean empty = (ctx.initVal().size() == 0);
        if (init.size() % totalParamCount != 0 || empty) {
            for (int i = init.size() % totalParamCount; i < totalParamCount; i++) init.add(intZero);
        }
        return null;
    }

    @Override
    public ValueRef visitCallFuncExp(SysYParser.CallFuncExpContext ctx) {
        String funcName = ctx.IDENT().getText();
        FunctionBlock functionBlock = (FunctionBlock) globalScope.getValueRef(funcName);
        FunctionType functionType = (FunctionType) globalScope.getType(funcName);
        int argc = functionType.getParamsType().size();
        List<ValueRef> args = new ArrayList<>(argc);
        int floatNO = 0;
        int noFloatNO = 0;
        int spillIndex = 0;
        for (int i = 0; i < argc; i++) {
            ValueRef param = ctx.funcRParams().param(i).accept(this);
            param.setUsedByFunction(true);
            Type paramType = functionBlock.getParam(i).getType();
            Type baseType = paramType;
//            while(baseType instanceof ArrayType || baseType instanceof PointerType){
//                if(baseType instanceof ArrayType){
//                    baseType = ((ArrayType)baseType).getElementType();
//                }
//                if(baseType instanceof PointerType){
//                    baseType = ((PointerType) baseType).getBaseType();
//                }
//            }
            if(baseType == floatType){
                param.setFloatNO(floatNO);
                if (floatNO > 7) {
                    param.setSpillIndex(spillIndex);
                    spillIndex++;
                }

                floatNO++;
            }else{
                param.setNoFloatNO(noFloatNO);
                if (noFloatNO > 7) {
                    param.setSpillIndex(spillIndex);
                    spillIndex++;
                }
                noFloatNO++;
            }

            if(paramType instanceof PointerType) {
                if ((!(((PointerType)paramType).getBaseType() instanceof ArrayType))&& param.getType() instanceof PointerType &&((PointerType) param.getType()).getBaseType() instanceof ArrayType) {
                    List<ValueRef> indexes = new ArrayList<>();
                    indexes.add(intZero);
                    indexes.add(intZero);
                    param = IRBuildGEP(builder, param, indexes, indexes.size(), "new_ptr");
                }else if(!Objects.equals(paramType.getText(), param.getType().getText())){
                    List<ValueRef> indexes = new ArrayList<>();
                    indexes.add(intZero);
                    indexes.add(intZero);
                    param = IRBuildGEP(builder, param, indexes, indexes.size(), "new_ptr");
                }
            }

            args.add(i, param);
        }
        return IRBuildCall(builder, functionBlock, args, argc, funcName);
    }

    @Override
    public ValueRef visitReturnStmt(SysYParser.ReturnStmtContext ctx) {
        ValueRef result = null;
        if (ctx.exp() != null) {
            result = visit(ctx.exp());
        }
        IRBuildRet(builder, result);
        return result;
    }

    @Override
    public ValueRef visitNumberExp(SysYParser.NumberExpContext ctx) {
        return ctx.number().accept(this);
    }

    @Override
    public ValueRef visitNumber(SysYParser.NumberContext ctx) {
        String num = ctx.getText();
        if (ctx.INTEGER_CONST() != null) {
            return calculateInt(num);
        } else if (ctx.FLOAT_CONST() != null) {
            return calculateFloat(num);
        }
        return new ConstIntValueRef(0);
    }

    public ValueRef calculateInt(String number) {
        int num;
        if (number.startsWith("0x") || number.startsWith("0X")) {
            num = Integer.parseInt(number.substring(2), 16);
        } else if (number.startsWith("0") && number.length() != 1) {
            num = Integer.parseInt(number.substring(1), 8);
        } else {
            num = Integer.parseInt(number);
        }
        return new ConstIntValueRef(num);
    }

    public ValueRef calculateFloat(String number) {
        float num = Float.parseFloat(number);
        return new ConstFloatValueRef(num);
    }

    @Override
    public ValueRef visitMulExp(SysYParser.MulExpContext ctx) {
        ValueRef left = visit(ctx.exp(0));
        ValueRef right = visit(ctx.exp(1));
        if(ConstIntVarMap.get(left.getText())!=null){
            left = new ConstIntValueRef(ConstIntVarMap.get(left.getText()));
        }
        if (ConstFloatVarMap.get(left.getText())!=null) {
            left = new ConstFloatValueRef(ConstFloatVarMap.get(left.getText()));
        }
        if(ConstIntVarMap.get(right.getText())!=null){
            right = new ConstIntValueRef(ConstIntVarMap.get(right.getText()));
        }
        if (ConstFloatVarMap.get(right.getText())!=null) {
            right = new ConstFloatValueRef(ConstFloatVarMap.get(right.getText()));
        }
        if (ctx.MUL() != null) {
            if(left.getType() == int32Type && right.getType() == int32Type) {
                return IRBuildCalc(builder, left, right, "mul_", MUL);
            }else{
                return IRBuildCalc(builder, left, right, "fmul_", FMUL);
            }
        } else if (ctx.DIV() != null) {
            if(left.getType() == int32Type && right.getType() == int32Type) {
                return IRBuildCalc(builder, left, right, "sdiv_", SDIV);
            }else{
                return IRBuildCalc(builder, left, right, "fdiv_", FDIV);
            }
        } else if (ctx.MOD() != null) {
            return IRBuildSRem(builder, left, right, "srem_");
        }

        return null;
    }

    @Override
    public ValueRef visitPlusExp(SysYParser.PlusExpContext ctx) {
        ValueRef left = visit(ctx.exp(0));
        ValueRef right = visit(ctx.exp(1));
        if(ConstIntVarMap.get(left.getText())!=null){
            left = new ConstIntValueRef(ConstIntVarMap.get(left.getText()));
        }
        if (ConstFloatVarMap.get(left.getText())!=null) {
            left = new ConstFloatValueRef(ConstFloatVarMap.get(left.getText()));
        }
        if(ConstIntVarMap.get(right.getText())!=null){
            right = new ConstIntValueRef(ConstIntVarMap.get(right.getText()));
        }
        if (ConstFloatVarMap.get(right.getText())!=null) {
            right = new ConstFloatValueRef(ConstFloatVarMap.get(right.getText()));
        }
        if (ctx.PLUS() != null) {
            if(left.getType() == int32Type && right.getType() == int32Type) {
                return IRBuildCalc(builder, left, right, "add_", ADD);
            }else{
                return IRBuildCalc(builder, left, right, "fadd_", FADD);
            }
        } else if (ctx.MINUS() != null) {
            if(left.getType() == int32Type && right.getType() == int32Type) {
                return IRBuildCalc(builder, left, right, "sub_", SUB);
            }else{
                return IRBuildCalc(builder, left, right, "fsub_", FSUB);
            }
        }

        return null;
    }

    @Override
    public ValueRef visitUnaryOpExp(SysYParser.UnaryOpExpContext ctx) {
        String operator = ctx.unaryOp().getText();
        ValueRef operand = visit(ctx.exp());
        switch (operator) {
            case "+":
                return operand;
            case "-":
                return IRBuildNeg(builder, operand, "neg_");
            case "!":
                operand = IRBuildICmp(builder, 1, new ConstIntValueRef(0), operand, "icmp_");
                 operand = IRBuildXor(builder, operand, new ConstIntValueRef(1, int1Type), "xor_");
                 operand = IRBuildZExt(builder, operand, int32Type, "zext_");
                return operand;
            default:
                break;
        }

        return null;
    }

    @Override
    public ValueRef visitExpParenthesis(SysYParser.ExpParenthesisContext ctx) {
        return this.visit(ctx.exp());
    }

    @Override
    public ValueRef visitAssignStmt(SysYParser.AssignStmtContext ctx) {
        ValueRef lValPointer = this.visitLVal(ctx.lVal());
        ValueRef exp = this.visit(ctx.exp());
        return IRBuildStore(builder, exp, lValPointer);
    }
    boolean isaddr = false;
    @Override
    public ValueRef visitLvalExp(SysYParser.LvalExpContext ctx) {
        String variableName = ctx.lVal().IDENT().getText();
        if(variableName.length()>20){
            variableName = variableName.substring(0,20);
        }
        ValueRef variable = currentScope.getValueRef(variableName);
        Type varType = currentScope.getType(variableName);
        if(ConstIntVarMap.get(variable.getText())!=null){
            return new ConstIntValueRef(ConstIntVarMap.get(variable.getText()));
        }
        if(ConstFloatVarMap.get(variable.getText())!=null){
            return new ConstFloatValueRef(ConstFloatVarMap.get(variable.getText()));
        }
        ValueRef lValPointer = visitLVal(ctx.lVal());
        if(arrayAddr){
            arrayAddr = false;
            isaddr = true;
            return lValPointer;
        }
        return IRBuildLoad(builder,lValPointer,variableName);
    }

    @Override
    public ValueRef visitLVal(SysYParser.LValContext ctx) {
        String lValName = ctx.IDENT().getText();
        if(lValName.length()>20){
            lValName = lValName.substring(0,20);
        }
        ValueRef lValPointer = currentScope.getValueRef(lValName);
        Type lvalType = currentScope.getType(lValName);
/*        if (ctx.exp().size() == 0) {
            return lValPointer;
        } else {
            List<ValueRef> indexes = new ArrayList<>();
            if(lvalType instanceof  ArrayType) {
                indexes.add(intZero);
            }else if(lvalType instanceof PointerType){
            }
            for(SysYParser.ExpContext expContext: ctx.exp()){
                ValueRef index = expContext.accept(this);
                indexes.add(index);
            }
            lValPointer = IRBuildGEP(builder, lValPointer, indexes, indexes.size(), lValName);
        }*/

//        if(lvalType.getText().equals(new PointerType(int32Type).getText())){
//            lValPointer = IRBuildLoad(builder,lValPointer,lValName);
//        }
        if(lvalType == int32Type || lvalType == floatType){
            return lValPointer;
        }
        if(lvalType.getText().equals(new PointerType(int32Type).getText()) || lvalType.getText().equals(new PointerType(floatType).getText())){
            if(ctx.exp().isEmpty()){
                return lValPointer;
            }else{
                if(ctx.exp().size() == 1) {
                    List<ValueRef> indexes = new ArrayList<>();
                    ValueRef index = ctx.exp(0).accept(this);
                    indexes.add(index);
                    ValueRef pointer = IRBuildLoad(builder, lValPointer, lValName);
                    lValPointer = IRBuildGEP(builder, pointer, indexes, indexes.size(), lValName);
                }
            }
        }else if(lvalType instanceof PointerType &&( ((PointerType)lvalType).getBaseType() instanceof ArrayType || ((PointerType)lvalType).getBaseType() instanceof PointerType ) ){
            if(ctx.exp().isEmpty()){
                return lValPointer;
            }else{
                List<ValueRef> indexes = new ArrayList<>();
                ValueRef index = ctx.exp(0).accept(this);
                indexes.add(index);
                ValueRef pointer = IRBuildLoad(builder, lValPointer, lValName);
                lValPointer = IRBuildGEP(builder, pointer, indexes, indexes.size(), lValName);
                for (int i = 1;i<ctx.exp().size();i++) {
                    List<ValueRef> interIndexes = new ArrayList<>();
                    ValueRef interIndex = ctx.exp(i).accept(this);
                    interIndexes.add(intZero);
                    interIndexes.add(interIndex);
                    lValPointer = IRBuildGEP(builder, lValPointer, interIndexes, interIndexes.size(), lValName);
                }
            }
        }
        else{
            if(ctx.exp().size()==0){
                List<ValueRef> indexes = new ArrayList<>();
                indexes.add(intZero);
                indexes.add(intZero);
                lValPointer = IRBuildGEP(builder, lValPointer, indexes, indexes.size(), lValName);

                arrayAddr = true;
                return lValPointer;
            }

            for (SysYParser.ExpContext expContext : ctx.exp()) {
                List<ValueRef> indexes = new ArrayList<>();
                if (lvalType instanceof ArrayType) {
                    indexes.add(intZero);
                }
                ValueRef index = expContext.accept(this);
                indexes.add(index);
                lValPointer = IRBuildGEP(builder, lValPointer, indexes, indexes.size(), lValName);
            }
        }
        if(lValPointer.getType() instanceof ArrayType ||  (lValPointer.getType() instanceof PointerType) && (((PointerType)lValPointer.getType()).getBaseType() instanceof ArrayType)){
            arrayAddr = true;
        }

        return lValPointer;
    }

    @Override
    public ValueRef visitConditionStmt(SysYParser.ConditionStmtContext ctx) {
        ValueRef conditionVal = this.visit(ctx.cond());
         ValueRef cmpResult = IRBuildICmp(builder, 1, conditionVal, intZero, "icmp_");
        BaseBlock trueBlock = IRAppendBasicBlock(currentFunction, "trueBlock");
        BaseBlock falseBlock = IRAppendBasicBlock(currentFunction, "falseBlock");
        BaseBlock afterBlock = IRAppendBasicBlock(currentFunction, "afterBlock");

         IRBuildCondBr(builder, cmpResult, trueBlock, falseBlock);

        IRPositionBuilderAtEnd(builder, trueBlock);
        this.visit(ctx.stmt(0));
        IRBuildBr(builder, afterBlock);

        IRPositionBuilderAtEnd(builder, falseBlock);
        if (ctx.ELSE() != null) {
            this.visit(ctx.stmt(1));
        }
        IRBuildBr(builder, afterBlock);

        IRPositionBuilderAtEnd(builder, afterBlock);
        return null;
    }

    @Override
    public ValueRef visitLtCond(SysYParser.LtCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
        ValueRef cmpResult = null;

        if (ctx.LT() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSLT, lVal, rVal, "icmp_LT");
        } else if (ctx.LE() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSLE, lVal, rVal, "icmp_LE");
        } else if (ctx.GE() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSGE, lVal, rVal, "icmp_GE");
        } else if (ctx.GT() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSGT, lVal, rVal, "icmp_GT");
        }

        return IRBuildZExt(builder,cmpResult,int32Type,"tmp_");
    }

    @Override
    public ValueRef visitEqCond(SysYParser.EqCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
        ValueRef cmpResult = null;
        if (ctx.EQ() != null) {
            cmpResult = IRBuildICmp(builder, IRIntEQ, lVal, rVal, "icmp_EQ");
        } else if (ctx.NEQ() != null) {
            cmpResult = IRBuildICmp(builder, IRIntNE, lVal, rVal, "icmp_NE");
        }
        return IRBuildZExt(builder,cmpResult,int32Type,"tmp_");
    }

    @Override
    public ValueRef visitAndCond(SysYParser.AndCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef cmp  = IRBuildICmp(builder, IRIntEQ , intZero, lVal, "icmp_EQ");
        BaseBlock trueBlock = IRAppendBasicBlock(currentFunction, "true_");
        BaseBlock falseBlock = IRAppendBasicBlock(currentFunction, "false_");
        BaseBlock after = IRAppendBasicBlock(currentFunction, "after_");
        ValueRef res = IRBuildAlloca(builder,int32Type,"and_");
        IRBuildStore(builder,lVal,res);

        IRBuildCondBr(builder,cmp,falseBlock,trueBlock);
        IRPositionBuilderAtEnd(builder,falseBlock);
        IRBuildBr(builder, after);

        IRPositionBuilderAtEnd(builder,trueBlock);
        ValueRef rVal = this.visit(ctx.cond(1));
        IRBuildStore(builder,rVal,res);
        IRBuildBr(builder,after);

        IRPositionBuilderAtEnd(builder,after);
        res = IRBuildLoad(builder, res , "load_");
        return IRBuildZExt(builder,res,int32Type,"tmp_");

    }

    @Override
    public ValueRef visitOrCond(SysYParser.OrCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef cmp  = IRBuildICmp(builder, IRIntNE , intZero, lVal, "icmp_NE");
        BaseBlock trueBlock = IRAppendBasicBlock(currentFunction, "true_");
        BaseBlock falseBlock = IRAppendBasicBlock(currentFunction, "false_");
        BaseBlock after = IRAppendBasicBlock(currentFunction, "after_");
        ValueRef res = IRBuildAlloca(builder,int32Type,"or_");
        IRBuildStore(builder,lVal,res);

        IRBuildCondBr(builder,cmp,trueBlock,falseBlock);
        IRPositionBuilderAtEnd(builder,trueBlock);
        IRBuildBr(builder, after);

        IRPositionBuilderAtEnd(builder,falseBlock);
        ValueRef rVal = this.visit(ctx.cond(1));
        IRBuildStore(builder,rVal,res);
        IRBuildBr(builder,after);

        IRPositionBuilderAtEnd(builder,after);
        res = IRBuildLoad(builder, res , "load_");
        return IRBuildZExt(builder,res,int32Type,"tmp_");
    }

    @Override
    public ValueRef visitWhileStmt(SysYParser.WhileStmtContext ctx) {
        BaseBlock condBlock = IRAppendBasicBlock(currentFunction, "condBlock");
        BaseBlock bodyBlock = IRAppendBasicBlock(currentFunction, "bodyBlock");
        BaseBlock afterBlock = IRAppendBasicBlock(currentFunction, "afterBlock");

        IRBuildBr(builder,condBlock);

        IRPositionBuilderAtEnd(builder, condBlock);
        ValueRef conditionVal = this.visit(ctx.cond());
         ValueRef cmpResult = IRBuildICmp(builder, IRIntNE, conditionVal, intZero, "icmp_");
         IRBuildCondBr(builder, cmpResult, bodyBlock, afterBlock);
//        IRBuildCondBr(builder, conditionVal, bodyBlock, afterBlock);

        IRPositionBuilderAtEnd(builder, bodyBlock);
        conditionStack.push(condBlock);
        afterStack.push(afterBlock);
        this.visit(ctx.stmt());
        IRBuildBr(builder, condBlock);
        conditionStack.pop();
        afterStack.pop();

        IRPositionBuilderAtEnd(builder, afterBlock);

        return null;
    }

    @Override
    public ValueRef visitBreakStmt(SysYParser.BreakStmtContext ctx) {
        IRBuildBr(builder, afterStack.peek());
        return null;
    }

    @Override
    public ValueRef visitContinueStmt(SysYParser.ContinueStmtContext ctx) {
        IRBuildBr(builder, conditionStack.peek());
        return null;
    }
}
