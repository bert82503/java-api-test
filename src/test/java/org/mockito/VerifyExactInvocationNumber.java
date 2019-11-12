package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 4. Verifying exact number of invocations / at least x / never 验证确切的调用次数
 *
 * @since 2019-11-12
 */
public class VerifyExactInvocationNumber {

    @Test
    public void verifyExactInvocationNumber() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        // using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        // following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        // exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        // verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        // verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("twice");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atLeast(3)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }
}
