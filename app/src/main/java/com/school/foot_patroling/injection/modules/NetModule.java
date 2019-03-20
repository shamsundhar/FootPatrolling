package com.school.foot_patroling.injection.modules;
import com.school.foot_patroling.BuildConfig;
import com.school.foot_patroling.injection.scopes.PerApplication;
import com.school.foot_patroling.register.RegisterApi;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

//    @Provides
//    @PerApplication
//    static Gson provideGson() {
//        return new GsonBuilder()
//                // Custom type adapters for models are not needed when using Gson, but this
//                // type adapter is a good example if you want to write one yourself.
//                .registerTypeAdapter(Country.class, CountryTypeAdapter.INSTANCE)
//                // These type adapters for RealmLists are needed, since RealmString and RealmStringMapEntry
//                // wrappers are not recognized by Gson in the default configuration.
//                .create();
//    }

    @Provides
    @PerApplication
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @PerApplication
    static OkHttpClient.Builder provideHttpClientBuilder(OkHttpClient okHttpClient){
        OkHttpClient.Builder httpClientBuilder = okHttpClient.newBuilder();

        //adding request headers interceptor.
//        httpClientBuilder.addInterceptor(new Interceptor() {
//            @Override public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder builder = request.newBuilder();
//                builder.addHeader("Client-Service", "shkang$@)!*");
//                builder.addHeader("Auth-key", "psangady#2018");
//                builder.addHeader("Content-Type", "application/json");
//                builder.build();
//                return chain.proceed(request);
//            }
//        });
        //adding logging interceptor
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(loggingInterceptor);
        }
        return httpClientBuilder;
    }

    @Provides
    @PerApplication
    static RegisterApi provideRegisterApi(OkHttpClient.Builder httpClientBuilder ) {
        return new Retrofit.Builder()
                .baseUrl("http://www.google.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .callFactory(httpClientBuilder.build())
                .build().create(RegisterApi.class);
    }
}
