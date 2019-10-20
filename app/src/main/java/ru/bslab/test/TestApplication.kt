package ru.bslab.test

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.singhajit.sherlock.core.Sherlock
import com.squareup.leakcanary.LeakCanary
import com.tspoon.traceur.Traceur
import ru.bslab.test.injection.component.AppComponent
import ru.bslab.test.injection.component.DaggerAppComponent
import ru.bslab.test.injection.module.AppModule
import ru.bslab.test.injection.module.NetworkModule
import timber.log.Timber

class TestApplication : MultiDexApplication() {

    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): TestApplication {
            return context.applicationContext as TestApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
            Sherlock.init(this)
            Traceur.enableLogging()
        }
    }

    // Needed to replace the component with a test specific one
    var component: AppComponent
        get() {
            if (appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .networkModule(NetworkModule(this))
                    .build()
            }
            return appComponent as AppComponent
        }
        set(appComponent) {
            this.appComponent = appComponent
        }
}