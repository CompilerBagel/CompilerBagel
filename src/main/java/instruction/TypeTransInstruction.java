package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

import static IRBuilder.IRConstants.FpToSi;
import static IRBuilder.IRConstants.SiToFp;

public class TypeTransInstruction extends Instruction{
    ValueRef origin;
    ValueRef resRegister;
    int type;
    public TypeTransInstruction(TypeTransInstruction rhs){
        super(rhs);
    }
    public TypeTransInstruction(List<ValueRef> operands, BaseBlock basicBlock, int type){
        super(operands, basicBlock);
        this.origin = operands.get(0);
        this.resRegister = operands.get(1);
        this.type = type;
    }

    @Override
    public String toString(){
        if(type == FpToSi){
            return resRegister.getText() + " = fptosi float " +
                    origin.getText() + " to i32";
        }else if(type == SiToFp){
            return resRegister.getText() + " = sitofp i32 "
                    + origin.getText() + " to float";
        }
        return null;
    }
}
