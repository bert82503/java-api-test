package sun.util.service.provider;

/**
 * 模块服务。
 *
 * @author xingle
 * @since 2016年08月25日 20:32
 */
public interface Module {

    /**
     * 模块的启动级别，决定了模块启动关闭的顺序。
     *
     * @return 模块的启动级别
     */
    int getStartLevel();

    /**
     * 初始化模块。
     *
     * @throws Exception
     */
    void init() throws Exception;

}
