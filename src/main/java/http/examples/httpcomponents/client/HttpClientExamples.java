package http.examples.httpcomponents.client;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Examples of {@link CloseableHttpClient}.
 *
 * @author dannong
 * @since 2016年12月02日 14:50
 */
public class HttpClientExamples {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientExamples.class);

    private static final int MILLIS_ONE_SECOND;

    private static final RequestConfig requestConfig;

    static {
        MILLIS_ONE_SECOND = (int) TimeUnit.SECONDS.toMillis(1L);

        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(MILLIS_ONE_SECOND)
                .setConnectTimeout(MILLIS_ONE_SECOND)
                .setSocketTimeout(MILLIS_ONE_SECOND)
                .build();
    }


    /**
     * HttpClient resource deallocation.
     * <p>
     * 资源再分配。
     */
    private static String resourceDeallocation() {
        String url = "https://www.baidu.com";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) { // InternalHttpClient, httpClient.close()
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) { // response.close()
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8); // May be null
                }
            } catch (ClientProtocolException e) {
                logger.error("server protocol not support, url:{}", url, e);
            } catch (IOException e) {
                logger.error("httpClient execute failed, url:{}", url, e);
            }
        } catch (IOException e) { // CloseableHttpClient.close() 抛出这个异常
            // HttpClient resource deallocation
            // 实际调用InternalHttpClient.close()，其内部已捕获IOException
            logger.error("httpClient close failed, url:{}", url, e);
        }

        return StringUtils.EMPTY;
    }

    public static void main(String[] args) {
        String responseContent = resourceDeallocation();
        logger.debug("response content: {}", responseContent);
    }

}
