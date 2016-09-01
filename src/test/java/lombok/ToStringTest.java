package lombok;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Test of @{@link lombok.ToString}.
 *
 * @author xingle
 * @since 2016年05月09日 14:51
 */
public class ToStringTest {

    private static final Logger logger = LoggerFactory.getLogger(ToStringTest.class);


    @Test
    public void toStringTest() {
        Sub sub = new Sub()
                .setId(37483817L)
                .setName("sub");
        sub.setIsDeleted(false)
                .setCreated(36473627)
                .setUpdated(36473627);

        logger.info("sub: {}", sub);
    }

    // ToStringTest.Sub(id=37483817, name=sub)
    @ToString
    private class Sub extends Super {

        @Getter
        private long id;

        @Getter
        private String name;

        public Sub setId(long id) {
            this.id = id;
            return this;
        }

        public Sub setName(String name) {
            this.name = name;
            return this;
        }
    }

    @ToString
    private class Super {

        @Getter
        boolean isDeleted;

        @Getter
        int created;

        @Getter
        int updated;

        public Super setIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Super setCreated(int created) {
            this.created = created;
            return this;
        }

        public Super setUpdated(int updated) {
            this.updated = updated;
            return this;
        }
    }

}
