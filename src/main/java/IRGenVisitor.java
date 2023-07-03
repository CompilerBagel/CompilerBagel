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
import IRBuilder.ConstFloatValueRef;

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
        }else if(number.startsWith("0")){
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
}
