package ru.bslab.test.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.bslab.test.injection.ActivityContext

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }
}