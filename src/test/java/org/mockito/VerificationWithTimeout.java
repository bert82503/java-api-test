package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 22. Verification with timeout 超时验证
 * <p>
 * Allows verifying with timeout. It causes a verify to wait for a specified period of time for a desired
 * interaction rather than fails immediately if had not already happened. May be useful for testing in concurrent
 * conditions.
 * 允许验证超时。它使验证等待指定的时间段以进行所需的交互，而不是如果尚未发生则立即失败。
 * 在并发条件下进行测试可能有用。
 * <p>
 * This feature should be used rarely - figure out a better way of testing your multi-threaded system.
 * 很少使用此功能，找出测试多线程系统的更好方法。
 *
 * @since 2019-11-14
 */
public class VerificationWithTimeout {

    @Test
    public void verifyWithTimeout() {
        // mock creation
        List<Object> mock = mock(List.class);

        // stubbing
        when(mock.get(0)).thenReturn("foo");

        assertThat(mock.get(0)).isEqualTo("foo");

        // verification
        verify(mock, timeout(10L).times(1)).get(0);
    }
}
