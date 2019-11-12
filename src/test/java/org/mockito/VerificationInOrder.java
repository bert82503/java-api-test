package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 6. Verification in order 验证顺序
 *
 * @since 2019-11-12
 */
public class VerificationInOrder {

    @Test
    public void verifyInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List<Object> singleMock = mock(List.class);

        // using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        // create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(singleMock);

        // following will make sure that add is first called with "was added first", then with "was added second"
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");


        // B. Multiple mocks that must be used in a particular order
        List<Object> firstMock = mock(List.class);
        List<Object> secondMock = mock(List.class);

        // using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        // create inOrder object passing any mocks that need to be verified in order
        inOrder = inOrder(firstMock, secondMock);

        // following will make sure that firstMock was called before secondMock
        inOrder.verify(firstMock).add("was called first");
        inOrder.verify(secondMock).add("was called second");

        // Oh, and A + B can be mixed together at will
    }
}
