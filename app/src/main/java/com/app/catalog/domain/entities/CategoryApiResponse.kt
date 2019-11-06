package com.app.catalog.domain.entities

import android.os.Parcelable
import com.app.catalog.commons.utils.Constants.Companion.NAME_API_TAG
import com.app.catalog.commons.utils.Constants.Companion.PRODUCTS_API_TAG
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryApiResponse(
    @field:Json(name = NAME_API_TAG) val name: String?,
    @field:Json(name = PRODUCTS_API_TAG) val products : List<Products>
) : Parcelable