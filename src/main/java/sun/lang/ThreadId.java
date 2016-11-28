package sun.lang;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * For example, the class below generates unique identifiers local to each thread.
 * A thread's id is assigned the first time it invokes ThreadId.get() and remains unchanged on subsequent calls.
 *
 * @see java.lang.ThreadLocal
 * @author xingle
 * @since 2016年06月17日 15:35
 */
public class ThreadId {

    /**
     * Atomic integer containing the next thread ID to be assigned
     */
    private static final AtomicInteger nextId = new AtomicInteger(0);

    /**
     * Thread local variable containing each thread's ID
     */
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override
                protected Integer initialValue() {
                    return nextId.incrementAndGet();
                }
            };

    /**
     * Returns the current thread's unique ID, assigning it if necessary
     *
     * @return the current thread's unique ID
     */
    public static int get() {
        return threadId.get();
    }

    /**
     * Removes the current thread's unique ID for this thread-local variable.
     */
    public static void remove() {
        threadId.remove();
    }

}
