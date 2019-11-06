package com.app.catalog.domain.entities

import android.os.Parcelable
import com.app.catalog.commons.utils.Constants.Companion.AMOUNT_API_TAG
import com.app.catalog.commons.utils.Constants.Companion.CURRENCY_API_TAG
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductPrice(
    @field:Json(name = AMOUNT_API_TAG) val productAmount: String?,
    @field:Json(name = CURRENCY_API_TAG) val productCurrency: String?
) : Parcelable