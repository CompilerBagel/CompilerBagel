package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import static IRBuilder.IRConstants.ZEXT;
import static Type.Int1Type.IRInt1Type;

public class ZextInstruction extends Instruction{
    public ZextInstruction(ZextInstruction rhs){
        super(rhs);
    }
    public ZextInstruction(List<ValueRef> operands, BaseBlock basicBlock){
        super(operands,basicBlock);
    }

    @Override
    public String toString() {
        ValueRef resRegister = operands.get(0);
        ValueRef valueRef = operands.get(1);
        // special val
        ValueRef typeValRef = operands.get(2);
        return resRegister.getText() + " = " + ZEXT + " " + IRInt1Type().getText() + " " + valueRef.getText() + " to " + typeValRef.getType().getText();
    }
}
