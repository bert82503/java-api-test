package org.mockito;

import org.testng.annotations.BeforeSuite;

/**
 * Abstract unit test class of TestNG.
 *
 * 9. Shorthand for mocks creation - @Mock annotation 模拟对象创建的简写——@Mock注解
 *
 * <ul>
 * <li>Allows shorthand creation of objects required for testing.</li>
 * 允许快速地创建测试所需的对象。
 * <li>Minimizes repetitive mock creation code.</li>
 * 最小化重复的模拟对象的创建代码。
 * <li>Makes the test class more readable.</li>
 * 使测试类更具可读性。
 * <li>Makes the verification error easier to read because the <b>field name</b>
 * is used to identify the mock.</li>
 * 因为字段名称用于标识模拟对象，所以使验证错误更易于阅读。
 * </ul>
 *
 * @see org.mockito.MockitoAnnotations
 * @since 2019-11-12
 */
// [FAQ] Is Mockito thread-safe?
//@Test(singleThreaded = true)
public abstract class TestNGBaseTestCase {

    @BeforeSuite(alwaysRun = true)
    public void initMocks() {
        // Important! This needs to be somewhere in the base class or a test runner:
        // 重要！这需要在基类或测试运行器中：
        MockitoAnnotations.initMocks(this);
    }

}
