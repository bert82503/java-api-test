package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 36. Java 8 Lambda Matcher Support Lambda表达式匹配器支持 (Since 2.1.0)
 *
 * @since 2019-11-14
 */
public class LambdaMatcherSupport {

    @Test
    public void lambdaMatcher() {
        // mock creation
        List<Object> mock = mock(List.class);

        mock.add("foo");
        mock.add("bee");

        // verify a list only had strings of a certain length added to it
        // note - this will only compile under Java 8
        verify(mock, times(2))
                .add(argThat((String argument) -> argument.length() < 5));
    }
}
