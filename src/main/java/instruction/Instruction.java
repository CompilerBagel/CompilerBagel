package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;
import java.util.List;

public abstract class Instruction {
    protected List<ValueRef> operands;
    protected BaseBlock basicBlock;

    public Instruction(List<ValueRef> operands, BaseBlock basicBlock) {
        this.operands = operands;
        this.basicBlock = basicBlock;
    }

    public void setBasicBlock(BaseBlock basicBlock){
        this.basicBlock = basicBlock;
    }
}
