package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.BaseRegister;
import IRBuilder.ValueRef;
import Type.PointerType;

import java.util.List;

import static IRBuilder.IRConstants.ALLOCA;

public class AllocaInstruction extends Instruction{
    public AllocaInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
    }

    @Override
    public String toString() {
        ValueRef resRegister = operands.get(0);
        return resRegister.getText() + " = " + ALLOCA + " " + ((PointerType)(resRegister.getType())).getText();
    }


}
