package utils;

import IRBuilder.IRConstants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntTools {
    public static Map<Integer, Integer> logMap = Stream.of(new Integer[][]{
            {2, 1}, {4, 2}, {8, 3}, {16, 4},
            {32, 5}, {64, 6},{128, 7}, {256, 8},
            {512, 9}, {1024, 10},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    /**
     * calc log2(num) by table logMap
     * @param num multiplicand
     * @return log2(num), if num is not 2^n, return -1
     */
    public static int logPowerOf2(int num) {
        if (logMap.get(num) == null) {
            return -1;
        }
        return logMap.get(num);
    }

    /**
     * judge weather num is 2^n
     * @param num rem num
     * @return num - 1,  if num is not 2^n, return -1
     */
    public static int remToAnd(int num) {
        // num & (num - 1) == 0  ==> num is 2^n
        if (num > 0 && num < 2048 && (num & (num - 1)) == 0) {
            return num - 1;
        }
        return -1;
    }
}
