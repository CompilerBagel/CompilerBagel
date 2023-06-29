package Type;

import java.util.ArrayList;

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
