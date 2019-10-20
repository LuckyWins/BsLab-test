package ru.bslab.test.data

import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.bslab.test.data.local.PreferencesHelper
import ru.bslab.test.data.models.BsLabTestResponse
import ru.bslab.test.data.remote.BsLabApi
import ru.bslab.test.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(@ApplicationContext private val appContext: Context,
            private val preferencesHelper: PreferencesHelper,
            private val bsLabApi: BsLabApi) {

    fun testRequest() = bsLabApi.testRequest()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
}