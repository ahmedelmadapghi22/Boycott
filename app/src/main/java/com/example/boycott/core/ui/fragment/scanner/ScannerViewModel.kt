package com.example.boycott.core.ui.fragment.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boycott.domain.usecase.CheckProductBarcodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ProductState() {
    YES, NO, EMPTY
}

@HiltViewModel
class ScannerViewModel @Inject constructor(private val checkProductBarcodeUseCase: CheckProductBarcodeUseCase) :
    ViewModel() {
    private val productBarcodeState: MutableStateFlow<ProductState> =
        MutableStateFlow(ProductState.EMPTY)
    val _productBarcodeState: StateFlow<ProductState> =
        productBarcodeState.asStateFlow()

    fun checkBarcode(barcode: String) {
        viewModelScope.launch {
            productBarcodeState.emit(checkProductBarcodeUseCase(barcode))
        }

    }

}