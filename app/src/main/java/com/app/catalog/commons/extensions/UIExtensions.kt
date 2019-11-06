package com.app.catalog.commons.extensions

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.request.CachePolicy
import com.app.catalog.BuildConfig
import com.app.catalog.R

@BindingAdapter(value = ["productImage"])
fun productImage(view: ImageView, imageUrl: String?){

    if(!TextUtils.isEmpty(imageUrl)) {
        view.load(BuildConfig.BASE_URL+imageUrl?.replace("/","")) {
            crossfade(true)
            placeholder(R.drawable.place_holder)
            error(R.drawable.place_holder)
            memoryCachePolicy(CachePolicy.DISABLED)
        }
    }
}