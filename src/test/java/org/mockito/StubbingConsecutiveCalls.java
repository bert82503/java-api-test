package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 10. Stubbing consecutive calls (iterator-style stubbing) 存根连续地调用(迭代器式存根)
 * <p>
 * 有时，对于同一方法调用，我们需要对不同的返回值/异常进行存根。
 * 典型的用例可能是模拟迭代器。
 * 但是，在极少数情况下，对连续调用进行存根可能会很有用：
 * <p>
 * <strong>Warning</strong> : if instead of chaining {@code .thenReturn()} calls, multiple stubbing with the same matchers or arguments
 * is used, then each stubbing will override the previous one:
 * 警告：如果使用多个具有相同匹配器或参数的存根，而不是链式的.thenReturn()调用，则每个存根将覆盖前一个存根：
 *
 * @since 2019-11-12
 */
public class StubbingConsecutiveCalls {

    @Test
    public void stubConsecutiveCalls() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        when(mockedList.get(6))
                .thenThrow(new RuntimeException("boom!"))
                .thenReturn("foo");

        // First call: throws runtime exception:
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> mockedList.get(6))
                .withMessage("boom!");

        // Second call: prints "foo"
        assertThat(mockedList.get(6)).isEqualTo("foo");

        // Any consecutive call: prints "foo" as well (last stubbing wins).
        assertThat(mockedList.get(6)).isEqualTo("foo");
        assertThat(mockedList.get(6)).isEqualTo("foo");


        // Alternative, shorter version of consecutive stubbing:
        when(mockedList.get(9))
                .thenReturn("one", "two", "three");

        assertThat(mockedList.get(9)).isEqualTo("one");
        assertThat(mockedList.get(9)).isEqualTo("two");
        assertThat(mockedList.get(9)).isEqualTo("three");


        // All mock.someMethod("some arg") calls will return "two"
        when(mockedList.get(10)).thenReturn("one");
        when(mockedList.get(10)).thenReturn("two");

        assertThat(mockedList.get(10)).isEqualTo("two");
    }
}
