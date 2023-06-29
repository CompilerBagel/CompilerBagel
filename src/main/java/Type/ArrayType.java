package Type;

import java.util.ArrayList;

/**
 * Arrays: can be represented as [BaseType, Length], e.g. [i32, 2].
 * BaseType: the type of the array element
 * Length: represent the length of the array
 * e.g. [[i32, 3], 2]: an array of length 2, the array element type is a 32-bit signed array of length 3
 * equivalent to int[2][3] in C/C++.
 */

public class ArrayType implements Type{
    private final int elementNumber;
    private final Type elementType;

    public ArrayType(Type elementType, int elementNumber) {
        this.elementType = elementType;
        this.elementNumber = elementNumber;
    }

    @Override
    public String getName() {
        return "[" + elementType.getName() + ", "
                + elementNumber + "]";
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public Type getElementType() {
        return elementType;
    }
}
