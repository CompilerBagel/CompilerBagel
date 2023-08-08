package utils;

public class FloatTools {
    /**
     * The decimal representation of the first 20 bits of a floating number in IEEE754 representation
     *
     * @param num floating number
     * @return int
     */
    public static int getHigh20(float num) {
        return (Float.floatToIntBits(num) & 0xfffff000) >> 12;
    }

    /**
     * The decimal representation of the last 12 bits of a floating number in IEEE754 representation
     *
     * @param num floating number
     * @return int
     */
    public static int getLow12(float num) {
        return Float.floatToIntBits(num) & 0xfff;
    }
}
