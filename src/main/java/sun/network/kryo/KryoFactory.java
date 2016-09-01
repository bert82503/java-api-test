package sun.network.kryo;

import java.net.URI;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.UUID;

import de.javakaffee.kryoserializers.ArraysAsListSerializer;
import de.javakaffee.kryoserializers.EnumMapSerializer;
import de.javakaffee.kryoserializers.GregorianCalendarSerializer;
import de.javakaffee.kryoserializers.KryoReflectionFactorySupport;
import de.javakaffee.kryoserializers.SynchronizedCollectionsSerializer;
import de.javakaffee.kryoserializers.URISerializer;
import de.javakaffee.kryoserializers.UUIDSerializer;
import de.javakaffee.kryoserializers.UnmodifiableCollectionsSerializer;

import com.esotericsoftware.kryo.Kryo;

/**
 * {@link Kryo} 工厂。
 * <p/>
 * <pre>
 * <a href="https://github.com/EsotericSoftware/kryo">Kryo</a>
 * <a href="https://github.com/magro/kryo-serializers">kryo-serializers</a>
 * </pre>
 *
 * @author xingle
 * @since 2016年08月31日 19:00
 */
public final class KryoFactory {

    /**
     * Creates a new {@link Kryo} instance.
     */
    protected static Kryo createKryo() {
        /*
         * KryoReflectionFactorySupport - kryo specialization that uses sun's ReflectionFactory to
         * create new instances for classes without a default constructor
         */
        Kryo kryo = new KryoReflectionFactorySupport();
        // configure kryo instance, customize settings
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);

        kryo.register(Arrays.asList("").getClass(), new ArraysAsListSerializer());
        kryo.register(URI.class, new URISerializer());
        kryo.register(UUID.class, new UUIDSerializer());
        kryo.register(EnumMap.class, new EnumMapSerializer());
        kryo.register(GregorianCalendar.class, new GregorianCalendarSerializer());

        UnmodifiableCollectionsSerializer.registerSerializers(kryo);
        SynchronizedCollectionsSerializer.registerSerializers(kryo);

        return kryo;
    }


    private KryoFactory() {
        // prevent instantiation
    }
}
