package sun.lang;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test of {@link String}.
 *
 * @since 1.0
 */
public class StringTest {

    @Test
    public void equal() {
        assertThat("str".equals(null)).isFalse();
        assertThat("str".equals("")).isFalse();
        assertThat("str".equals("null")).isFalse();
        assertThat("str".equals("str")).isTrue();
        assertThat("str" == "str").isTrue();
    }

    @Test(dataProvider = "equalsIgnoreCaseTestData")
    public void equalsIgnoreCase(String s1, String s2, boolean expected) {
        assertThat(s1.equalsIgnoreCase(s2)).isEqualTo(expected);
    }

    @DataProvider(name = "equalsIgnoreCaseTestData")
    private static Object[][] equalsIgnoreCaseTestData() {
        return new Object[][]{
            {"Phoenix", "phoenix", true},
            {"phoenix", "Phoenix", true},
        };
    }

    @Test(dataProvider = "indexOfTestData")
    public void indexOf(String str, int ch, int expectedIndex) {
        int index = str.indexOf(ch);
        assertThat(index).isEqualTo(expectedIndex);
    }

    @DataProvider(name = "indexOfTestData")
    private static Object[][] indexOfTestData() {
        return new Object[][]{
                {"dannong@wacai.com", '@', 7},
        };
    }


    @Test
    public void lastIndexOf() {
        String uri = "https://api.free.cn/biz/get_token";
        int i = uri.lastIndexOf('/');
        assertThat(i).isGreaterThan(0);
        String path = uri.substring(i + 1);
        assertThat(path).isEqualTo("get_token");

        String serviceInterface = "org.free.DemoService";
        int lastIndexOf = serviceInterface.lastIndexOf('.');
        assertThat(serviceInterface.substring(lastIndexOf + 1)).isEqualTo("DemoService");
    }


    @Test(dataProvider = "substringTestData")
    public void substring(String str, int beginIndex, int endIndex, String expected) {
        String subString = str.substring(beginIndex, endIndex);
        assertThat(subString).isEqualTo(expected);
    }

    @DataProvider(name = "substringTestData")
    private static Object[][] substringTestData() {
        return new Object[][]{
                {"1.65", 0, 3, "1.6"},
        };
    }


    @Test(dataProvider = "matchesTestData")
    public void matches(String jarName, String regex, boolean result) {
        assertThat(jarName.matches(regex)).isEqualTo(result);
    }

    @DataProvider(name = "matchesTestData")
    private static Object[][] matchesTestData() {
        return new Object[][]{
            {"mybatis-3.5.1-SNAPSHOT.jar", "^mybatis-\\d+.\\d+.\\d+-\\w+.jar$", true},
            {"mybatis-3.5.10-SNAPSHOT.jar", "^mybatis-\\d+.\\d+.\\d+-\\w+.jar$", true},
            {"mybatis-3.5.10-snapshot.jar", "^mybatis-\\d+.\\d+.\\d+-\\w+.jar$", true},
            {"mybatis-3.5.10-RELEASE.jar", "^mybatis-\\d+.\\d+.\\d+-\\w+.jar$", true},
            {"mybatis-spring-1.3.1-SNAPSHOT.jar", "^mybatis-\\d+.\\d+.\\d+-\\w+.jar$", false},
        };
    }

    @Test(dataProvider = "replaceFirstTestData")
    public void replaceFirst(String str, String regex, String replacement, String expected) {
        String replaceAll = str.replaceFirst(regex, replacement);
        assertThat(replaceAll).isEqualTo(expected);
    }

    @DataProvider(name = "replaceFirstTestData")
    private static Object[][] replaceFirstTestData() {
        return new Object[][]{
            {"test$com.service.CreditCardService:1.0$", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0$"},
            {"test-com.service.CreditCardService:-1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:-1.0"},
            {"test_com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test+com.service.CreditCardService:1.0+", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0+"},
            {"test!com.service.CreditCardService:1.0!", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0!"},
            {"test~com.service.CreditCardService:~1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:~1.0"},
            /// 没替换
            {"test:com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test:com.service.CreditCardService:1.0"},
        };
    }

    @Test(dataProvider = "replaceAllTestData")
    public void replaceAll(String str, String regex, String replacement, String expected) {
        String replaceAll = str.replaceAll(regex, replacement);
        assertThat(replaceAll).isEqualTo(expected);
    }

    @DataProvider(name = "replaceAllTestData")
    private static Object[][] replaceAllTestData() {
        return new Object[][]{
            {"test$com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test-com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test_com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test+com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test!com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            {"test~com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test/com.service.CreditCardService:1.0"},
            /// 没替换
            {"test:com.service.CreditCardService:1.0", "[$\\-_+!~]", "/", "test:com.service.CreditCardService:1.0"},
        };
    }

    @Test(dataProvider = "valueOfTestData")
    public void valueOf(Object obj, String expected) {
        String str = String.valueOf(obj);
        assertThat(str).isEqualTo(expected);
    }

    @DataProvider(name = "valueOfTestData")
    private static Object[][] valueOfTestData() {
        return new Object[][]{
                {0L, "0"},
        };
    }


    @Test(dataProvider = "compareToTestData")
    public void compareTo(String str1, String str2, int expected) {
        if (expected > 0) {
            assertThat(str1.compareTo(str2)).isGreaterThan(0);
        } else if (expected < 0) {
            assertThat(str1.compareTo(str2)).isLessThan(0);
        } else {
            assertThat(str1.compareTo(str2)).isEqualTo(0);
        }
    }

    @DataProvider(name = "compareToTestData")
    private static Object[][] compareToTestData() {
        return new Object[][]{
                {"5", "12", 1},
                {"1", "12", -1},
        };
    }
}
