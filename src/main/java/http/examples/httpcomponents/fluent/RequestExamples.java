package http.examples.httpcomponents.fluent;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Examples of {@link Request}.
 *
 * @author dannong
 * @since 2016年11月27日 22:44
 */
public class RequestExamples {

    private static final Logger logger = LoggerFactory.getLogger(RequestExamples.class);

    private static final int MILLIS_ONE_SECOND = (int) TimeUnit.SECONDS.toMillis(1L);
    private static final String CHARSET_UTF_8 = StandardCharsets.UTF_8.name();


    /**
     * Execute a GET request.
     */
    public static void doGet() {
        String uri = "https://www.baidu.com";
        try {
            // with timeout settings and return response content as String.
            Response response = Request.Get(uri)
                    .connectTimeout(MILLIS_ONE_SECOND)
                    .socketTimeout(MILLIS_ONE_SECOND)
                    .execute();
            HttpResponse httpResponse = response.returnResponse();
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                // 响应内容编码自适应？(好像没那么智能)
//                String responseContent = response.returnContent().asString(); // IllegalStateException: Response content has been already consumed
                String responseContent = EntityUtils.toString(
                        httpResponse.getEntity(), CHARSET_UTF_8); // OK

                logger.debug("response content: {}", responseContent);
            }


//            String responseContent = Request.Get(uri)
//                    .connectTimeout(MILLIS_ONE_SECOND)
//                    .socketTimeout(MILLIS_ONE_SECOND)
//                    .execute().returnContent().asString();
//            logger.debug("response content: {}", responseContent);
        } catch (IOException e) {
            logger.error("GET request failed, uri:{}", uri, e);
        }
    }

    /**
     * Execute a POST request.
     */
    public static void doPost() {
        String uri = "http://somehost/do-stuff";
        try {
            // with the 'expect-continue' handshake, using HTTP/1.1, containing a request body as String
            byte[] content = Request.Post(uri)
                    .useExpectContinue()
                    .version(HttpVersion.HTTP_1_1)
                    .bodyString("Important stuff", ContentType.DEFAULT_TEXT)
                    .execute().returnContent().asBytes();
            String responseContent = StringUtils.toEncodedString(content, StandardCharsets.UTF_8);

            // with a custom header through the proxy containing a request body as an HTML form
            Request.Post("http://somehost/some-form")
                    .addHeader("X-Custom-header", "stuff")
                    .viaProxy(HttpHost.create("myproxy:8080"))
                    .bodyForm(Form.form().add("username", "vip").add("password", "secret").build())
                    .execute().saveContent(new File("result.dump"));


            // use Executor directly in order to execute requests in a specific security context
            Executor executor = Executor.newInstance()
                    .auth(HttpHost.create("somehost"), "username", "password")
                    .auth(HttpHost.create("myproxy:8080"), "username", "password")
                    .authPreemptive("myproxy:8080");

            executor.execute(Request.Get("http://somehost/"))
                    .returnContent().asString();

            executor.execute(Request.Post("http://somehost/do-stuff")
                    .useExpectContinue()
                    .bodyString("Important stuff", ContentType.DEFAULT_TEXT))
                    .returnContent().asString();

            logger.debug("response content: {}", responseContent);

        } catch (IOException e) {
            logger.error("POST request failed, uri:{}", uri, e);
        }
    }

    public static void handleResponse() {
        String uri = "https://www.baidu.com";
        try {
            String responseContent = Request.Get(uri)
                    .execute().handleResponse(response -> {
                        StatusLine statusLine = response.getStatusLine();
                        HttpEntity entity = response.getEntity();
                        if (statusLine.getStatusCode() >= HttpStatus.SC_MULTIPLE_CHOICES) {
                            throw new HttpResponseException(
                                    statusLine.getStatusCode(), statusLine.getReasonPhrase());
                        }
                        if (entity == null) {
                            throw new ClientProtocolException("Response contains no content");
                        }

//                        ContentType contentType = ContentType.getOrDefault(entity);
//                        if (!contentType.equals(ContentType.APPLICATION_XML)) {
//                            throw new ClientProtocolException("Unexpected content type:" + contentType);
//                        }
//                        Charset charset = contentType.getCharset();
//                        if (charset == null) {
//                            charset = HTTP.DEF_CONTENT_CHARSET;
//                        }
//                        InputStream entityContent = entity.getContent();

                        return EntityUtils.toString(entity, CHARSET_UTF_8);
                    });

            logger.debug("response content: {}", responseContent);

        } catch (IOException e) {
            logger.error("POST request failed, uri:{}", uri, e);
        }
    }

    public static void main(String[] args) {
//        doGet();

        handleResponse();
    }

}
