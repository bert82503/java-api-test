package leet.code.calculator;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 224. 基本计算器
 * <p></p>
 * https://leetcode-cn.com/problems/basic-calculator/
 * <p></p>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * <p>
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 * @author guangyi
 */
public class CalculateTwo {
    private static final char PLUS = '+';
    private static final char SUBTRACT = '-';

    private static final char LEFT_BRACKET = '(';
    private static final char RIGHT_BRACKET = ')';

    private static final Set<Character> OPERATOR_SET;

    static {
        OPERATOR_SET = new HashSet<>(8);
        OPERATOR_SET.add(PLUS);
        OPERATOR_SET.add(SUBTRACT);
    }

    public static int calculate(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return calculate_String(str, 0)[0];
    }

    public static int[] calculate_String(String str, int i) {
        // 左侧操作数堆栈
        Deque<Integer> leftOperandDeque = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        int len = str.length();
        while (i < len) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                // 数字
                num = num * 10 + ch - '0';
            } else if (OPERATOR_SET.contains(ch)) {
                // 运算符
                eval(leftOperandDeque,preSign,num);
                preSign = ch;
                num = 0;
            } else if (ch == LEFT_BRACKET) {
                // 递归条件，入栈
                int[] tmp = calculate_String(str, i + 1);
                num = tmp[0];
                i = tmp[1];
            } else if (ch == RIGHT_BRACKET) {
                // 终止条件，出栈
                // 处理最后的数字
                eval(leftOperandDeque,preSign,num);
                // 结果计算
                int sum = leftOperandDeque.stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                return new int[]{sum, i};
            }
            i++;
        }
        // 处理最后的数字
        eval(leftOperandDeque,preSign,num);
        // 结果计算
        int sum = leftOperandDeque.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return new int[]{sum, i};
    }

    public static int calculate_Deque(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        Deque<Character> charDeque = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            charDeque.add(ch);
        }
        return calculate_Deque(charDeque);
    }

    private static int calculate_Deque(Deque<Character> charDeque) {
        // 左侧操作数堆栈
        Deque<Integer> leftOperandDeque = new LinkedList<>();
        char preSign = '+';
        int num = 0;
        while (!charDeque.isEmpty()) {
            char ch = charDeque.poll();
            if (Character.isDigit(ch)) {
                // 数字
                num = num * 10 + ch - '0';
            } else if (ch == LEFT_BRACKET) {
                // 递归条件，入栈
                num = calculate_Deque(charDeque);
            } else if (ch == RIGHT_BRACKET) {
                // 终止条件，出栈
                break;
            } else if (OPERATOR_SET.contains(ch)) {
                // 运算符
                eval(leftOperandDeque, preSign, num);
                preSign = ch;
                num = 0;
            }
        }
        // 结果计算
        eval(leftOperandDeque, preSign, num);
        return leftOperandDeque.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static void eval(Deque<Integer> leftOperandDeque, char preSign, int rightOperand) {
        switch (preSign) {
            case PLUS:
                leftOperandDeque.addLast(rightOperand);
                break;
            case SUBTRACT:
                leftOperandDeque.addLast(-rightOperand);
                break;
            default:
                break;
        }
    }
}
