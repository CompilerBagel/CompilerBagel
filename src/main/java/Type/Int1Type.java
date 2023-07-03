package Type;

public class Int1Type implements Type{
    private static final String name = "i1";
    private static Int1Type int1Type = null;

    private Int1Type() {}

    public static Type IRInt1Type() {
        if (int1Type == null) {
            int1Type = new Int1Type();
        }
        return int1Type;
    }

    @Override
    public String getText() {
        return name;
    }
}
