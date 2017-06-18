package me.ritesh.wallpapers.data.net;

import java.io.IOException;
import me.ritesh.wallpapers.data.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private final static String AUTHORIZATION = "key";
    private final static String TOKEN = BuildConfig.PIXABAY_API_TOKEN;

    @Override public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder().addQueryParameter(AUTHORIZATION, TOKEN).build();

        Request.Builder requestBuilder = original.newBuilder().url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
