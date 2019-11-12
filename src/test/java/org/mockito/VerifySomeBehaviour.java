package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 1. Let's verify some behaviour!
 *
 * @since 2019-11-11
 */
public class VerifySomeBehaviour {

    @Test
    public void verifyListBehaviour() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        // using mock object
        mockedList.add("one");
        mockedList.clear();

        // verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
