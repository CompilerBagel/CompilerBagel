package Type;

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

    public ArrayType(Type elementType, int elementNumber) {
        this.elementType = elementType;
        this.elementNumber = elementNumber;
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
}
