package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

import static IRBuilder.IRConstants.LOAD;

public class LoadInstruction extends Instruction {

    public LoadInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
    }

    @Override
    public String toString() {
        ValueRef resRegister = operands.get(0);
        ValueRef pointer = operands.get(1);
        return resRegister.getText() + " = " + LOAD + " " + resRegister.getTypeText() + ", "
                + pointer.getTypeText() + " " + pointer.getText();
    }

}
