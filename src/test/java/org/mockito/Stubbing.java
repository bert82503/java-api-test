package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;

import org.testng.annotations.Test;

/**
 * 2. How about some stubbing? 存根怎么样？
 *
 * @since 2019-11-11
 */
public class Stubbing {

    @Test
    public void stub() {
        // You can mock concrete classes, not just interfaces
        LinkedList<String> mockedList = mock(LinkedList.class);

        // stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException("boom!"));

        // following prints "first"
        assertThat(mockedList.get(0)).isEqualTo("first");
        assertThat(mockedList.get(0)).isEqualTo("first");

        // following throws runtime exception
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> mockedList.get(1))
                .withMessage("boom!");

        // following prints "null" because get(999) was not stubbed
        assertThat(mockedList.get(999)).isNull();

        // Although it is possible to verify a stubbed invocation, usually it's just redundant
        // If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        // If your code doesn't care what get(0) returns, then it should not be stubbed.
        String s = verify(mockedList, times(2)).get(0);
        assertThat(s).isNull();
    }
}
