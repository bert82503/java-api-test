package leet.code.stack.monotonic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Test of {@link DailyTemperatures}.
 *
 * @author guangyi
 */
public class DailyTemperaturesTest {

    @Test(dataProvider = "dailyTemperaturesTestData")
    public void dailyTemperatures(int[] temperatures, int[] expected) {
        int[] result = DailyTemperatures.dailyTemperatures(temperatures);
        assertThat(result).isEqualTo(expected);
    }

    @DataProvider(name = "dailyTemperaturesTestData")
    private static Object[][] dailyTemperaturesTestData() {
        return new Object[][]{
                {new int[]{73,74,75,71,69,72,76,73},
                        new int[]{1,1,4,2,1,1,0,0}},
                {new int[]{30,40,50,60},
                        new int[]{1,1,1,0}},
                {new int[]{30,60,90},
                        new int[]{1,1,0}},
        };
    }
}
