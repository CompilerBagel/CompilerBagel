package Type;

public class Int32Type implements Type{
    private static final String name = "i32";
    private static Int32Type int32Type = null;

    private Int32Type() {}

    public static Type IRInt32Type() {
        if (int32Type == null) {
            int32Type = new Int32Type();
        }
        return int32Type;
    }

    @Override
    public String getText() {
        return name;
    }
}
