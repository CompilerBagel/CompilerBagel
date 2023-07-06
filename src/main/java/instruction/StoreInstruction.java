package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

public class StoreInstruction extends Instruction{
    private ValueRef ptr;
    private ValueRef target;
    private ValueRef actualPtr;
    public StoreInstruction(StoreInstruction rhs){
        super(rhs);
    }
    public StoreInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
        this.target = operands.get(1);
        this.ptr = operands.get(2);
    }

    @Override
    public String toString() {
        return "store " + target.getType() +  " " +
                target.getText() + ", " + ptr.getType()
                + " " + ptr.getText();
    }
}
