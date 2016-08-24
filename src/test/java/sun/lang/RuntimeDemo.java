package sun.lang;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

/**
 * The demo of {@link Runtime}.
 *
 * @author xingle
 * @since 2016年08月24日 16:26
 */
public class RuntimeDemo {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeDemo.class);


    /**
     * 注册一个新的虚拟机关闭钩子(回调线程)。
     */
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("FRAMEWORK-SHUTDOWN-HOOK-THREAD")
                .setDaemon(true)
                .build();
        // 关闭钩子回调线程
        Thread shutdownHook = threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                logger.info("begin to shutdown FrameworkLauncher");
            }
        });
        // 添加关闭钩子
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

}
