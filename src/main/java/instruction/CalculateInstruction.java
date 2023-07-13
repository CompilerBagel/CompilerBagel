package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

import static IRBuilder.IRConstants.*;

public class CalculateInstruction extends Instruction{
    String type;
    public CalculateInstruction(List<ValueRef> operands, BaseBlock basicBlock, String type) {
        super(operands, basicBlock);
        this.type = type;
    }

    public CalculateInstruction(CalculateInstruction rhs){
        super(rhs);
        this.type = rhs.type;
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
            case SREM:
                return resRegister.getType() + " = " + SREM + " " + resRegister.getTypeText() + " " + lhsValRef.getText() + ", " + rhsValRef.getText();
            case XOR:
                return resRegister.getText() + " = " + XOR + " " + rhsValRef.getTypeText() + " " + lhsValRef.getText() + ", true";
        }
        return null;
    }
}