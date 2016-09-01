package sun.network;

import java.io.Serializable;

import org.springframework.util.SerializationUtils;

/**
 * 序列化和反序列化的静态工具。
 *
 * @author xingle
 * @since 2016年08月31日 11:30
 */
public final class SerializationUtil {

    /**
     * Serialize the given object to a byte array.
     *
     * @param obj the object to serialize
     * @return an array of bytes representing the object in a portable fashion
     */
    public static byte[] serialize(Serializable obj) {
        return SerializationUtils.serialize(obj);
    }

    /**
     * Deserialize the byte array into an object.
     *
     * @param in a serialized object
     * @return the result of deserialization the bytes
     */
    public static <T extends Serializable> T deserialize(byte[] in) {
        return (T) SerializationUtils.deserialize(in);
    }


    private SerializationUtil() {
        // prevent instantiation
    }
}
