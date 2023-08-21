package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

public class OccupyInstruction extends Instruction{
    public OccupyInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
    }

    public OccupyInstruction(Instruction rhs) {
        super(rhs);
    }

    @Override
    public String toString() {
        return "";
    }
}
