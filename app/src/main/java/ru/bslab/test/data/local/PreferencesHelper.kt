package ru.bslab.test.data.local

import android.content.Context
import android.content.SharedPreferences
import ru.bslab.test.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesHelper @Inject
constructor(@ApplicationContext context: Context) {

    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    var bsLabUrl: String
        get() = preferences.getString(PREF_BSLAB_URL, "https://imya.bslab.ru/")!!
        set(value) { preferences.edit().putString(PREF_BSLAB_URL, value).apply() }

    var bsLabNewUrl: String
        get() = preferences.getString(PREF_BSLAB_NEW_URL, "https://imya.bslab.ru/")!!
        set(value) { preferences.edit().putString(PREF_BSLAB_NEW_URL, value).apply() }

    //GENERAL FUNC
    fun clear() {
        preferences.edit().clear().apply()
    }

    companion object {
        private const val PREF_FILE_NAME = "bslab_pref_file"

        //URL'S
        private const val PREF_BSLAB_URL = "PREF_BSLAB_URL"
        private const val PREF_BSLAB_NEW_URL = "PREF_BSLAB_NEW_URL"
    }

}