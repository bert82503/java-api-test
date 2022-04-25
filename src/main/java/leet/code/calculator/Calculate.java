package leet.code.calculator;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 面试题 16.26. 计算器
 * <p></p>
 * https://leetcode-cn.com/problems/calculator-lcci/
 * <p></p>
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 *
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

    private static final char SPACE = ' ';

    public static int calculate(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        // 左侧操作数堆栈
        Deque<Integer> leftOperandDeque = new LinkedList<>();
        int num = 0;
        char preOperator = '+';
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == SPACE) {
                // ignore
                continue;
            } else if (Character.isDigit(ch)) {
                // 数字
                num = num * 10 + ch - '0';
            } else if (ch == PLUS || ch == SUBTRACT || ch == MULTIPLY || ch == DIVIDE) {
                eval(leftOperandDeque, preOperator, num);
                num = 0;
                preOperator = ch;
            }
        }
        eval(leftOperandDeque, preOperator, num);
        int sum = 0;
        while (!leftOperandDeque.isEmpty()) {
            sum += leftOperandDeque.removeFirst();
        }
        return sum;
    }

    private static void eval(Deque<Integer> leftOperandDeque, char preOperator, int rightOperand) {
        switch (preOperator) {
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
