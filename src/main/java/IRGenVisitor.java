import IRBuider.Register;
import antlr.SysYParser;
import antlr.SysYParserVisitor;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class IRGenVisitor implements SysYParserVisitor<Register> {
    @Override
    public Register visit(ParseTree parseTree) {
        return null;
    }

    @Override
    public Register visitChildren(RuleNode ruleNode) {
        return null;
    }

    @Override
    public Register visitTerminal(TerminalNode terminalNode) {
        return null;
    }

    @Override
    public Register visitErrorNode(ErrorNode errorNode) {
        return null;
    }

    @Override
    public Register visitProgram(SysYParser.ProgramContext ctx) {
        return null;
    }

    @Override
    public Register visitCompUnit(SysYParser.CompUnitContext ctx) {
        return null;
    }

    @Override
    public Register visitDecl(SysYParser.DeclContext ctx) {
        return null;
    }

    @Override
    public Register visitConstDecl(SysYParser.ConstDeclContext ctx) {
        return null;
    }

    @Override
    public Register visitBType(SysYParser.BTypeContext ctx) {
        return null;
    }

    @Override
    public Register visitConstDef(SysYParser.ConstDefContext ctx) {
        return null;
    }

    @Override
    public Register visitConstInitVal(SysYParser.ConstInitValContext ctx) {
        return null;
    }

    @Override
    public Register visitVarDecl(SysYParser.VarDeclContext ctx) {
        return null;
    }

    @Override
    public Register visitVarDef(SysYParser.VarDefContext ctx) {
        return null;
    }

    @Override
    public Register visitInitVal(SysYParser.InitValContext ctx) {
        return null;
    }

    @Override
    public Register visitFuncDef(SysYParser.FuncDefContext ctx) {
        return null;
    }

    @Override
    public Register visitFuncType(SysYParser.FuncTypeContext ctx) {
        return null;
    }

    @Override
    public Register visitFuncFParams(SysYParser.FuncFParamsContext ctx) {
        return null;
    }

    @Override
    public Register visitFuncFParam(SysYParser.FuncFParamContext ctx) {
        return null;
    }

    @Override
    public Register visitBlock(SysYParser.BlockContext ctx) {
        return null;
    }

    @Override
    public Register visitBlockItem(SysYParser.BlockItemContext ctx) {
        return null;
    }

    @Override
    public Register visitAssignStmt(SysYParser.AssignStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitExpStmt(SysYParser.ExpStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitBlockStmt(SysYParser.BlockStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitConditionStmt(SysYParser.ConditionStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitWhileStmt(SysYParser.WhileStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitBreakStmt(SysYParser.BreakStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitContinueStmt(SysYParser.ContinueStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitReturnStmt(SysYParser.ReturnStmtContext ctx) {
        return null;
    }

    @Override
    public Register visitExpParenthesis(SysYParser.ExpParenthesisContext ctx) {
        return null;
    }

    @Override
    public Register visitCallFuncExp(SysYParser.CallFuncExpContext ctx) {
        return null;
    }

    @Override
    public Register visitNumberExp(SysYParser.NumberExpContext ctx) {
        return null;
    }

    @Override
    public Register visitUnaryOpExp(SysYParser.UnaryOpExpContext ctx) {
        return null;
    }

    @Override
    public Register visitPlusExp(SysYParser.PlusExpContext ctx) {
        return null;
    }

    @Override
    public Register visitMulExp(SysYParser.MulExpContext ctx) {
        return null;
    }

    @Override
    public Register visitLvalExp(SysYParser.LvalExpContext ctx) {
        return null;
    }

    @Override
    public Register visitLtCond(SysYParser.LtCondContext ctx) {
        return null;
    }

    @Override
    public Register visitOrCond(SysYParser.OrCondContext ctx) {
        return null;
    }

    @Override
    public Register visitExpCond(SysYParser.ExpCondContext ctx) {
        return null;
    }

    @Override
    public Register visitAndCond(SysYParser.AndCondContext ctx) {
        return null;
    }

    @Override
    public Register visitEqCond(SysYParser.EqCondContext ctx) {
        return null;
    }

    @Override
    public Register visitLVal(SysYParser.LValContext ctx) {
        return null;
    }

    @Override
    public Register visitNumber(SysYParser.NumberContext ctx) {
        return null;
    }

    @Override
    public Register visitUnaryOp(SysYParser.UnaryOpContext ctx) {
        return null;
    }

    @Override
    public Register visitFuncRParams(SysYParser.FuncRParamsContext ctx) {
        return null;
    }

    @Override
    public Register visitParam(SysYParser.ParamContext ctx) {
        return null;
    }

    @Override
    public Register visitConstExp(SysYParser.ConstExpContext ctx) {
        return null;
    }
}
