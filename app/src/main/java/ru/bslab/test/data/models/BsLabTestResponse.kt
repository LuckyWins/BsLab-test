package ru.bslab.test.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BsLabTestResponse(

    @Json(name = "providers")
    val providers: List<BsLabProvider>? = null

) : Parcelable

@Parcelize
data class BsLabProvider(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "image_url")
    val imageUrl: String? = null,

    @Json(name = "gift_cards")
    val giftCards: List<BsLabCard>? = null

) : Parcelable

@Parcelize
data class BsLabCard(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "featured")
    val featured: Boolean? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "credits")
    val credits: Long? = null,

    @Json(name = "image_url")
    val imageUrl: String? = null,

    @Json(name = "codes_count")
    val codesCount: Long? = null,

    @Json(name = "currency")
    val currency: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "redeem_url")
    val redeemUrl: String? = null

) : Parcelable