package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 15. Capturing arguments for further assertions 为进一步断言捕获参数 (Since 1.8.0)
 * <p>
 * Mockito verifies argument values in natural java style: by using an <code>equals()</code> method.
 * This is also the recommended way of matching arguments because it makes tests clean & simple.
 * In some situations though, it is helpful to assert on certain arguments after the actual verification.
 * Mockito以自然的java样式验证参数值：通过使用equals()方法。
 * 这也是推荐的匹配参数的方法，因为它使测试简洁明了。
 * 但是，在某情况下，在实际验证之后对某些参数进行断言是有帮助的。
 * <p>
 * <b>Warning:</b> it is recommended to use ArgumentCaptor with verification <b>but not</b> with stubbing.
 * Using ArgumentCaptor with stubbing may decrease test readability because captor is created outside of assert (aka verify or 'then') block.
 * Also it may reduce defect localization because if stubbed method was not called then no argument is captured.
 * 警告：建议对验证使用ArgumentCaptor，但不要对存根使用。
 * 结合存根使用ArgumentCaptor可能会降低测试的可读性，因为捕手是在断言块之外创建的。
 * 同样，它可以减少缺陷的定位，因为如果未调用存根方法，则不会捕获任何参数。
 * <p>
 *
 * @since 2019-11-12
 */
public class CapturingArgumentsForFurtherAssertions {

    @Test
    public void captureArgumentsForFurtherAssertions() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        mockedList.add("John");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockedList).add(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo("John");
    }
}
