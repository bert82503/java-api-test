package org.apache.commons.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test of {@link FilenameUtils}.
 *
 * @author dannong
 * @since 2017年03月23日 15:28
 */
public class FilenameUtilsTest {

    @Test(dataProvider = "getExtensionTestData")
    public void getExtension(String filename, String expected) {
        String extension = FilenameUtils.getExtension(filename);
        assertThat(extension).isEqualTo(expected);
    }

    @DataProvider(name = "getExtensionTestData")
    private static Object[][] getExtensionTestData() {
        return new Object[][]{
                {"test.txt", "txt"},
                {"test.csv", "csv"},
                {"test.xlsx", "xlsx"},
        };
    }

    @Test(dataProvider = "isExtensionTestData")
    public void isExtension(String filename, String extension, boolean expected) {
        boolean res = FilenameUtils.isExtension(filename, extension);
        assertThat(res).isEqualTo(expected);
    }

    @DataProvider(name = "isExtensionTestData")
    private static Object[][] isExtensionTestData() {
        return new Object[][]{
                {"test.txt", "txt", true},
                {"test.csv", "csv", true},
                {"test.xlsx", "xlsx", true},
                {"test.xlsx", "docx", false},
        };
    }
}
