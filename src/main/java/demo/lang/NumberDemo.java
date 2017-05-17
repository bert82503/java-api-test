package demo.lang;

/**
 * Demo of {@link Number}。
 *
 * @author dannong
 * @since 2017年04月06日 15:43
 */
public class NumberDemo {

    public void boxUnbox() {
        Integer x = 24_000;
        int y = x + 23; // NullPointerException
        Integer z = x + 23;
    }

    public void sum() {
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }

}
