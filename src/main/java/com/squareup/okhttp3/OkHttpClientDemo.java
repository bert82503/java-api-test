package com.squareup.okhttp3;

import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp用户指南。
 * <p>
 * <a href="https://square.github.io/okhttp/recipes/">OkHttp Recipes</a>
 *
 * @author guangyi
 * @since 2021-02-23
 */
public class OkHttpClientDemo {

    private static final String HTTP_URL
            = "https://iris-preview.hermesborderguru.io/oauth/token";

    public static void main(String[] args) throws IOException {
        System.out.println(getToken());
    }

    public static String getToken() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();
        return post(HTTP_URL, formBody);
    }

    private static String post(String url, RequestBody body) throws IOException {
        OkHttpClient client = createOkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", applyCredential())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                return "";
            }
            return responseBody.string();
        }
    }

    private static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5L, TimeUnit.SECONDS)
                .callTimeout(5L, TimeUnit.SECONDS)
                .readTimeout(5L, TimeUnit.SECONDS)
                .writeTimeout(5L, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Reactive Authentication
     * <pre>   {@code
     *    String credential = Credentials.basic(...)
     *    return response.request().newBuilder()
     *        .header("Authorization", credential)
     *        .build();
     * }</pre>
     * <p>
     * <a href="https://square.github.io/okhttp/recipes/#handling-authentication-kt-java">
     *     Handling authentication</a>
     *
     * @see okhttp3.Authenticator
     */
    private static String applyCredential() {
        String username = "TESTRJ4HZM";
        String password = "x%x4'*;0O4a3:vLi";
        return Credentials.basic(username, password);
    }
}
