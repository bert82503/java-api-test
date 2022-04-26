package leet.code.stack;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link MinStack}.
 *
 * @author guangyi
 */
public class MinStackTest {

    @Test
    public void operator() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        assertThat(minStack.getMin())
                .isEqualTo(-3);
        minStack.pop();
        assertThat(minStack.top())
                .isEqualTo(0);
        assertThat(minStack.getMin())
                .isEqualTo(-2);
    }
}
