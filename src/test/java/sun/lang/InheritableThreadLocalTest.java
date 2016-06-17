package sun.lang;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link CustomInheritableThreadLocal}.
 *
 * @author xingle
 * @since 2016年06月17日 18:12
 */
public class InheritableThreadLocalTest {

    private CustomInheritableThreadLocal<String> inheritableThreadLocal;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        inheritableThreadLocal = CustomInheritableThreadLocal.newCustomInheritableThreadLocal();
    }

    @Test
    public void childThreadLocal() {
        inheritableThreadLocal.set("parent thread");
        assertThat(inheritableThreadLocal.get()).isEqualTo("parent thread");

        Thread thread = Executors.defaultThreadFactory().newThread(
                new Runnable() {
                    @Override
                    public void run() {
                        String threadLocal = inheritableThreadLocal.get();
                        assertThat(threadLocal).isEqualTo("parent thread"); // 验证从父线程继承了本地变量

                        inheritableThreadLocal.set("sub thread"); // 只会更新子线程的本地变量
                        threadLocal = inheritableThreadLocal.get();
                        assertThat(threadLocal).isEqualTo("sub thread");

                        // action

                        // 清理并释放本地变量
                        inheritableThreadLocal.clearAll();
                    }
                }
        );
        thread.start();

        assertThat(inheritableThreadLocal.get()).isEqualTo("parent thread"); // 验证父线程的本地变量未受影响
    }

    @Test
    public void executorService() throws InterruptedException, ExecutionException, TimeoutException {
        inheritableThreadLocal.set("thread pool");
        assertThat(inheritableThreadLocal.get()).isEqualTo("thread pool");

        RunnableFuture<String> runnableFuture = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String threadLocal = inheritableThreadLocal.get();
                assertThat(threadLocal).isEqualTo("thread pool"); // 验证从父线程继承了本地变量

                inheritableThreadLocal.set("Callable.call()"); // 只会更新子线程的本地变量
                threadLocal = inheritableThreadLocal.get();
                assertThat(threadLocal).isEqualTo("Callable.call()");

                // action

                // 清理并释放本地变量
                inheritableThreadLocal.clearAll();

                return "Callable.call()";
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(runnableFuture);

        String result = runnableFuture.get(100L, TimeUnit.MILLISECONDS);
        assertThat(result).isEqualTo("Callable.call()");

        assertThat(inheritableThreadLocal.get()).isEqualTo("thread pool"); // 验证父线程的本地变量未受影响
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        inheritableThreadLocal.clearAll();
    }

}
