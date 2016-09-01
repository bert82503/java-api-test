package sun.network.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * {@link ThreadLocal} of {@link Kryo}, {@link Output} and {@link Input}。
 *
 * @author xingle
 * @since 2016年08月31日 20:59
 */
final class KryoThreadLocal {

    /// Kryo
    public static ThreadLocal<Kryo> getKryoHolder() {
        return KRYO_HOLDER;
    }

    /**
     * {@link ThreadLocal} of {@link Kryo} instances.
     */
    private static final ThreadLocal<Kryo> KRYO_HOLDER = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return KryoFactory.createKryo();
        }
    };


    /// Output
    public static ThreadLocal<Output> getOutputHolder() {
        return OUTPUT_HOLDER;
    }

    public static final int BUFFER_SIZE = 4 * 1024; // 4K

    public static final int MAX_BUFFER_SIZE = 64 * BUFFER_SIZE; // 64K

    private static final ThreadLocal<Output> OUTPUT_HOLDER = new ThreadLocal<Output>() {
        @Override
        protected Output initialValue() {
            return new Output(BUFFER_SIZE, MAX_BUFFER_SIZE);
        }
    };


    /// Input
    public static ThreadLocal<Input> getInputHolder() {
        return INPUT_HOLDER;
    }

    private static final ThreadLocal<Input> INPUT_HOLDER = new ThreadLocal<Input>() {
        @Override
        protected Input initialValue() {
            return new Input();
        }
    };


    private KryoThreadLocal() {
        // prevent instantiation
    }
}
