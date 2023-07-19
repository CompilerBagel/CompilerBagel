package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.HashMap;
import java.util.List;

public class PhiInstruction extends Instruction {
    private HashMap<BaseBlock, ValueRef> values;
    //BaseBlock Register
    public HashMap<BaseBlock, ValueRef> getValues(){
        return values;
    }
    private AllocaInstruction owner = null;
    //to be used at mem2Reg

    public AllocaInstruction getOwner(){
        return owner;
    }

    public void setOwner(AllocaInstruction owner){
        this.owner = owner;
    }

    public PhiInstruction(List<ValueRef> value, BaseBlock baseBlock){
        super(value,baseBlock);

    }

    public PhiInstruction(PhiInstruction rhs){
        super(rhs);
        values = (HashMap<BaseBlock, ValueRef>) rhs.values.clone();
        owner = rhs.owner;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
