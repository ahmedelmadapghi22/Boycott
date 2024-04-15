package com.example.boycott.core.ui.uistate

import com.example.boycott.domain.model.BoycottProduct

sealed class BoycottProductsUIState() {
    data class Loading(var isLoading: Boolean) : BoycottProductsUIState()
    data class Success(var data: List<BoycottProduct>) : BoycottProductsUIState()
    data class Error(var err: String) : BoycottProductsUIState()
}
