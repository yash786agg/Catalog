package com.app.catalog.domain.entities

object CategoryGenerator {

    fun getSuccessCategoryData() : CategoryApiResponse {

        return CategoryApiResponse(
            "Food",
            listOf(
                (Products("1", "Bread", "/Bread.jpg",
                    ProductPrice("0.81", "EUR")
                )),
                (Products("2", "Sandwich", "/Sandwich.jpg",
                    ProductPrice("2.01", "EUR")
                ))
            ))
    }

    fun getEmptyCategoryData() : CategoryApiResponse {
        return CategoryApiResponse(null, listOf())
    }
}