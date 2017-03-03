package org.apache.commons.lang3;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test for {@link StringUtils}.
 *
 * @author dannong
 * @since 2017年03月03日 18:56
 */
public class StringUtilsTest {

    @Test(dataProvider = "substringTestData")
    public void substring(String email, String expected) {
        String emailSuffix = StringUtils.substring(email, email.indexOf('@') + 1);
        assertThat(emailSuffix).isEqualTo(expected);
    }

    @DataProvider(name = "substringTestData")
    private static Object[][] substringTestData() {
        return new Object[][]{
                {"edwardlee@qq.com", "qq.com"},
                {"edwardlee@139.com", "139.com"},
                {"edwardlee@163.com", "163.com"},
                {"edwardlee@126.com", "126.com"},
        };
    }

}
