import IRBuilder.*;
import Scope.GlobalScope;
import Scope.LocalScope;
import Scope.Scope;
import Type.ArrayType;
import Type.FunctionType;
import Type.PointerType;
import Type.Type;
import antlr.*;

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
                paramsType.add(new PointerType(defineType(paramTypeName)));
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
            Type paramType = defineType(paramTypeName);
            if (ctx.funcFParams().funcFParam(i).L_BRACKT().size() > 0) {
                paramType = new PointerType(paramType);
            }
            ValueRef paramPointer = IRBuildAlloca(builder, paramType, paramName);
            ValueRef param = IRGetParam(currentFunction, i);
            IRBuildStore(builder, param, paramPointer);
            currentScope.define(paramName, paramPointer, paramType);
        }

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
    public ValueRef visitConstDecl(SysYParser.ConstDeclContext ctx) {
        String typeName = ctx.bType().getText();
        ValueRef assign;
        for (SysYParser.ConstDefContext constDefContext : ctx.constDef()) {
            Type type = defineType(typeName);
            ValueRef constVariable;
            String constName = constDefContext.IDENT().getText();
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
                    IRSetInitializer(module, constVariable, assign, constName);
                } else {
                    // TODO: 验证vardecl的正确性之后再搬过来
                    //if(constDefContext.ASSIGN() != null) visitInitVal(constDefContext.constInitVal());
                    IRSetInitializer(module, constVariable, init);
                }
            } else {
                constVariable = IRBuildAlloca(builder, type, constName);
                if (paramCount.size() == 0) {
                    if (constDefContext.ASSIGN() != null) assign = constDefContext.constInitVal().accept(this);
                    IRBuildStore(builder, assign, constVariable);
                } else {
                    // TODO: 验证vardecl的正确性之后再搬过来
                    //if(constDefContext.ASSIGN() != null) visitInitVal(constDefContext.constInitVal());
                    IRSetInitializer(module, constVariable, init);
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
            // 初始化assign的值
            if (typeName.equals("int")) assign = intZero;
            else assign = floatZero;
            // 求type，反向加载数组，迭代求解type
            List<Integer> paramCount = new ArrayList<>();
            for (SysYParser.ConstExpContext constExpContext : varDefContext.constExp()) {
                paramCount.add(Integer.parseInt(constExpContext.getText()));
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
                    IRSetInitializer(module, variable, assign, variableName);
                } else {
                    if (varDefContext.ASSIGN() != null) visitInitVal(varDefContext.initVal());
                    for (int i = 0; i < init.size(); i++) System.err.println(init.get(i).getText());
                    IRSetInitializer(module, variable, init);
                }
            } else {
                variable = IRBuildAlloca(builder, type, variableName);
                if (paramCount.size() == 0) {
                    if (varDefContext.ASSIGN() != null) assign = varDefContext.initVal().accept(this);
                    IRBuildStore(builder, assign, variable);
                } else {
                    //TODO: 正确性验证
                    if (varDefContext.ASSIGN() != null) visitInitVal(varDefContext.initVal());
                    IRSetInitializer(module, variable, init);
                }
            }
            currentScope.define(variableName, variable, type);
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
        for (int i = 0; i < argc; i++) {
            args.add(i, ctx.funcRParams().param(i).accept(this));
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

        if (ctx.MUL() != null) {
            return IRBuildCalc(builder, left, right, "mul_", MUL);
        } else if (ctx.DIV() != null) {
            return IRBuildCalc(builder, left, right, "sdiv_", SDIV);
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
            return IRBuildCalc(builder, left, right, "add_", ADD);
        } else if (ctx.MINUS() != null) {
            return IRBuildCalc(builder, left, right, "sub_", SUB);
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

    @Override
    public ValueRef visitLvalExp(SysYParser.LvalExpContext ctx) {
        String variableName = ctx.lVal().IDENT().getText();
        ValueRef variable = currentScope.getValueRef(variableName);
        Type varType = currentScope.getType(variableName);
        ValueRef lValPointer = visitLVal(ctx.lVal());
        if (varType instanceof PointerType) {
            return IRBuildLoad(builder, lValPointer, variableName);
        } else if (varType instanceof ArrayType) {
            if (ctx.lVal().exp().size() > 0) {
                return IRBuildLoad(builder, lValPointer, variableName);
            }
            return lValPointer;
        } else {
            return IRBuildLoad(builder, lValPointer, variableName);
        }
    }

    @Override
    public ValueRef visitLVal(SysYParser.LValContext ctx) {
        String lValName = ctx.IDENT().getText();
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
        if (ctx.exp().size() == 0) {
            return lValPointer;
        } else {
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
//        return cmpResult;
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
//        return cmpResult;
        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
    }

    @Override
    public ValueRef visitAndCond(SysYParser.AndCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
//        if(lVal.getType() != int1Type){
//            lVal = IRBuildICmp(builder, IRIntNE, lVal, intZero, "icmp_NE");
//        }
//        if(rVal.getType() != int1Type){
//            rVal = IRBuildICmp(builder, IRIntNE, rVal, intZero, "imcp_NE");
//        }
        ValueRef cmpResult = IRBuildAnd(builder, lVal, rVal, "and_");
//        return cmpResult;
        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
    }

    @Override
    public ValueRef visitOrCond(SysYParser.OrCondContext ctx) {
        ValueRef lVal = this.visit(ctx.cond(0));
        ValueRef rVal = this.visit(ctx.cond(1));
//        if(lVal.getType() != int1Type){
//            lVal = IRBuildICmp(builder, IRIntNE, lVal, intZero, "icmp_NE");
//        }
//        if(rVal.getType() != int1Type){
//            rVal = IRBuildICmp(builder, IRIntNE, rVal, intZero, "imcp_NE");
//        }
        ValueRef cmpResult = IRBuildOr(builder,  lVal, rVal, "or_");
//        return cmpResult;
        return IRBuildZExt(builder, cmpResult, int32Type, "zext_");
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
