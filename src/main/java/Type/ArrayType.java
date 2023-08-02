package Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Arrays: can be represented as [Length x BaseType], e.g. [3 x i32].
 * BaseType: the type of the array element
 * Length: represent the length of the array
 * e.g. [3 x [2 x i32]]: an array of length 3, the array element type is a 32-bit signed array of length 2
 * equivalent to int[3][2] in C/C++.
 */

public class ArrayType implements Type{
    private final int elementNumber;
    private final Type elementType;
    private final List<Integer> elementDimension;

    public ArrayType(Type elementType, int elementNumber){
        this.elementType = elementType;
        this.elementNumber = elementNumber;
        if(elementType instanceof ArrayType){
            elementDimension = ((ArrayType) elementType).getElementDimension();
        }else{
            elementDimension = new ArrayList<>();
        }
        elementDimension.add(0, elementNumber);
    }

    public ArrayType(Type elementType, int elementNumber, List<Integer> elementDimension) {
        this.elementType = elementType;
        this.elementNumber = elementNumber;
        this.elementDimension = elementDimension;
    }

    @Override
    public String getText() {
        return "[" + elementNumber + " x " + elementType.getText() + "]";
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public Type getElementType() {
        return elementType;
    }

    public List<Integer> getElementDimension(){
        return elementDimension;
    }

    public int getLength() {
        int total = 1;
        for (Integer integer : elementDimension) {
            total *= integer;
        }
        return total;
    }

    public int getOtherDimensionLength(int dims, int index) {
        int curDim = elementDimension.size() - dims;
        for (int i = curDim + 1; i < elementDimension.size(); i ++) {
            index *= elementDimension.get(i);
        }
        return index;
    }
}
