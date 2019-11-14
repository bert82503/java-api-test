package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 35. Custom verification failure message 自定义验证失败的消息 (Since 2.1.0)
 * <p>
 * Allows specifying a custom message to be printed if verification fails.
 * 如果验证失败，则允许指定要打印的自定义消息。
 *
 * @since 2019-11-14
 */
public class CustomVerificationFailureMessage {

    @Test
    public void verifyCustomFailureMessage() {
        // mock creation
        List<Object> mock = mock(List.class);

        mock.size();

        // will print a custom message on verification failure
        verify(mock, description("This will print on failure")).size();

        mock.size();

        // will work with any verification mode
        verify(mock, times(2).description("size method should be called twice")).size();
    }
}
