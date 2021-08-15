package com.fasterxml.jackson.databind;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.testng.annotations.Test;

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
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .setSerializationInclusion(Include.NON_NULL)
//                .setLocale(Locale.CHINA)
        ;
    }


    @Test
    public void readValue() throws IOException {
        String response = "{\"return_code\":\"0\",\"token\":\"a8d7as********sjdabca\",\"token_expiry\":\"2016-09-03 23:11:56\"}";
        TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);
        assertThat(tokenResponse).isNotNull();
        assertThat(tokenResponse.getReturnCode()).isEqualTo(0);
        assertThat(tokenResponse.getToken()).isEqualTo("a8d7as********sjdabca");
        assertThat(tokenResponse.getTokenExpiry()).isEqualTo("2016-09-03 23:11:56");


        tokenResponse = objectMapper.readValue("{}", TokenResponse.class);
        assertThat(tokenResponse).isNotNull();
        assertThat(tokenResponse.getReturnCode()).isEqualTo(-1);
        assertThat(tokenResponse.getToken()).isNull();
        assertThat(tokenResponse.getTokenExpiry()).isNull();
    }

    @Test(expectedExceptions = JsonMappingException.class,
            expectedExceptionsMessageRegExp = "No content to map due to end-of-input.*")
    public void readValue_Exception() throws IOException {
        objectMapper.readValue("", TokenResponse.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class TokenResponse {

        @JsonProperty("return_code")
        private int returnCode = -1;

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
