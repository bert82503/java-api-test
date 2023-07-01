package leet.code.string;

/**
 * 949. 给定数字能组成的最大时间
 * <p></p>
 * https://leetcode-cn.com/problems/largest-time-for-given-digits/
 * <p></p>
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 *
 * 24 小时格式为 "HH:MM" ，其中 HH 在 00 到 23 之间，MM 在 00 到 59 之间。
 * 最小的 24 小时制时间是 00:00 ，而最大的是 23:59 。
 * 从 00:00 （午夜）开始算起，过得越久，时间越大。
 *
 * 以长度为 5 的字符串，按 "HH:MM" 格式返回答案。
 * 如果不能确定有效时间，则返回空字符串。
 *
 * 规律/规则：
 * 1. 四维坐标系的数学排列组合
 * 2. 24小时格式，24小时制时间。小时不大于24，分钟不大于60
 * <p></p>
 * 特性：
 * 1. 通过数学加减法的等价转换，将四维坐标系降为三维坐标系
 * 2. 时间大小等价于字符串比较
 *
 * @author guangyi
 */
public class LargestTimeFromDigits {
    /**
     * 最大的小时
     */
    private static final int MAX_HOUR = 24;
    /**
     * 最大的分钟
     */
    private static final int MAX_MINUTE = 60;

    public static String largestTimeFromDigits(int[] arr) {
        int length = arr.length;
        int indexSum = 0;
        for (int i = 0; i < length; i++) {
            indexSum += i;
        }
        String maxHourMinute = "";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    if (i == j || i == k || j == k) {
                        // 重复下标
                        continue;
                    }
                    // 通过数学加减法的等价转换，将四维坐标系降为三维坐标系
                    int n = indexSum - i - j - k;
                    int hour = arr[i] * 10 + arr[j];
                    int minute = arr[k] * 10 + arr[n];
                    if (hour < MAX_HOUR && minute < MAX_MINUTE) {
                        // 满足时间约束
                        String hourMinute = "" + arr[i] + arr[j] + ':' + arr[k] + arr[n];
                        // 时间大小等价于字符串比较
                        if (hourMinute.compareTo(maxHourMinute) > 0) {
                            maxHourMinute = hourMinute;
                        }
                    }
                }
            }
        }
        return maxHourMinute;
    }
}
