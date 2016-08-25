package sun.util.service.provider;

import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.ServiceLoader;

/**
 * The demo of {@link ServiceLoader}.
 *
 * @author xingle
 * @since 2016年08月25日 20:45
 */
public class ServiceLoaderDemo {

    private static final Logger logger = LoggerFactory.getLogger(ServiceLoaderDemo.class);


    public static void main(String[] args) {
        // 1. 加载模块
        ServiceLoader<Module> modules = ServiceLoader.load(Module.class); // 基于SPI机制来实例化模块

        // 2. 对模块启动顺序排序(根据startLevel由小到大排序，startLevel值最小的最先启动)
        Collections.sort(Lists.newArrayList(modules), new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
                return o1.getStartLevel() - o2.getStartLevel();
            }
        });

        // 3. 初始化所有加载的模块(调用每个模块的init方法)
        for (Module module : modules) {
            try {
                module.init();
            } catch (Exception e) {
                logger.error("'{}' Module init error", module.getClass().getName(), e);
            }
        }
    }

}
