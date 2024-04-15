package com.example.boycott.core.ui.uistate

import com.example.boycott.domain.model.ProductType

sealed class ProductsTypeUIState() {
    data class Loading(var isLoading: Boolean) : ProductsTypeUIState()
    data class Success(var data: List<ProductType>) : ProductsTypeUIState()
    data class Error(var err: String) : ProductsTypeUIState()
}
