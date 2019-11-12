package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 5. Stubbing void methods with exceptions 存根带异常的void方法
 * <p>
 * 模拟方法执行异常场景。
 *
 * @since 2019-11-12
 */
public class StubbingVoidMethodsWithExceptions {

    @Test
    public void stubVoidMethodsWithExceptions() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        doThrow(new RuntimeException("boom!"))
                .when(mockedList).clear();

        // following throws RuntimeException:
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(mockedList::clear)
                .withMessage("boom!");
    }
}
