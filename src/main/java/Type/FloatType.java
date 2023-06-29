package Type;

public class FloatType implements Type {
    private static final String name = "float";
    private static FloatType floatType = null;

    private FloatType() {}

    public static Type IRFloatType() {
        if (floatType == null) {
            floatType = new FloatType();
        }
        return floatType;
    }

    @Override
    public String getName() {
        return name;
    }
}
