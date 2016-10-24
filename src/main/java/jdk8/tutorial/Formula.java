package jdk8.tutorial;

/**
 * Default Methods for Interfaces. (接口的默认方法)
 * <p>
 * Add non-abstract method implementations to interfaces by utilizing the <b>default</b> keyword.
 * This feature is also known as <a href="http://stackoverflow.com/a/24102730">virtual extension methods (虚拟扩展方法)</a>.
 * <p>
 * 使用<b>default</b>关键字往接口添加非抽象方法实现，此功能也称为<b><i>虚拟扩展方法</i></b>。
 *
 * @author dannong
 * @since 2016年10月22日 15:10
 */
public interface Formula {
    double calculate(int a);

    // 核心实现 接口的默认方法
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
