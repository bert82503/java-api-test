package sun.util.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册模块。
 *
 * @author xingle
 * @since 2016年08月25日 20:38
 */
public class RegisterModule implements Module {

    private static final Logger logger = LoggerFactory.getLogger(RegisterModule.class);


    public RegisterModule() {
        logger.info("create 'RegisterModule'");
    }


    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public void init() throws Exception {
        logger.info("init 'RegisterModule'");
    }

}
