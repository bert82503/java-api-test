package leet.code.calculator;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 227. 基本计算器 II
 * <p></p>
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 * <p></p>
 * <pre>
 * 思路
 * 由于乘除优先于加减计算，因此不妨考虑先进行所有乘除运算，并将这些乘除运算后的整数值放回原表达式的相应位置，
 * 则随后整个表达式的值，就等于一系列整数加减后的值。
 *
 * 基于此，我们可以用一个栈，保存这些（进行乘除运算后的）整数的值。
 * 对于加减号后的数字，将其直接压入栈中；
 * 对于乘除号后的数字，可以直接与栈顶元素计算，并替换栈顶元素为计算后的结果。
 *
 * 具体来说，遍历字符串 s，并用变量 preSign 记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为加号。
 * 每次遍历到数字末尾时，根据 preSign 来决定计算方式：
 * 加号：将数字压入栈；
 * 减号：将数字的相反数压入栈；
 * 乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。
 *
 * 代码实现中，若读到一个运算符，或者遍历到字符串末尾，即认为是遍历到了数字末尾。
 * 处理完该数字后，更新 preSign 为当前遍历的字符。
 *
 * 遍历完字符串 s 后，将栈中元素累加，即为该字符串表达式的值。
 * </pre>
 * 面试题 16.26. 计算器
 * <p></p>
 * https://leetcode-cn.com/problems/calculator-lcci/
 * <p></p>
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * <p>
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。
 * 整数除法仅保留整数部分。
 * <p></p>
 * 规律/规则：
 * 1.正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式的优先级
 * 2.正整数、乘(*)、除(/)，先处理
 * 3.加(+)、减(-)，后处理，且计算顺序从左往右
 *
 * @author guangyi
 */
public class Calculate {
    private static final char PLUS = '+';
    private static final char SUBTRACT = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';

    private static final Set<Character> OPERATOR_SET;

    static {
        OPERATOR_SET = new HashSet<>(8);
        OPERATOR_SET.add(PLUS);
        OPERATOR_SET.add(SUBTRACT);
        OPERATOR_SET.add(MULTIPLY);
        OPERATOR_SET.add(DIVIDE);
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
            } else if (OPERATOR_SET.contains(ch)) {
                eval(leftOperandDeque, preSign, num);
                preSign = ch;
                num = 0;
            }
        }
        eval(leftOperandDeque, preSign, num);
        // 结果计算
        return leftOperandDeque.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static int calculate(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return calculate_String(str, 0);
    }

    private static int calculate_String(String str, int i) {
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
                // 右操作数入栈，将运算符转换为整数
                eval(leftOperandDeque, preSign, num);
                preSign = ch;
                num = 0;
            }
            i++;
        }
        // 处理最后的数字
        eval(leftOperandDeque, preSign, num);
        // 结果计算
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
            case MULTIPLY:
                leftOperandDeque.addLast(leftOperandDeque.removeLast() * rightOperand);
                break;
            case DIVIDE:
                leftOperandDeque.addLast(leftOperandDeque.removeLast() / rightOperand);
                break;
            default:
                break;
        }
    }
}
