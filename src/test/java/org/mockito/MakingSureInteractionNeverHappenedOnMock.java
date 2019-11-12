package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 7. Making sure interaction(s) never happened on mock 确保模拟中从未发生过互动
 *
 * @since 2019-11-12
 */
public class MakingSureInteractionNeverHappenedOnMock {

    @Test
    public void makeSureInteractionNeverHappened() {
        // mock creation
        List<Object> mockOne = mock(List.class);

        // using mocks - only mockOne is interacted
        mockOne.add("one");

        // ordinary verification
        verify(mockOne).add("one");

        // verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        List<Object> mockTwo = mock(List.class);
        List<Object> mockThree = mock(List.class);

        // verify that other mocks were not interacted
        verifyNoInteractions(mockTwo, mockThree);
    }
}
