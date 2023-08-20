package Pass;

import IRBuilder.ValueRef;
import Type.Type;
import antlr.SysYParser;
import antlr.SysYParserBaseVisitor;
import antlr.SysYParserVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Type.FloatType.IRFloatType;
import static Type.Int32Type.IRInt32Type;
import static Type.VoidType.IRVoidType;

public class ConstPassVisitor extends SysYParserBaseVisitor<ValueRef> {
    private static final Type int32Type = IRInt32Type();
    private static final Type floatType = IRFloatType();
    public static Map<String, Integer> variables = new HashMap<String, Integer>();
    private Type defineType(String typeName) {
        if (typeName.equals("int")) return int32Type;
        else if (typeName.equals("float")) return floatType;
        return IRVoidType();
    }
    @Override
    public ValueRef visitVarDecl(SysYParser.VarDeclContext ctx) {
        String typeName = ctx.bType().getText();
        ValueRef assign;
        for (SysYParser.VarDefContext varDefContext : ctx.varDef()) {
            Type type = defineType(typeName);
            if(varDefContext.constExp().isEmpty()){
                String variableName = varDefContext.IDENT().getText();
                if(variableName.length()>20){
                    variableName = variableName.substring(0,20);
                }
                variables.put(variableName, 0);
            }
        }
        return super.visitVarDecl(ctx);
    }

    @Override
    public ValueRef visitAssignStmt(SysYParser.AssignStmtContext ctx) {
        String variableName = ctx.lVal().IDENT().getText();
        if(variables.containsKey(variableName)){
            int temp = variables.get(variableName) + 1;
            if(temp>=2)
                variables.keySet().removeIf(key -> key.equals(variableName));
            else
                variables.put(variableName, temp);
        }
        return super.visitAssignStmt(ctx);
    }
}
