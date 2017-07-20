package com.alibaba.fastjson;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.common.collect.Maps;

import org.testng.annotations.Test;

import java.util.Map;

/**
 * Test of {@link JSON}.
 *
 * @author dannong
 * @since 2017年07月12日 15:38
 */
public class JSONTest {

    @Test
    public void toJSONString() {
        BankParam bankParam = new BankParam().setEntryName("37236638385647");
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("bankJob", bankParam);
        String jsonString = JSON.toJSONString(map);
        assertThat(jsonString).isEqualTo("{\"bankJob\":{\"entryName\":\"37236638385647\"}}");
    }


    private static class BankParam {
        private String entryName;

        public String getEntryName() {
            return entryName;
        }

        public BankParam setEntryName(String entryName) {
            this.entryName = entryName;
            return this;
        }
    }
}
