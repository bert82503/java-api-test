package org.mockito;

import static org.mockito.Mockito.*;

import java.util.List;

import org.testng.annotations.Test;

/**
 * 8. Finding redundant invocations 查找多余的调用
 *
 * <p>
 * A word of <b>warning</b>:
 * Some users who did a lot of classic, expect-run-verify mocking tend to use <code>verifyNoMoreInteractions()</code> very often, even in every test method.
 * <code>verifyNoMoreInteractions()</code> is not recommended to use in every test method.
 * <code>verifyNoMoreInteractions()</code> is a handy assertion from the interaction testing toolkit. Use it only when it's relevant.
 * Abusing it leads to <strong>overspecified</strong>, <strong>less maintainable</strong> tests.
 * 一个警告：一些做过很多经典的"期望-运行-验证"模式的用户，
 * 往往会经常使用verifyNoMoreInteractions()，即使在每个测试方法中也是如此。
 * 不建议在每个测试方法中都使用verifyNoMoreInteractions()。
 * verifyNoMoreInteractions()是来自交互测试工具包的便捷断言。
 * 仅在相关时使用，滥用它会导致规范过度且难以维护的测试。
 *
 * <p>
 * See also {@link Mockito#never()} - it is more explicit and
 * communicates the intent well.
 * 另请参见{@link Mockito#never()}——更加明确且可以很好地传达意图。
 *
 * @since 2019-11-12
 */
public class FindingRedundantInvocations {

    @Test
    public void findRedundantInvocations() {
        // mock creation
        List<Object> mockedList = mock(List.class);

        // using mocks
        mockedList.add("one");
//        mockedList.add("two");

        verify(mockedList).add("one");
        verify(mockedList, never()).add("two");

        // following verification will fail
        verifyNoMoreInteractions(mockedList);
    }
}
