import IRBuilder.*;
import Scope.GlobalScope;
import Scope.LocalScope;
import Scope.Scope;
import Type.ArrayType;
import Type.FunctionType;
import Type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private GlobalScope globalScope = null;
    private Scope currentScope = null;
    private int localScopeCounter = 0;
    private FunctionBlock currentFunction = null;
    private Stack<BaseBlock> conditionStack = new Stack<>();
    private Stack<BaseBlock> afterStack = new Stack<>();
    private ValueRef intZero = new ConstIntValueRef(0);
    private ValueRef floatZero = new ConstFloatValueRef(0);

    public IRModule getModule() {
        return module;
    }
    @Override
    public ValueRef visitProgram(SysYParser.ProgramContext ctx) {
        globalScope = new GlobalScope("globalScope", null);
        currentScope = globalScope;
        return super.visitProgram(ctx);
    }

    private Type defineType(String typeName){
        if(typeName.equals("int")) return int32Type;
        else if(typeName.equals("float")) return floatType;
        return IRVoidType();
    }

    @Override
    public ValueRef visitFuncDef(SysYParser.FuncDefContext ctx){
        String funcName = ctx.IDENT().getText();
        Type returnType;
        int paramsCount = 0;
        if(ctx.funcFParams() != null)   paramsCount = ctx.funcFParams().funcFParam().size();
        List<Type> paramsType = new ArrayList<Type>(paramsCount);
        // todo: arrayType
        String returnTypeName = ctx.funcType().getText();
        returnType = defineType(returnTypeName);
        for(int i = 0; i < paramsCount; i++){
            if(ctx.funcFParams().funcFParam(i).L_BRACKT(0) != null){
                //paramsType.add(IRArrayType());
            }else{
                String paramTypeName = ctx.funcFParams().funcFParam(i).bType().getText();
                paramsType.add(defineType(paramTypeName));
            }
        }
        FunctionType functionType = new FunctionType(paramsType, returnType);
        FunctionBlock functionBlock = IRAddFunction(module, funcName, functionType);
        IRPositionBuilderAtEnd(builder, IRAppendBasicBlock(functionBlock, funcName + "Entry"));
        globalScope.define(funcName, functionBlock, functionType);

        currentScope = new LocalScope(funcName + "Scope", currentScope);
        for(int i = 0; i < paramsCount; i++){
            String paramName = ctx.funcFParams().funcFParam(i).IDENT().getText();
            String paramTypeName = ctx.funcFParams().funcFParam(i).bType().getText();
            Type paramType = defineType(paramTypeName);
            ValueRef paramPointer = IRBuildAlloca(builder, paramType, paramName);
            // todo: param
            ValueRef param = IRGetParam(functionBlock, i);
            IRBuildStore(builder, param, paramPointer);
            currentScope.define(paramName, paramPointer, paramType);
        }

        currentFunction = IRAddFunction(module, funcName, functionType);
        IRPositionBuilderAtEnd(builder, IRAppendBasicBlock(currentFunction, funcName + "Entry"));
        globalScope.define(funcName, currentFunction, functionType);
        ValueRef ret = super.visitFuncDef(ctx);
        currentScope = currentScope.getEnclosingScope();
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
    public ValueRef visitConstDecl(SysYParser.ConstDeclContext ctx){
        String typeName = ctx.bType().getText();
        Type type = defineType(typeName);
        ValueRef constVariable;
        ValueRef assign;
        if(typeName.equals("int")){
            assign = intZero;
        }else{
            assign = floatZero;
        }
        for(SysYParser.ConstDefContext constDefContext: ctx.constDef()){
            String constName = constDefContext.IDENT().getText();
            if(constDefContext.ASSIGN() != null){
                assign = constDefContext.constInitVal().accept(this);
            }
            if(currentScope instanceof GlobalScope){
                constVariable = IRAddGlobal(module, type, constName);
                IRSetInitializer(module, assign);
            }else{
                constVariable = IRBuildAlloca(builder, type, constName);
                IRBuildStore(builder, assign, constVariable);
            }
            currentScope.define(constName, constVariable, type);
        }
        return null;
    }

    @Override
    public ValueRef visitVarDecl(SysYParser.VarDeclContext ctx){
        String typeName = ctx.bType().getText();
        ValueRef assign;
        if(typeName.equals("int")){
            assign = intZero;
        }else{
            assign = floatZero;
        }
        for(SysYParser.VarDefContext varDefContext: ctx.varDef()){
            Type type = defineType(typeName);
            ValueRef variable;
            String variableName = varDefContext.IDENT().getText();
            ValueRef pointer = null;
            int paramCount = 0;
            int totalParamCount = 1;
            for(SysYParser.ConstExpContext constExpContext: varDefContext.constExp()){
                paramCount = Integer.parseInt(constExpContext.getText());
                type = new ArrayType(type,paramCount);
                totalParamCount *= paramCount;
            }

            if(varDefContext.ASSIGN() != null){
                assign = varDefContext.initVal().accept(this);
            }
            if(currentScope instanceof GlobalScope){
                variable = IRAddGlobal(module, type, variableName);
                if(paramCount == 0) {
                    IRSetInitializer(module, assign);
                }else{
                    //TODO PointerPointer
                }
            }else{
                variable = IRBuildAlloca(builder, type, variableName);
                IRBuildStore(builder, assign, variable);
            }
            currentScope.define(variableName, variable, type);
        }
        return null;
    }

    @Override
    public ValueRef visitCallFuncExp(SysYParser.CallFuncExpContext ctx){
        String funcName = ctx.IDENT().getText();
        FunctionBlock functionBlock = (FunctionBlock) globalScope.getValueRef(funcName);
        FunctionType functionType = (FunctionType) globalScope.getType(funcName);

        int argc = functionType.getParamsType().size();
        List<ValueRef> args = new ArrayList<>(argc);
        for(int i = 0; i < argc; i++){
            args.add(i, ctx.funcRParams().param(i).accept(this));
        }
        return IRBuildCall(builder, functionBlock, args, argc, funcName);
    }

    @Override
    public ValueRef visitReturnStmt(SysYParser.ReturnStmtContext ctx){
        ValueRef ret = ctx.exp().accept(this);
        IRBuildRet(builder, ret);
        return ret;
    }

    @Override
    public ValueRef visitNumberExp(SysYParser.NumberExpContext ctx){
        return ctx.number().accept(this);
    }

    @Override
    public ValueRef visitNumber(SysYParser.NumberContext ctx){
        String num = ctx.getText();
        if(ctx.INTEGER_CONST() != null){
            return calculateInt(num);
        }else if(ctx.FLOAT_CONST() != null){
            return calculateFloat(num);
        }
        return new ConstIntValueRef(0);
    }
    
    public ValueRef calculateInt(String number){
        int num;
        if(number.startsWith("0x") || number.startsWith("0X")){
            num = Integer.parseInt(number.substring(2), 16);
        }else if(number.startsWith("0") && number.length() != 1){
            num = Integer.parseInt(number.substring(1), 8);
        }else{
            num = Integer.parseInt(number);
        }
        return new ConstIntValueRef(num);
    }

    public ValueRef calculateFloat(String number){
        float num = Float.parseFloat(number);
        return new ConstFloatValueRef(num);
    }

    @Override
    public ValueRef visitMulExp(SysYParser.MulExpContext ctx) {
        ValueRef left = visit(ctx.exp(0));
        ValueRef right = visit(ctx.exp(1));

        if (ctx.MUL() != null) {
            return IRBuildMul(builder, left, right, "mul_");
        } else if (ctx.DIV() != null) {
            return IRBuildDiv(builder, left, right, "div_");
        } else if (ctx.MOD() != null) {
            return IRBuildSRem(builder, left, right, "srem_");
        }

        return null;
    }

    @Override
    public ValueRef visitPlusExp(SysYParser.PlusExpContext ctx) {
        ValueRef left = visit(ctx.exp(0));
        ValueRef right = visit(ctx.exp(1));

        if (ctx.PLUS() != null) {
            return IRBuildAdd(builder, left, right, "add_");
        } else if (ctx.MINUS() != null) {
            return IRBuildSub(builder, left, right, "sub_");
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
                operand = IRBuildICmp(builder , 1 , new ConstIntValueRef(0) , operand , "icmp_");
                operand = IRBuildXor(builder , operand , new ConstIntValueRef(1, int1Type) , "xor_");
                operand = IRBuildZExt(builder , operand , int32Type , "zext_");
                return operand;
            default:
                break;
        }

        return null;
    }
    
    @Override
    public ValueRef visitAssignStmt(SysYParser.AssignStmtContext ctx){
        ValueRef lValPointer = this.visitLVal(ctx.lVal());
        ValueRef exp = this.visit(ctx.exp());
        return IRBuildStore(builder, exp, lValPointer);
    }

    @Override
    public ValueRef visitLvalExp(SysYParser.LvalExpContext ctx){
        ValueRef lValPointer = ctx.lVal().accept(this);
        return IRBuildLoad(builder, lValPointer, ctx.lVal().getText());
    }
    
    @Override
    public ValueRef visitLVal(SysYParser.LValContext ctx){
        String lValName = ctx.IDENT().getText();
        ValueRef lValPointer = currentScope.getValueRef(lValName);
        
        if (ctx.exp().size() == 0) {
            return lValPointer;
        } else {
            // TODO: array
        }
        
        return lValPointer;
    }
    
    @Override
    public ValueRef visitConditionStmt(SysYParser.ConditionStmtContext ctx){
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
            cmpResult = IRBuildICmp(builder, IRIntSLT,  lVal, rVal, "icmp_LT");
        } else if (ctx.LE() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSLE, lVal, rVal, "icmp_LE");
        } else if (ctx.GE() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSGE, lVal, rVal, "icmp_GE");
        } else if (ctx.GT() != null) {
            cmpResult = IRBuildICmp(builder, IRIntSGT, lVal, rVal, "icmp_GT");
        }

        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
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

        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
    }

    @Override
    public ValueRef visitAndCond(SysYParser.AndCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
        // TODO: icmpType = 10 -> and
        ValueRef cmpResult = IRBuildICmp(builder, 10, lVal, rVal, "and_");
        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
    }
    
    @Override
    public ValueRef visitOrCond(SysYParser.OrCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
        // TODO: icmpType = 11 -> or
        ValueRef cmpResult = IRBuildICmp(builder, 11, lVal, rVal, "or_");
        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
    }
    
    @Override
    public ValueRef visitWhileStmt(SysYParser.WhileStmtContext ctx) {
        BaseBlock condBlock = IRAppendBasicBlock(currentFunction, "condBlock");
        BaseBlock bodyBlock = IRAppendBasicBlock(currentFunction, "bodyBlock");
        BaseBlock afterBlock = IRAppendBasicBlock(currentFunction, "afterBlock");
        
        IRPositionBuilderAtEnd(builder, condBlock);
        ValueRef conditionVal = this.visit(ctx.cond());
        ValueRef cmpResult = IRBuildICmp(builder, IRIntNE, conditionVal, intZero, "icmp_");
        IRBuildCondBr(builder, cmpResult, bodyBlock, afterBlock);
        
        IRPositionBuilderAtEnd(builder, bodyBlock);
        conditionStack.push(condBlock);
        afterStack.push(afterBlock);
        this.visit(ctx.stmt());
        conditionStack.pop();
        afterStack.pop();
        IRBuildBr(builder, afterBlock);
        
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
