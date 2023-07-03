import IRBuilder.FunctionBlock;
import IRBuilder.IRBuilder;
import IRBuilder.IRModule;
import IRBuilder.ValueRef;
import Scope.GlobalScope;
import Scope.LocalScope;
import Scope.Scope;
import Type.FunctionType;
import Type.Type;
import IRBuilder.ConstIntValueRef;

import java.util.ArrayList;
import java.util.List;

import static IRBuilder.BaseBlock.IRAppendBasicBlock;
import static IRBuilder.IRBuilder.*;
import static IRBuilder.IRModule.IRModuleCreateWithName;
import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;

public class IRGenVisitor extends SysYParserBaseVisitor<ValueRef> {
    IRModule module = IRModuleCreateWithName("module");
    IRBuilder builder = IRCreateBuilder();
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    private GlobalScope globalScope = null;
    private Scope currentScope = null;
    private int localScopeCounter = 0;

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
        // todo: floatType arrayType
        if(ctx.funcType().getText().equals("int")){
            returnType = IRInt32Type();
        }else{
            returnType = IRVoidType();
        }
        for(int i = 0; i < paramsCount; i++){
            if(ctx.funcFParams().funcFParam(i).L_BRACKT(0) != null){
                //paramsType.add(IRArrayType());
            }else{
                paramsType.add(IRInt32Type());
            }
        }
        FunctionType functionType = new FunctionType(paramsType, returnType);
        FunctionBlock functionBlock = new FunctionBlock(funcName, functionType);
        IRPositionBuilderAtEnd(builder, IRAppendBasicBlock(functionBlock, funcName + "Entry"));
        module.addFunction(functionBlock);
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
    public ValueRef visitReturnStmt(SysYParser.ReturnStmtContext ctx){
        ValueRef ret = new ConstIntValueRef(1);
        IRBuildRet(builder, ret);
        return null;
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
            return IRBuildMod(builder, left, right, "mod_");
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
    public ValueRef visitUnaryExp(SysYParser.UnaryExpContext ctx) {
        String operator = ctx.unaryOp().getText();
        ValueRef operand = visit(ctx.exp());
        switch (operator) {
            case "+":
                return operand;
            case "-":
                return IRBuildNeg(builder, operand, "neg_");
            case "!":
                return IRBuildNot(builder, operand, "not_");
            default:
        }

        return null;
    }
}
