package org.mockito;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 26. Mocking details 模拟细节
 * <p>
 * Mockito offers API to inspect the details of a mock object.
 * This API is useful for advanced users and mocking framework integrators.
 * Mockito提供API来检查模拟对象的详细信息。
 * 这个API对高级用户和模拟框架集成商很有用。
 *
 * @since 2019-11-14
 */
public class MockingDetails3 {

    @Test
    public void mockDetails() {
        // mock creation
        List<Object> mock = mock(List.class);
        List<Object> spy = spy(new LinkedList<>());

        // To identify whether a particular object is a mock or a spy:
        mockingDetails(mock).isMock();
        mockingDetails(spy).isSpy();

        // Getting details like type to mock or default answer:
        MockingDetails details = mockingDetails(mock);
        details.getMockCreationSettings().getTypeToMock();
        details.getMockCreationSettings().getDefaultAnswer();

        // Getting invocations and stubbings of the mock:
        details.getInvocations();
        details.getStubbings();

        // Printing all interactions (including stubbing, unused stubs)
//        System.out.println(details.printInvocations());
    }
}
