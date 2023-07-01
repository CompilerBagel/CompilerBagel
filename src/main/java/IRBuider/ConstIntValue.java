package IRBuider;

public class ConstIntValue implements ValueRef{
    private int value;
    @Override
    public String getText() {
        return "" + value;
    }

    @Override
    public String getTypeText() {
        return null;
    }
}
