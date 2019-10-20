package ru.bslab.test.data.remote

import io.reactivex.Single
import retrofit2.http.HTTP
import ru.bslab.test.data.models.BsLabTestResponse

interface BsLabSecondApi {

    @HTTP(method = "GET", path = "/", hasBody = false)
    fun getIpAddress(): Single<BsLabTestResponse>

}