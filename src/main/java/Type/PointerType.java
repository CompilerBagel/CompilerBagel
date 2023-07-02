package Type;

/**
 * Pointer: can be represented as `BaseType + *`
 * e.g. i32*, i32**.
 */

public class PointerType implements Type {
    private final Type baseType;

    public PointerType(Type baseType) {
        this.baseType = baseType;
    }
    @Override
    public String getText() {
        return baseType.getText() + "*";
    }

    public Type getBaseType() {
        return baseType;
    }
}
