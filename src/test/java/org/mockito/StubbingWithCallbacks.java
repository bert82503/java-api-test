package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.mockito.stubbing.Answer;
import org.testng.annotations.Test;

/**
 * 11. Stubbing with callbacks 带回调的存根
 * <p>
 * Allows stubbing with generic {@link Answer} interface.
 * 允许使用通用的{@link Answer}接口进行存根。
 * <p>
 * Yet another controversial feature which was not included in Mockito
 * originally. We recommend simply stubbing with <code>thenReturn()</code> or
 * <code>thenThrow()</code>, which should be enough to test/test-drive
 * any clean & simple code.
 * 另一个有争议的功能最初并未包含在Mockito中。
 * 我们建议仅使用thenReturn()或thenThrow()进行存根，应该足以测试/测试驱动任何干净简洁的代码。
 *
 * @since 2019-11-12
 */
public class StubbingWithCallbacks {

    @Test
    public void stubWithCallbacks() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        when(mockedList.get(6))
                .thenAnswer(invocation -> {
                    Object[] args = invocation.getArguments();
//                    Object mock = invocation.getMock();
                    return "called with arguments: " + Arrays.toString(args);
                });

        // Following prints "called with arguments: [foo]"
        assertThat(mockedList.get(6)).isEqualTo("called with arguments: [6]");
    }
}
