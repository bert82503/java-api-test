package sun.lang;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

/**
 * Test of {@link System}.
 *
 * @author dannong.lihg
 */
public class SystemTest {

    @Test(enabled = false)
    public void getProperty() {
        String userHomeDir = System.getProperty("user.home");
        System.out.println("User home directory: " + userHomeDir);
        assertThat(userHomeDir).isEqualTo("/Users/dannong");
    }
}
