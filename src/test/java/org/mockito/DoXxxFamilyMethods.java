package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 12. doReturn()|doThrow()|doAnswer()|doNothing()|doCallRealMethod() family of methods
 * doXxx方法家族
 * <p>
 * Stubbing void methods requires a different approach from {@link Mockito#when(Object)} because the compiler does not
 * like void methods inside brackets...
 * 对void方法进行存根，需要与{@link Mockito#when(Object)}不同的方法，
 * 因为编译器不喜欢方括号内的void方法。
 * <p>
 * Use <code>doThrow()</code> when you want to stub a void method with an exception:
 * 当您想通过异常存根void方法时，请使用doThrow()：
 *
 * @since 2019-11-12
 */
public class DoXxxFamilyMethods {

    @Test
    public void doXxxMethods() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        doThrow(new RuntimeException("boom!"))
                .when(mockedList).clear();
        // following throws RuntimeException:
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(mockedList::clear)
                .withMessage("boom!");

        // You have to use doReturn() for stubbing:
//        doReturn("foo").when(spy).get(0);
        doReturn("foo")
                .when(mockedList).get(6);
        assertThat(mockedList.get(6)).isEqualTo("foo");
    }
}
