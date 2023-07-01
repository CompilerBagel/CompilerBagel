package Type;

public class VoidType implements Type{
    private static final String name = "void";
    private static VoidType voidType = null;

    private VoidType() {}

    public static Type IRVoidType() {
        if (voidType == null) {
            voidType = new VoidType();
        }
        return voidType;
    }

    @Override
    public String getText() {
        return name;
    }
}
