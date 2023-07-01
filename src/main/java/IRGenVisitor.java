import IRBuider.Register;
import Scope.GlobalScope;
import Scope.Scope;
import Type.Type;
import antlr.SysYParser;
import antlr.SysYParserBaseVisitor;
import antlr.SysYParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;

public class IRGenVisitor extends SysYParserBaseVisitor<Register> {
    Type int32Type = IRInt32Type();
    Type floatType = IRFloatType();
    private GlobalScope globalScope = null;
    private Scope currentScope = null;
    private int localScopeCounter = 0;

    @Override
    public Register visitProgram(SysYParser.ProgramContext ctx) {
        globalScope = new GlobalScope("globalScope", null);
        currentScope = globalScope;
        return super.visitProgram(ctx);
    }


}
