package com.google.common.base;

import org.testng.annotations.Test;

/**
 * Unit test of {@link Preconditions}.
 *
 * @author xingle
 * @since 2016年08月28日 11:10
 */
public class PreconditionsTest {

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "'id' is invalid, id: 0")
    public void checkArgument() {
        long id = 0L;
        Preconditions.checkArgument(id > 0L, "'id' is invalid, id: %s", id);
    }

}
