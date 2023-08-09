package utils;

import IRBuilder.IRConstants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntTools {
    public static Map<Integer, Integer> logMap = Stream.of(new Integer[][]{
            {2, 1},
            {4, 2},
            {8, 3},
            {16, 4},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static int logPowerOf2(int num) {
        if (logMap.get(num) == null) {
            return -1;
        }
        return logMap.get(num);
    }
}
