package sun.lang;

import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link ThreadId}.
 *
 * @author xingle
 * @since 2016年06月17日 17:20
 */
public class ThreadIdTest {

    @Test
    public void get() {
        assertThat(ThreadId.get()).isEqualTo(0); // 测试主线程

        ThreadFactory threadFactory = Executors.defaultThreadFactory(); // 默认的线程工厂
        for (int i = 1; i < 10; i++) {
            Thread thread = threadFactory.newThread(new ThreadLocalRunnable(i));
            thread.start();
        }

        ThreadId.remove();
    }

    static class ThreadLocalRunnable implements Runnable {

        private int id;

        public ThreadLocalRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            int threadId = ThreadId.get(); // 新创建的子线程
            assertThat(threadId).isEqualTo(id);

            ThreadId.remove();
        }

    }

}
