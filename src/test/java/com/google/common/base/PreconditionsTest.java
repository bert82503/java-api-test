package com.google.common.base;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Unit test of {@link Preconditions}.
 *
 * @author xingle
 * @since 2016年08月28日 11:10
 */
public class PreconditionsTest {

    @Test(dataProvider = "checkArgumentTestData",
            expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "'id' is invalid, id: 0")
    public void checkArgument(long id) {
        Preconditions.checkArgument(id > 0L, "'id' is invalid, id: %s", id);
    }

    @DataProvider(name = "checkArgumentTestData")
    private static Object[][] checkArgumentTestData() {
        return new Object[][]{
                {0L},
        };
    }

}
