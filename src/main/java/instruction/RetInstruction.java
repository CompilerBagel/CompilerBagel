package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

import static IRBuilder.IRConstants.RET;

public class RetInstruction extends Instruction{
    public RetInstruction(RetInstruction rhs){
        super(rhs);
    }
    public RetInstruction(List<ValueRef> operands, BaseBlock basicBlock){
        super(operands,basicBlock);
    }
    @Override
    public String toString() {
        return RET + " " + operands.get(0).getTypeText() + " " + operands.get(0).getText();
    }
}
