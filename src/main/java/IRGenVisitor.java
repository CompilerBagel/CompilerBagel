import IRBuilder.*;
import Scope.GlobalScope;
import Scope.LocalScope;
import Scope.Scope;
import Type.FunctionType;
import Type.Type;
import antlr.SysYParser;

import java.util.ArrayList;
import java.util.List;

import antlr.SysYParserBaseVisitor;

import static IRBuilder.BaseBlock.IRAppendBasicBlock;
import static IRBuilder.IRBuilder.*;
import static IRBuilder.IRModule.IRModuleCreateWithName;
import static IRBuilder.IRModule.IRAddFunction;
import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;
import static Type.Int1Type.IRInt1Type;

public class IRGenVisitor extends SysYParserBaseVisitor<ValueRef> {
    IRModule module = IRModuleCreateWithName("module");
    IRBuilder builder = IRCreateBuilder();
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private static final Type int1Type = IRInt1Type();
    private GlobalScope globalScope = null;
    private Scope currentScope = null;
    private int localScopeCounter = 0;

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

    @Override
    public ValueRef visitFuncDef(SysYParser.FuncDefContext ctx){
        String funcName = ctx.IDENT().getText();
        Type returnType;
        int paramsCount = 0;
        if(ctx.funcFParams() != null)   paramsCount = ctx.funcFParams().funcFParam().size();
        List<Type> paramsType = new ArrayList<Type>(paramsCount);
        // todo: arrayType
        if(ctx.funcType().getText().equals("int")){
            returnType = IRInt32Type();
        }else if(ctx.funcType().getText().equals("float")){
            returnType = IRFloatType();
        }else{
            returnType = IRVoidType();
        }
        for(int i = 0; i < paramsCount; i++){
            if(ctx.funcFParams().funcFParam(i).L_BRACKT(0) != null){
                //paramsType.add(IRArrayType());
            }else{
                if(ctx.funcFParams().funcFParam(i).bType().getText().equals("int")){
                    paramsType.add(IRInt32Type());
                }else if(ctx.funcFParams().funcFParam(i).bType().getText().equals("float")){
                    paramsType.add(IRFloatType());
                }
            }
        }
        FunctionType functionType = new FunctionType(paramsType, returnType);
        FunctionBlock functionBlock = IRAddFunction(module, funcName, functionType);
        IRPositionBuilderAtEnd(builder, IRAppendBasicBlock(functionBlock, funcName + "Entry"));
        globalScope.define(funcName, functionBlock, functionType);
        ValueRef ret = super.visitFuncDef(ctx);
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
        Type type;
        ValueRef constVariable;
        ValueRef assign;
        if(typeName.equals("int")){
            type = IRInt32Type();
            assign = intZero;
        }else{
            type = IRFloatType();
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
                IRBuildStore(builder, constVariable, assign);
            }
            currentScope.define(constName, constVariable, type);
        }
        return null;
    }

    @Override
    public ValueRef visitVarDecl(SysYParser.VarDeclContext ctx){
        String typeName = ctx.bType().getText();
        Type type;
        ValueRef variable;
        ValueRef assign;
        if(typeName.equals("int")){
            type = IRInt32Type();
            assign = intZero;
        }else{
            type = IRFloatType();
            assign = floatZero;
        }
        for(SysYParser.VarDefContext varDefContext: ctx.varDef()){
            String variableName = varDefContext.IDENT().getText();
            if(varDefContext.ASSIGN() != null){
                assign = varDefContext.initVal().accept(this);
            }
            if(currentScope instanceof GlobalScope){
                variable = IRAddGlobal(module, type, variableName);
                IRSetInitializer(module, assign);
            }else{
                variable = IRBuildAlloca(builder, type, variableName);
                IRBuildStore(builder, variable, assign);
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

        int argsNum = functionType.getParamsType().size();
        ValueRef[] args = new ValueRef[argsNum];
        for(int i = 0; i < argsNum; i++){
            args[i] = ctx.funcRParams().param(i).accept(this);
        }
        // todo: callfunc 具体怎么设计还需要商讨
        // IRBuildCall(builder, functionBlock, args, argsNum, functionType.retType);
        return null;
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
}
