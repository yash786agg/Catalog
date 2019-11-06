package com.app.catalog.commons.extensions

import com.app.catalog.domain.entities.Products

interface ProductItem {

    fun onProductItemClickListener(products: Products?)
}