package me.ritesh.wallpapers.dependency;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.ritesh.wallpapers.BuildConfig;
import me.ritesh.wallpapers.MainApplication;
import me.ritesh.wallpapers.data.net.PixabayApi;
import me.ritesh.wallpapers.data.net.TokenInterceptor;
import okhttp3.Cache;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class NetworkModule {

    private static final String BASE_URL = "https://pixabay.com";

    @Singleton @Provides Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Singleton @Provides OkHttpClient provideOkHttpProxy(MainApplication mainApplication) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        Cache cache = new Cache(mainApplication.getCacheDir(), 1024 * 1024 * 10);
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(
                TlsVersion.TLS_1_2)
                .cipherSuites(CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build();
        return new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spec))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new TokenInterceptor())
                .addInterceptor(logging)
                .cache(cache)
                .build();
    }

    @Singleton @Provides PixabayApi providePixabayApi(OkHttpClient client, Gson gson) {
        Retrofit retrofit =
                new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .baseUrl(BASE_URL)
                        .build();

        return retrofit.create(PixabayApi.class);
    }
}
