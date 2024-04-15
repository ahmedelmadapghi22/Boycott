package com.example.boycott.core.ui.fragment.boycott

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boycott.core.ui.adapter.ProductAdapter
import com.example.boycott.core.ui.fragment.ViewBindingFragment
import com.example.boycott.core.ui.uistate.BoycottProductsUIState
import com.example.boycott.core.ui.uistate.ProductsTypeUIState
import com.example.boycott.databinding.FragmentBoycottProductsBinding
import com.example.boycott.domain.model.BoycottProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class BoycottProductsFragment() :
    ViewBindingFragment<FragmentBoycottProductsBinding>(FragmentBoycottProductsBinding::inflate) {
    private val viewModel: BoycottProductsViewModel by viewModels()
    private lateinit var boycottProductAdapter: ProductAdapter<BoycottProduct>
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boycottProductAdapter = ProductAdapter(null)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding { binding ->
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.categoryState.collect{categoryID->
                        viewModel.getProductsType(categoryID)
                    }
                }
            }

            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.productsTypeState.collect { state ->
                        when (state) {
                            is BoycottProductsUIState.Loading -> {
                                if (state.isLoading) {
                                    Log.d("????????", "Loading")
                                    binding.loadingBar.visibility = View.VISIBLE
                                } else {
                                    Log.d("????????", "Finish Loading")
                                    binding.loadingBar.visibility = View.GONE


                                }
                            }

                            is BoycottProductsUIState.Success -> {
                                boycottProductAdapter.injectList(state.data)

                                binding.rvBoycottProducts.apply {
                                    layoutManager = linearLayoutManager
                                    adapter = boycottProductAdapter

                                }
                            }

                            is BoycottProductsUIState.Error -> {
                                Toast.makeText(requireContext(), state.err, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            val category = BoycottProductsFragmentArgs.fromBundle(it).categoryID
            viewModel.updateCategoryID(category)
        }
    }
}