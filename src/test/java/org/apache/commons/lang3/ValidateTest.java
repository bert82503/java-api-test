package org.apache.commons.lang3;

import org.testng.annotations.Test;

/**
 * Unit test of {@link Validate}.
 *
 * @author xingle
 * @since 2016年06月22日 11:03
 */
public class ValidateTest {

    @Test(expectedExceptions = {NullPointerException.class, IllegalArgumentException.class},
            expectedExceptionsMessageRegExp = "'str' is empty")
    public void notEmpty_Null() {
        Validate.notEmpty((String) null, "'str' is empty", "");
        Validate.notEmpty("", "'str' is empty", "");
    }

}
