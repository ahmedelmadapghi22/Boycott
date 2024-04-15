package com.example.boycott.core.ui.fragment.type

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boycott.R
import com.example.boycott.core.ui.adapter.ProductAdapter
import com.example.boycott.core.ui.adapter.viewholder.listener.SetOnClickProductsType
import com.example.boycott.core.ui.fragment.ViewBindingFragment
import com.example.boycott.core.ui.uistate.ProductsTypeUIState
import com.example.boycott.databinding.FragmentProductsTypeBinding
import com.example.boycott.domain.model.ProductType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsTypeFragment :
    ViewBindingFragment<FragmentProductsTypeBinding>(FragmentProductsTypeBinding::inflate),
    SetOnClickProductsType {
    private val viewModel: ProductsTypeViewModel by viewModels()
    private lateinit var productTypeAdapter: ProductAdapter<ProductType>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productTypeAdapter = ProductAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding {
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.productsTypeFlow.collect { state ->
                        when (state) {
                            is ProductsTypeUIState.Loading -> {
                                if (state.isLoading) {
                                    Log.d("????????", "Loading")
                                    it.loadingBar.visibility = View.VISIBLE
                                } else {
                                    Log.d("????????", "Finish Loading")
                                    it.loadingBar.visibility = View.GONE


                                }
                            }

                            is ProductsTypeUIState.Success -> {
                                productTypeAdapter.injectList(state.data)
                                requireBinding().apply {
                                    rvProductType.apply {
                                        layoutManager = LinearLayoutManager(
                                            requireContext(),
                                            LinearLayoutManager.VERTICAL,
                                            false
                                        )
                                        adapter = productTypeAdapter
                                    }
                                }
                            }

                            is ProductsTypeUIState.Error -> {
                                Toast.makeText(requireContext(), state.err, Toast.LENGTH_SHORT)
                                    .show()
                                Log.d("????????", "Error : ${state.err}")

                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            viewModel.getProductsType()
        }
    }
        override fun onClick(id: Int) {
        findNavController().navigate(ProductsTypeFragmentDirections.actionProductsTypeFragmentToBoycottProductsFragment(id))
        Log.d("????????", "ProductsTypeId : ${id}")
    }


}