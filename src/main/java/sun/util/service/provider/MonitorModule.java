package sun.util.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 监听模块。
 *
 * @author xingle
 * @since 2016年08月25日 20:41
 */
public class MonitorModule implements Module {

    private static final Logger logger = LoggerFactory.getLogger(MonitorModule.class);


    public MonitorModule() {
//        logger.info("create 'MonitorModule'");
        throw new RuntimeException("create 'MonitorModule' exception");
    }


    @Override
    public int getStartLevel() {
        return 2;
    }

    @Override
    public void init() throws Exception {
        logger.info("init 'MonitorModule'");
    }
}
