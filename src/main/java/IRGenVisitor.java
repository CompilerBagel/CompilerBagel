import IRBuilder.*;
import Scope.*;
import Type.Type;
import antlr.SysYParser;
import antlr.SysYParserBaseVisitor;

import static IRBuilder.IRBuilder.IRCreateBuilder;
import static IRBuilder.IRModule.IRModuleCreateWithName;
import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;

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
    public ValueRef visitBlock(SysYParser.BlockContext ctx) {
        String scopeName = "localScope" + localScopeCounter;
        localScopeCounter++;
        currentScope = new LocalScope(scopeName, currentScope);
        ValueRef ret = super.visitBlock(ctx);
        currentScope = currentScope.getEnclosingScope();
        return ret;
    }

    

}
