package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 3. Argument matchers 参数匹配器
 * <p>
 * Mockito verifies argument values in natural java style: by using an <code>equals()</code> method.
 * Sometimes, when extra flexibility is required then you might use argument matchers:
 * Mockito以自然的java样式验证参数值：通过使用equals()方法。
 * 有时，当需要额外的灵活性时，您可以使用参数匹配器：
 * <p>
 * Argument matchers allow flexible verification or stubbing.
 * {@link ArgumentMatchers Click here} {@link org.mockito.hamcrest.MockitoHamcrest or here} to see more built-in matchers
 * and examples of <b>custom argument matchers / hamcrest matchers</b>.
 * 参数匹配器允许灵活地验证或存根。
 * 点击{@link ArgumentMatchers}查看更多的内置匹配器以及自定义的参数匹配器/hamcrest匹配器的示例。
 *
 * @since 2019-11-12
 */
public class ArgumentMatchers3 {

    @Test
    public void matchArgument() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        // stubbing using built-in anyInt() argument matcher
//        when(mockedList.get(anyInt())).thenReturn("element");
        when(mockedList.get(eq(999))).thenReturn("element");
        // above is correct - eq() is also an argument matcher

        // stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
//        when(mockedList.contains(argThat(isValid()))).thenReturn("element");

        // following prints "element"
        assertThat(mockedList.get(999)).isEqualTo("element");

        // you can also verify using an argument matcher
        verify(mockedList, times(1)).get(anyInt());

        mockedList.add("element");

        // argument matchers can also be written as Java 8 Lambdas
        verify(mockedList).add(argThat(
                (String argument) -> argument.length() > 5));
    }
}
