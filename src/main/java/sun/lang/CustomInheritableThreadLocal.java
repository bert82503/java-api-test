package sun.lang;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Custom implementation of InheritableThreadLocal.
 *
 * @see java.lang.InheritableThreadLocal
 * @author xingle
 * @since 2016年06月17日 17:57
 */
public class CustomInheritableThreadLocal<T> extends InheritableThreadLocal<T> {

    private static final List<ThreadLocal<?>> threadLocalList = Lists.newLinkedList();

    private CustomInheritableThreadLocal() {
        threadLocalList.add(this);
    }

    public static <E> CustomInheritableThreadLocal<E> newCustomInheritableThreadLocal() {
        return new CustomInheritableThreadLocal<>();
    }

    /**
     * 清空当前线程持有的所有本地变量。
     */
    public static void clearAll() {
        for (ThreadLocal<?> threadLocal : threadLocalList) {
            threadLocal.remove();
        }
    }

}
