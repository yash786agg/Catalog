package com.app.catalog.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.catalog.R
import com.app.catalog.databinding.ActivityProductDetailsBinding
import com.app.catalog.commons.utils.Constants.Companion.EXTRA_PRODUCT

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityProductDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)

        if(intent != null && intent.hasExtra(EXTRA_PRODUCT))
            binding.product = intent.getParcelableExtra(EXTRA_PRODUCT)
    }
}