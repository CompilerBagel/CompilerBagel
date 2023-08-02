package instruction;

import IRBuilder.BaseBlock;
import IRBuilder.ValueRef;
import Type.ArrayType;
import Type.PointerType;
import Type.Type;

import java.util.ArrayList;
import java.util.List;

import static IRBuilder.IRConstants.GETPTR;
import static Type.Int32Type.IRInt32Type;

public class GetElemPtrInstruction extends Instruction{
    private ValueRef base;
    private ArrayList<ValueRef> indexs;

    public GetElemPtrInstruction(GetElemPtrInstruction rhs) {
        super(rhs);
        this.base = rhs.base;
        this.indexs = (ArrayList<ValueRef>) rhs.indexs.clone();
    }

    public GetElemPtrInstruction(List<ValueRef> operands, BaseBlock basicBlock) {
        super(operands, basicBlock);
        this.base = operands.get(1);
        this.indexs = new ArrayList<ValueRef>();
        for(int i = 2; i < operands.size();i++){
            this.indexs.add(operands.get(i));
        }
    }

    public ValueRef getBase() {
        return base;
    }

    public ValueRef getPointer() {
        return operands.get(1);
    }

    public Type getPointedType(ValueRef pointer, int depth) {
        Type pointedType = null;
        if (pointer.getType() instanceof PointerType) {
            pointedType = ((PointerType) pointer.getType()).getBaseType();
            for (int i = 1; i < depth; i++) {
                if (pointedType instanceof ArrayType) {
                    pointedType = ((ArrayType) pointedType).getElementType();
                }
            }
        } else if (pointer.getType() instanceof ArrayType) {
            pointedType = ((ArrayType) pointer.getType()).getElementType();
            for (int i = 1; i < depth; i++) {
                if (pointedType instanceof ArrayType) {
                    pointedType = ((ArrayType) pointedType).getElementType();
                }
            }
        }
        return pointedType;
    }

    @Override
    public String toString() {
        ValueRef resRegister = operands.get(0);
        Type baseType = ((PointerType) base.getType()).getBaseType();
        Type resType;
        if (baseType instanceof ArrayType) {
            resType = ((ArrayType) baseType).getElementType();
        } else {
            resType = baseType;
        }
        StringBuilder indexStrBuilder = new StringBuilder();
        for (ValueRef index : indexs) {
            indexStrBuilder.append(", ").append(IRInt32Type().getText())
                    .append(" ").append(index.getText());
        }
        return resRegister.getText() + " = " + GETPTR + " " + baseType.getText() + ", "
                + base.getTypeText() + " " + base.getText() + indexStrBuilder.toString();
    }
}
