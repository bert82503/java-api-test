package sun.time;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZoneId;

import org.testng.annotations.Test;

/**
 * Timezones. (时区：由{@link ZoneId}表示)
 *
 * @author dannong
 * @since 2016年10月26日 14:33
 */
public class TimezonesTest {
//    private static final Logger logger = LoggerFactory.getLogger(TimezonesTest.class);

    @Test
    public void functionTest() {
//        logger.info("All available zone IDs: {}", ZoneId.getAvailableZoneIds());

        ZoneId shanghaiZone = ZoneId.of("Asia/Shanghai");
        ZoneId berlinZone = ZoneId.of("Europe/Berlin");
//        ZoneId systemDefaultZone = ZoneId.systemDefault(); // Asia/Shanghai

        assertThat(shanghaiZone.getRules().toString()).isEqualTo("ZoneRules[currentStandardOffset=+08:00]");
        assertThat(berlinZone.getRules().toString()).isEqualTo("ZoneRules[currentStandardOffset=+01:00]");
    }
}
