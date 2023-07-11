package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;

import java.util.List;

public class BrInstruction extends Instruction{
    public final static int SINGLE = 0;
    public final static int DOUBLE = 1;
    private int type;
    private BaseBlock baseBlock1;
    private BaseBlock baseBlock2;
    public BrInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
        if(operands.size()==1) {
            this.baseBlock1 = (BaseBlock) operands.get(0);
            this.type = SINGLE;
        }else{
            this.baseBlock1 = (BaseBlock) operands.get(1);
            this.baseBlock2 = (BaseBlock) operands.get(2);
            this.type = DOUBLE;
        }
    }
    public int getType(){
        return this.type;
    }
    @Override
    public String toString() {
        if(type == SINGLE){
            return "br label " + baseBlock1.toString();
        }else{
            ValueRef register = operands.get(0);
            return "br i1 "+ register.getText() + ", label " +baseBlock1.getLabel() + ", label " + baseBlock2.getLabel();
        }
    }
}
