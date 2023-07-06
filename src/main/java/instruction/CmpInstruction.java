package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;
import static IRBuilder.IRConstants.*;
import java.util.List;

public class CmpInstruction extends Instruction{
    String type;
    public CmpInstruction(List<ValueRef> operands, BaseBlock basicBlock, String type) {
        super(operands, basicBlock);
        this.type = type;
    }

    @Override
    public String toString() {
        ValueRef resRegister = operands.get(0);
        ValueRef lhsValRef = operands.get(1);
        ValueRef rhsValRef = operands.get(2);
        switch (type){
            case ADD:
                return resRegister.getText() + " = " + ADD + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText();
            case SUB:
                return resRegister.getText() + " = " + SUB + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText();
            case MUL:
                return resRegister.getText() + " = " + MUL + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText();
            case DIV:
                return resRegister.getText() + " = " + DIV + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText();
        }
        return null;
    }
}