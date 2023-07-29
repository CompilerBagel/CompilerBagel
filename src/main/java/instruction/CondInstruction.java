package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

import static IRBuilder.IRConstants.ICMP;
import static IRBuilder.IRConstants.ICMPCodes;

public class CondInstruction extends Instruction{
    int icmpType;
    public CondInstruction(List<ValueRef> operands, BaseBlock basicBlock, int icmpType){
        super(operands, basicBlock);
        this.icmpType = icmpType;
    }

    public CondInstruction(CondInstruction rhs){
        super(rhs);
        this.icmpType = rhs.icmpType;
    }

    @Override
    public String toString(){
        ValueRef resRegister = operands.get(0);
        ValueRef lhs = operands.get(1);
        ValueRef rhs = operands.get(2);
        return resRegister.getText() + " = " + ICMP + " " + ICMPCodes[icmpType] + " "
                + lhs.getTypeText() + " " + lhs.getText() + ", " + rhs.getText();

    }

    public int getIcmpType() {
        return icmpType;
    }
}
