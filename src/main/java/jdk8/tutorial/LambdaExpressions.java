package jdk8.tutorial;

import java.util.List;

/**
 * Lambda expressions. (Lambda表达式)
 *
 * @author dannong
 * @since 2016年10月22日 16:33
 */
public class LambdaExpressions {

    /**
     * how to sort a list of strings. (如何排序字符串列表)
     */
    public static void sortString(List<String> list) {
        // prior versions of Java 8
        // creating anonymous comparators and pass them to the sort method
//        Collections.sort(list, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o2.compareTo(o1);
//            }
//        });

        // Java 8 comes with a much shorter syntax, lambda expressions:
//        Collections.sort(list, (String o1, String o2) -> {
//            return o2.compareTo(o1);
//        });
        // For one line method bodies you can skip both the braces {} and the return keyword
//        Collections.sort(list, (String o1, String o2) -> o2.compareTo(o1));

        // Java编译器知道该参数的类型（类型推导：目标类型、目标类型的上下文、词法作用域、变量捕获、方法引用）
        list.sort(((o1, o2) -> o2.compareTo(o1)));
    }

    // 泛型方法
    public static <T extends Comparable<T>> void sort(List<T> list) {
//        Collections.sort(list, (T o1, T o2) -> o2.compareTo(o1));

        // gets even shorter
        list.sort(((o1, o2) -> o2.compareTo(o1)));
    }

}
