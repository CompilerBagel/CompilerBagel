package antlr;// Generated from ./src/main/java/antlr/SysYParser.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SysYParser}.
 */
public interface SysYParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SysYParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SysYParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SysYParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompUnit(SysYParser.CompUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#compUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompUnit(SysYParser.CompUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterDecl(SysYParser.DeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitDecl(SysYParser.DeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void enterConstDecl(SysYParser.ConstDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#constDecl}.
	 * @param ctx the parse tree
	 */
	void exitConstDecl(SysYParser.ConstDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#bType}.
	 * @param ctx the parse tree
	 */
	void enterBType(SysYParser.BTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#bType}.
	 * @param ctx the parse tree
	 */
	void exitBType(SysYParser.BTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#constDef}.
	 * @param ctx the parse tree
	 */
	void enterConstDef(SysYParser.ConstDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#constDef}.
	 * @param ctx the parse tree
	 */
	void exitConstDef(SysYParser.ConstDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void enterConstInitVal(SysYParser.ConstInitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#constInitVal}.
	 * @param ctx the parse tree
	 */
	void exitConstInitVal(SysYParser.ConstInitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(SysYParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(SysYParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(SysYParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(SysYParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#initVal}.
	 * @param ctx the parse tree
	 */
	void enterInitVal(SysYParser.InitValContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#initVal}.
	 * @param ctx the parse tree
	 */
	void exitInitVal(SysYParser.InitValContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(SysYParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(SysYParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcType}.
	 * @param ctx the parse tree
	 */
	void enterFuncType(SysYParser.FuncTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcType}.
	 * @param ctx the parse tree
	 */
	void exitFuncType(SysYParser.FuncTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcFParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncFParams(SysYParser.FuncFParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcFParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncFParams(SysYParser.FuncFParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcFParam}.
	 * @param ctx the parse tree
	 */
	void enterFuncFParam(SysYParser.FuncFParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcFParam}.
	 * @param ctx the parse tree
	 */
	void exitFuncFParam(SysYParser.FuncFParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SysYParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SysYParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(SysYParser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(SysYParser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(SysYParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(SysYParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterExpStmt(SysYParser.ExpStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitExpStmt(SysYParser.ExpStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(SysYParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(SysYParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterConditionStmt(SysYParser.ConditionStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitConditionStmt(SysYParser.ConditionStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(SysYParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(SysYParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(SysYParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(SysYParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(SysYParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(SysYParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(SysYParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link SysYParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(SysYParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expParenthesis}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpParenthesis(SysYParser.ExpParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expParenthesis}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpParenthesis(SysYParser.ExpParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callFuncExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCallFuncExp(SysYParser.CallFuncExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callFuncExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCallFuncExp(SysYParser.CallFuncExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterNumberExp(SysYParser.NumberExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitNumberExp(SysYParser.NumberExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOpExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOpExp(SysYParser.UnaryOpExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOpExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOpExp(SysYParser.UnaryOpExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plusExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPlusExp(SysYParser.PlusExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plusExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPlusExp(SysYParser.PlusExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMulExp(SysYParser.MulExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMulExp(SysYParser.MulExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lvalExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterLvalExp(SysYParser.LvalExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lvalExp}
	 * labeled alternative in {@link SysYParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitLvalExp(SysYParser.LvalExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ltCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterLtCond(SysYParser.LtCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ltCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitLtCond(SysYParser.LtCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterOrCond(SysYParser.OrCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitOrCond(SysYParser.OrCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterExpCond(SysYParser.ExpCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitExpCond(SysYParser.ExpCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterAndCond(SysYParser.AndCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitAndCond(SysYParser.AndCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterEqCond(SysYParser.EqCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqCond}
	 * labeled alternative in {@link SysYParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitEqCond(SysYParser.EqCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#lVal}.
	 * @param ctx the parse tree
	 */
	void enterLVal(SysYParser.LValContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#lVal}.
	 * @param ctx the parse tree
	 */
	void exitLVal(SysYParser.LValContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(SysYParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(SysYParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(SysYParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#unaryOp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(SysYParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncRParams(SysYParser.FuncRParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#funcRParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncRParams(SysYParser.FuncRParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(SysYParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(SysYParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link SysYParser#constExp}.
	 * @param ctx the parse tree
	 */
	void enterConstExp(SysYParser.ConstExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SysYParser#constExp}.
	 * @param ctx the parse tree
	 */
	void exitConstExp(SysYParser.ConstExpContext ctx);
}