package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.FunctionBlock;
import IRBuilder.ValueRef;
import Type.VoidType;

import java.util.ArrayList;
import java.util.List;

import static IRBuilder.IRConstants.CALL;

public class CallInstruction extends Instruction{
    private FunctionBlock functionBlock;
    private ArrayList<ValueRef> params;
    public boolean isVoid(){
        return isVoid;
    }
    private boolean isVoid;
    public FunctionBlock getFunction(){
        return functionBlock;
    }

    public CallInstruction(CallInstruction rhs){
        super(rhs);
        this.isVoid = rhs.isVoid;
        this.params = (ArrayList<ValueRef>) rhs.params.clone();
        this.functionBlock = rhs.functionBlock;
    }

    public CallInstruction(List<ValueRef> operands, BaseBlock basicBlock){
        super(operands,basicBlock);
        this.functionBlock = (FunctionBlock) operands.get(1);
        if(functionBlock.getRetType() instanceof VoidType){
            isVoid = true;
        }else{
            isVoid = false;
        }
        for(int i = 2; i < operands.size();i++) {
            params.add(operands.get(i));
        }
    }

    @Override
    public String toString() {
        ValueRef resRegister = this.operands.get(0);
        StringBuilder stringBuilder = new StringBuilder();
        if (!isVoid) {
            stringBuilder.append(resRegister.getText()).append(" = ");
        }
        stringBuilder.append(CALL).append(" ").append(functionBlock.getRetType().getText())
                .append(" ").append(functionBlock.getText()).append("(");
        if (params.size() > 0) {
            stringBuilder.append(params.get(0).getTypeText()).append(" ").append(params.get(0).getText());
        }
        for (int i = 0; i < params.size(); i++) {
            stringBuilder.append(", ")
                    .append(params.get(i).getTypeText())
                    .append(" ").append(params.get(i).getText());
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
