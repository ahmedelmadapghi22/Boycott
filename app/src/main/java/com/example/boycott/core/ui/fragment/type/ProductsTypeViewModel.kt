package com.example.boycott.core.ui.fragment.type

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boycott.core.ui.uistate.ProductsTypeUIState
import com.example.boycott.data.Result
import com.example.boycott.domain.usecase.GetProductsTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsTypeViewModel @Inject constructor(private val getProductsTypeUseCase: GetProductsTypeUseCase) :
    ViewModel() {
    private val _productsTypeFlow: MutableStateFlow<ProductsTypeUIState> =
        MutableStateFlow(ProductsTypeUIState.Loading(true))
    val productsTypeFlow: StateFlow<ProductsTypeUIState> = _productsTypeFlow.asStateFlow()

    init {
        viewModelScope.launch {
        }
    }

     suspend fun getProductsType() {
        when (val result = getProductsTypeUseCase()) {
            is Result.Success -> {
                val productsTypes = result.data
                Log.d("????????", "getProductsType:${productsTypes}")
                _productsTypeFlow.emit(ProductsTypeUIState.Success(productsTypes))
                _productsTypeFlow.emit(ProductsTypeUIState.Loading(false))

            }

            is Result.Error -> {
                _productsTypeFlow.emit(ProductsTypeUIState.Error(result.errMsg.message.toString()))
                _productsTypeFlow.emit(ProductsTypeUIState.Loading(false))

            }

        }

    }


}