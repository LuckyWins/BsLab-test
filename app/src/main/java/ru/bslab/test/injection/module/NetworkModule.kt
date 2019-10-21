package ru.bslab.test.injection.module

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.bslab.test.BuildConfig
import ru.bslab.test.data.local.PreferencesHelper
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule(private val context: Context) {

    private fun getBsLabUrl(preferencesHelper: PreferencesHelper) = preferencesHelper.bsLabUrl

    private fun getNewBsLabUrl(preferencesHelper: PreferencesHelper) = preferencesHelper.bsLabNewUrl

    @Provides
    @Singleton
    @Named("bslab")
    internal fun provideRetrofit(@Named("general") okHttpClient: OkHttpClient, moshi: Moshi, preferencesHelper: PreferencesHelper): Retrofit =
        Retrofit.Builder()
            .baseUrl(getBsLabUrl(preferencesHelper))
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("bslab-second")
    internal fun provideBsLabRetrofit(@Named("general") okHttpClient: OkHttpClient, moshi: Moshi, preferencesHelper: PreferencesHelper): Retrofit =
        Retrofit.Builder()
            .baseUrl(getNewBsLabUrl(preferencesHelper))
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    @Named("general")
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                                     chuckInterceptor: ChuckInterceptor,
                                     stethoInterceptor: StethoInterceptor,
                                     loggingInterceptor: LoggingInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor { chain ->
            val original = chain?.request()

            val request = original?.newBuilder()
                ?.header("Content-Type", "text/json;charset=utf-8")
                ?.method(original.method(), original.body())
                ?.build()

            chain!!.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            //httpClientBuilder.addInterceptor(httpLoggingInterceptor)
            httpClientBuilder.addInterceptor(loggingInterceptor)
            httpClientBuilder.addInterceptor(chuckInterceptor)
            httpClientBuilder.addNetworkInterceptor(stethoInterceptor)
        }
        return httpClientBuilder.build()

    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): LoggingInterceptor =
        LoggingInterceptor.Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            //.setLevel(Level.HEADERS)
            .log(Platform.INFO)
            .addHeader("version", BuildConfig.VERSION_NAME)
            .build()

    @Provides
    @Singleton
    internal fun provideChuckInterceptor(): ChuckInterceptor = ChuckInterceptor(context)

    @Provides
    @Singleton
    internal fun provideStetho(): StethoInterceptor = StethoInterceptor()

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

}