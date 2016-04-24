package org.apache.velocity.app;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for {@link Velocity}.
 *
 * @author xingle
 * @since 2016年04月24日 02:13
 */
public class VelocityTest {

    private Context context;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        context = new VelocityContext(new ConcurrentHashMap<>());
        context.put("name", "jason");
        context.put("Floog", "floogie woogie");
    }

    /**
     * Test for {@link Velocity#evaluate(Context, Writer, String, String)}.
     *
     * @param velocityTemplate 用来呈现的 Velocity 模板
     * @param expectedText     期望的文本
     */
    @Test(dataProvider = "evaluateTestData")
    public void evaluate(String velocityTemplate, String expectedText) {
        Writer out = new StringWriter(); // 用来呈现输出
        Velocity.evaluate(context, out, "VelocityTemplate", velocityTemplate);
        String compiledText = out.toString(); // 编译后的文本
        assertThat(compiledText).isEqualTo(expectedText);
    }

    @DataProvider(name = "evaluateTestData")
    private static Object[][] evaluateTestData() {
        return new Object[][]{
                {"My name is $name -> $Floog", "My name is jason -> floogie woogie"}, // $xxx：占位符
        };
    }

}
