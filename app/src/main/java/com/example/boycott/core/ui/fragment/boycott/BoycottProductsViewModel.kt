package com.example.boycott.core.ui.fragment.boycott

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.boycott.core.ui.uistate.BoycottProductsUIState
import com.example.boycott.data.Result
import com.example.boycott.domain.usecase.GetBoycottProductsByCategoryIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class BoycottProductsViewModel @Inject constructor(private val getBoycottProductsByCategoryIDUseCase: GetBoycottProductsByCategoryIDUseCase) :
    ViewModel() {
    private val _boycottProductsState: MutableStateFlow<BoycottProductsUIState> =
        MutableStateFlow(BoycottProductsUIState.Loading(true))
    val productsTypeState: StateFlow<BoycottProductsUIState> = _boycottProductsState.asStateFlow()
     val categoryState: MutableStateFlow<Int> = MutableStateFlow(-1)

    fun updateCategoryID(categoryID: Int)  {
        categoryState.value = categoryID
    }

    suspend fun getProductsType(id: Int) {
        when (val result = getBoycottProductsByCategoryIDUseCase(id)) {
            is Result.Success -> {
                val productsTypes = result.data
                Log.d("????????", "getProductsType:${productsTypes}")
                _boycottProductsState.emit(BoycottProductsUIState.Success(productsTypes))
                _boycottProductsState.emit(BoycottProductsUIState.Loading(false))

            }

            is Result.Error -> {
                _boycottProductsState.emit(BoycottProductsUIState.Error(result.errMsg.message.toString()))
                _boycottProductsState.emit(BoycottProductsUIState.Loading(false))

            }

        }

    }
}