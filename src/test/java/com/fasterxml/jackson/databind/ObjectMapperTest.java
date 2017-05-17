package com.fasterxml.jackson.databind;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;

/**
 * Test of {@link ObjectMapper}.
 * <p>
 * Fields are in lower-case letters, separated by underscores.
 *
 * @author dannong
 * @since 2017年05月17日 17:28
 */
public class ObjectMapperTest {

    /**
     * Mapper instances are fully thread-safe
     */
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }


    @Test(enabled = true)
    public void readValue() throws IOException {
        String response = "{\"return_code\":\"0\",\"token\":\"a8d7as********sjdabca\",\"token_expiry\":\"2016-09-03 23:11:56\"}";
        TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);

        assertThat(tokenResponse).isNotNull();
        assertThat(tokenResponse.getReturnCode()).isEqualTo(0);
        assertThat(tokenResponse.getToken()).isEqualTo("a8d7as********sjdabca");
        assertThat(tokenResponse.getTokenExpiry()).isEqualTo("2016-09-03 23:11:56");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TokenResponse {

        @JsonProperty("return_code")
        private int returnCode;

        @JsonProperty("token")
        private String token;

        @JsonProperty("token_expiry")
        private String tokenExpiry;


        public int getReturnCode() {
            return returnCode;
        }

        public String getToken() {
            return token;
        }

        public String getTokenExpiry() {
            return tokenExpiry;
        }
    }
}
