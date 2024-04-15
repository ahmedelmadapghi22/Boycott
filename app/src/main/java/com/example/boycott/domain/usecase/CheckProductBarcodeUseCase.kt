package com.example.boycott.domain.usecase

import com.example.boycott.core.ui.fragment.scanner.ProductState
import com.example.boycott.util.BarcodeConstants
import javax.inject.Inject

class CheckProductBarcodeUseCase @Inject constructor() {
    operator fun invoke(barcode: String): ProductState {
        if (barcode.isEmpty() || barcode.isBlank()) {
            return ProductState.EMPTY
        } else {
            return when (barcode.subSequence(0, 3)) {
                BarcodeConstants.CODE_1 -> ProductState.YES
                BarcodeConstants.CODE_2 -> ProductState.YES
                BarcodeConstants.CODE_3 -> ProductState.YES
                BarcodeConstants.CODE_4 -> ProductState.YES
                BarcodeConstants.CODE_5 -> ProductState.YES
                BarcodeConstants.CODE_6 -> ProductState.YES
                BarcodeConstants.CODE_7 -> ProductState.YES
                BarcodeConstants.CODE_8 -> ProductState.YES
                BarcodeConstants.CODE_9 -> ProductState.YES
                BarcodeConstants.CODE_10 -> ProductState.YES
                BarcodeConstants.CODE_11 -> ProductState.YES
                BarcodeConstants.CODE_12 -> ProductState.YES
                else -> ProductState.NO
            }
        }
    }
}