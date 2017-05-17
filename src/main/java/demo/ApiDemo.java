package demo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demo of API.
 *
 * @author dannong
 * @since 2017年04月08日 15:11
 */
public class ApiDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApiDemo.class);

    public void invoke() {
        String thirdPartyResponse = null;
        try {
            // invoke third party code (第三方靠谱吗？)
            // ...
            thirdPartyResponse = "OK";
        } catch (Exception e) { // 远程调用失败(XxxException)
            logger.error("error message, param:{}", "value", e);
        }

        if (StringUtils.isEmpty(thirdPartyResponse)) { // 返回值校验/null
            // TODO
        }
    }

}
