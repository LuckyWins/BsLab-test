package ru.bslab.test.injection.module

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import ru.bslab.test.injection.ApplicationContext
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class AppModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideGlide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @Singleton
    internal fun provideGlidePrefs(): RequestOptions {
        return RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
    }

}