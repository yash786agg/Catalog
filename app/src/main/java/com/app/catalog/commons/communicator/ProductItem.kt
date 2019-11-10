package com.app.catalog.commons.communicator

import com.app.catalog.domain.entities.Products

interface ProductItem {
    fun onProductItemClickListener(products: Products?)
}