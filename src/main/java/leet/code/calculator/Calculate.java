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
    private static final char MULTIPLY = '*';
    private static final char DIVIDE = '/';
    private static final char PLUS = '+';
    private static final char SUBTRACT = '-';

    private static final char SPACE = ' ';

    public static int calculate(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return depthFirstSearch(str, 0);
    }

    private static int depthFirstSearch(String str, int i) {
        // 堆栈
        // 操作数
        Deque<Integer> operandDeque = new LinkedList<>();
        // 运算符
        Deque<Character> operatorDeque = new LinkedList<>();

        // 1.整数、乘除计算
        int num = 0;
        while (i < str.length()) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                // 数字
                num = num * 10 + ch - '0';
            } else if (ch == MULTIPLY || ch == DIVIDE) {
                // 出栈，递归的终止条件
                // 右操作数
                int rightOperand = 0;
                while (++i < str.length()) {
                    char c = str.charAt(i);
                    if (Character.isDigit(c)) {
                        rightOperand = rightOperand * 10 + c - '0';
                    } else if (c == SPACE) {
                        // ignore
                    } else {
                        i--;
                        break;
                    }
                }
                // 下一个运算符
                if (ch == MULTIPLY) {
                    num = num * rightOperand;
                } else {
                    // ch == DIVIDE
                    num = num / rightOperand;
                }
            } else if (ch == PLUS || ch == SUBTRACT) {
                // 入栈，递归的开启条件
                operandDeque.addLast(num);
                num = 0;
                operatorDeque.addLast(ch);
            }
            i++;
        }
        operandDeque.addLast(num);
        // 2.加减计算
        // 未考虑到加减法计算顺序，从左往右
        int result = 0;
        while (!operatorDeque.isEmpty()) {
            char op = operatorDeque.removeFirst();
            int leftOperand = operandDeque.removeFirst();
            int rightOperand = operandDeque.removeFirst();
            if (op == PLUS) {
                result = leftOperand + rightOperand;
            } else if (op == SUBTRACT) {
                result = leftOperand - rightOperand;
            }
            operandDeque.addFirst(result);
        }
        return operandDeque.removeFirst();
    }
}
