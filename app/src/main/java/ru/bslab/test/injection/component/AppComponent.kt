package ru.bslab.test.injection.component

import android.app.Application
import android.content.Context
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Component
import ru.bslab.test.data.DataManager
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.data.remote.BsLabApi
import ru.bslab.test.data.remote.BsLabSecondApi
import ru.bslab.test.injection.ApplicationContext
import ru.bslab.test.injection.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun bsLabApi(): BsLabApi

    fun bsLabSecondApi(): BsLabSecondApi

    fun preferencesHelper(): PreferencesHelper

    fun glide(): RequestManager

    fun glidePrefs(): RequestOptions

}