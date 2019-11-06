package com.app.catalog.domain.entities

import android.os.Parcelable
import com.app.catalog.commons.utils.Constants.Companion.ID_API_TAG
import com.app.catalog.commons.utils.Constants.Companion.NAME_API_TAG
import com.app.catalog.commons.utils.Constants.Companion.SALE_API_TAG
import com.app.catalog.commons.utils.Constants.Companion.URL_API_TAG
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Products(
    @field:Json(name = ID_API_TAG) val productId: String?,
    @field:Json(name = NAME_API_TAG) val productName: String?,
    @field:Json(name = URL_API_TAG) val productImageUrl: String?,
    @field:Json(name = SALE_API_TAG) val productPrice: ProductPrice?
) : Parcelable