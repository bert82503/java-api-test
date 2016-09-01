package sun.network;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Test of {@link SerializationUtil}.
 *
 * @author xingle
 * @since 2016年08月31日 11:40
 */
public class SerializationUtilTest {

    @Test
    public void primitiveType() {
        // Integer
        encodeDecode(Integer.MIN_VALUE);
        // Long
        encodeDecode(Long.MAX_VALUE);
        // String
        encodeDecode("");
        encodeDecode("dif641die@!~438#放大了……%……*(&肥嘟嘟$%$((");
        // List
        encodeDecode(Lists.newArrayList(Integer.MAX_VALUE, 0, -1, 1));
        encodeDecode(Lists.newArrayList(Long.MIN_VALUE, 0L, -3L, 3L));
        encodeDecode(Lists.newArrayList("", "3dafy78dh@~@!78343@~@32"));

        // User
        encodeDecode(buildUser());
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

    private static <T extends Serializable> void encodeDecode(T obj) {
        T deserializeObj = SerializationUtil.deserialize(SerializationUtil.serialize(obj));
        assertThat(deserializeObj).isEqualTo(obj);
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
