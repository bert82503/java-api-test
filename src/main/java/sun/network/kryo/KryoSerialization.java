package sun.network.kryo;

import java.io.Serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 基于 {@link Kryo} 实现的序列化和反序列化辅助类。
 *
 * @author xingle
 * @since 2016年08月31日 18:39
 */
public final class KryoSerialization {

    /**
     * 序列化对象。
     *
     * @param obj 待序列化的对象
     * @param <T> 对象类型
     * @return 序列化后的字节数组
     */
    public static <T extends Serializable> byte[] serialize(T obj) {
        Kryo kryo = KryoThreadLocal.getKryoHolder().get();
        try (Output output = KryoThreadLocal.getOutputHolder().get()) {
            kryo.writeObject(output, obj);
            byte[] out = output.getBuffer();
            output.clear();
            return out;
        }
    }

    /**
     * 反序列化对象。
     *
     * @param in    序列化后的字节数组
     * @param clazz 类型对象
     * @param <T>   对象类型
     * @return 原始对象
     */
    public static <T extends Serializable> T deserialize(byte[] in, Class<T> clazz) {
        Kryo kryo = KryoThreadLocal.getKryoHolder().get();
        try (Input input = KryoThreadLocal.getInputHolder().get()) {
            input.setBuffer(in);
            return kryo.readObject(input, clazz);
        }
    }

    private KryoSerialization() {
        // prevent instantiation
    }
}
