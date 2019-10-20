package ru.bslab.test.injection.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.bslab.test.data.remote.BsLabApi
import ru.bslab.test.data.remote.BsLabSecondApi
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    internal fun provideBsLabApi(@Named("bslab") retrofit: Retrofit): BsLabApi = retrofit.create(BsLabApi::class.java)

    @Provides
    @Singleton
    internal fun provideBsLabSecondApi(@Named("bslab-second") retrofit: Retrofit): BsLabSecondApi = retrofit.create(BsLabSecondApi::class.java)

}