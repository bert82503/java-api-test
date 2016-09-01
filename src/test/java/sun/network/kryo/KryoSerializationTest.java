package sun.network.kryo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Integration of {@link KryoSerialization}.
 *
 * @author xingle
 * @since 2016年09月01日 11:34
 */
public class KryoSerializationTest {

    @Test
    public void primitiveType() {
        // Integer
        encodeDecode(Integer.MIN_VALUE, Integer.class);
        // Long
        encodeDecode(Long.MAX_VALUE, Long.class);
        // String
        encodeDecode("", String.class);
        encodeDecode("dif641die@!~438#放大了……%……*(&肥嘟嘟$%$((", String.class);
        // List
        encodeDecode(Lists.newArrayList(Integer.MAX_VALUE, 0, -1, 1), ArrayList.class);
        encodeDecode(Lists.newArrayList(Long.MIN_VALUE, 0L, -3L, 3L), ArrayList.class);
        encodeDecode(Lists.newArrayList("", "3dafy78dh@~@!78343@~@32"), ArrayList.class);
        encodeDecode(Lists.newLinkedList(Arrays.asList("Hello,", " world!")), LinkedList.class);
        // Set
        encodeDecode(Sets.newHashSet("@!~#$%", "*^%()_+"), HashSet.class);
        encodeDecode(Sets.newLinkedHashSet(Arrays.asList(4382332L, 323843789L)), LinkedHashSet.class);
        encodeDecode(Sets.newLinkedHashSet(Arrays.asList("Edward Lee", "Fei Men")), LinkedHashSet.class);
        // Map
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> hashMap = Maps.newHashMap();
            hashMap.put("int", 24);
            hashMap.put("long", 38372L);
            hashMap.put("string", "#@~#@~%^$&%*(*)))+)_NB<M>GK<");
            hashMap.put("list", Lists.newArrayList());
            hashMap.put("set", Sets.newLinkedHashSet());
            hashMap.put("map", Maps.newHashMap());
            encodeDecode(hashMap, HashMap.class);
        }
        LinkedHashMap<String, Object> linkedHashMap = Maps.newLinkedHashMap();
        linkedHashMap.put(null, null);
        linkedHashMap.put("_int", 7384);
        encodeDecode(linkedHashMap, LinkedHashMap.class);

        // User
        encodeDecode(buildUser(), User.class);
    }

    /**
     * {@link com.esotericsoftware.kryo.Kryo}   基于{@link ThreadLocal}实现性能更优
     * <pre>
     * KryoPool:    avg time (ms): 346.0
     * ThreadLocal: avg time (ms): 298.3
     * </pre>
     *
     * {@link com.esotericsoftware.kryo.io.Output}  基于{@link ThreadLocal}实现性能优势很大
     * <pre>
     * ThreadLocal: avg time (ms): 334.9
     * new:         avg time (ms): 609.1
     * </pre>
     *
     * {@link com.esotericsoftware.kryo.io.Input}   性能差别不大
     * <pre>
     * ThreadLocal: avg time (ms): 332.4
     * new:         avg time (ms): 341.9
     * </pre>
     */
    @Test(enabled = false)
    public void benchmark() {
        long totalTime = 0L;

        int count = 10;
        for (int j = 0; j <= count; j++) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 500000; i++) {
                // Set
                encodeDecode(Sets.newLinkedHashSet(Arrays.asList(346376437L, 34734383L, 1346371237L)), LinkedHashSet.class);
            }
            long runTime = System.currentTimeMillis() - startTime;
            if (j != 0) {
                totalTime += runTime;
            }
            System.out.println("run time (ms): " + runTime);
        }

        double avgTime = 1.0 * totalTime / count;
        System.out.println("\navg time (ms): " + avgTime);
    }

    private static <T extends Serializable> void encodeDecode(T obj, Class<T> clazz) {
        T deserializeObj = KryoSerialization.deserialize(KryoSerialization.serialize(obj), clazz);
        assertThat(deserializeObj).isEqualTo(obj);
    }


    private static User buildUser() {
        Map<String, Object> objectMap = Maps.newHashMap();
        objectMap.put("int", 24);
        objectMap.put("long", 38473L);
        objectMap.put("string", "fdfy3432dafu");

        return new User().setName("xingle").setAge(30)
                .setObjectList(Lists.<Object>newArrayList(43, 438473L, "dfd137die"))
                .setObjectMap(objectMap);
    }

    private static class User implements Serializable {
        private static final long serialVersionUID = -6573400345937920925L;

        private String name;
        private int age;
        private List<Object> objectList;
        private Map<String, Object> objectMap;

        public User() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }

            User user = (User) o;

            return age == user.age && name.equals(user.name)
                    && objectList.equals(user.objectList)
                    && objectMap.equals(user.objectMap);
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            result = 31 * result + objectList.hashCode();
            result = 31 * result + objectMap.hashCode();
            return result;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public int getAge() {
            return age;
        }

        public User setAge(int age) {
            this.age = age;
            return this;
        }

        public List<Object> getObjectList() {
            return objectList;
        }

        public User setObjectList(List<Object> objectList) {
            this.objectList = objectList;
            return this;
        }

        public Map<String, Object> getObjectMap() {
            return objectMap;
        }

        public User setObjectMap(Map<String, Object> objectMap) {
            this.objectMap = objectMap;
            return this;
        }
    }

}
