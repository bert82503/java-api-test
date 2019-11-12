package org.mockito;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * 13. Spying on real objects 监视真实的对象
 * <p>
 * You can create spies of real objects. When you use the spy then the <b>real</b> methods are called
 * (unless a method was stubbed).
 * 您可以创建真实对象的侦察。
 * 当您使用侦察时，将调用实际方法(除非对方法进行了存根)。
 * <p>
 * Spying on real objects can be associated with "partial mocking" concept.
 * 监视真实对象可以与"部分模拟"概念相关联。
 *
 * <h4>Important gotcha on spying real objects! 从事侦察活动的重要陷阱！</h4>
 * <ol>
 * <li>Sometimes it's impossible or impractical to use {@link Mockito#when(Object)} for stubbing spies.
 * Therefore when using spies please consider <code>doReturn</code>|<code>Answer</code>|<code>Throw()</code> family of
 * methods for stubbing.
 * 有时，使用{@link Mockito#when(Object)}来存根侦察是不可能或不切实际的。
 * 因此，在使用侦察对象时，请考虑使用doReturn|Answer|Throw()序列方法进行存根。
 *
 * <li>Mockito <b>*does not*</b> delegate calls to the passed real instance, instead it actually creates a copy of it.
 * So if you keep the real instance and interact with it, don't expect the spied to be aware of those interaction
 * and their effect on real instance state.
 * The corollary is that when an <b>*unstubbed*</b> method is called <b>*on the spy*</b> but <b>*not on the real instance*</b>,
 * you won't see any effects on the real instance.
 * </li>
 * Mockito不会将调用委派给传递的真实实例，而实际上是创建它的副本。
 * 因此，如果保留真实实例并与之交互，请不要期望侦察对象知道这些交互及其对真实实例状态的影响。
 * 必然的结果是，当在侦察对象上调用未使用的方法，而在真实实例上却未调用方法时，您将看不到任何对真实实例的影响。
 *
 * <li>Watch out for final methods.
 * Mockito doesn't mock final methods so the bottom line is: when you spy on real objects + you try to stub a final method = trouble.
 * Also you won't be able to verify those method as well.
 * </li>
 * 注意最终方法，Mockito不模拟最终方法，因此最重要的是：当您监视真实对象 + 尝试对最终方法进行存根 = 麻烦。
 * 另外，您也将无法验证这些方法。
 * </ol>
 *
 * @since 2019-11-12
 */
public class SpyingOnRealObjects {

    @Test
    public void spyOnRealObjects() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        // using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        // prints "one" - the first element of a list
        assertThat(spy.get(0)).isEqualTo("one");
        assertThat(spy.get(1)).isEqualTo("two");

        // size() method was stubbed - 100 is printed
        assertThat(spy.size()).isEqualTo(100);

        // optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    @Test
    public void impossibleCallRealMethod() {
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
//        when(spy.get(0)).thenReturn("foo");
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> when(spy.get(0)).thenReturn("foo"))
                .withMessage("Index: 0, Size: 0");

        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }
}
