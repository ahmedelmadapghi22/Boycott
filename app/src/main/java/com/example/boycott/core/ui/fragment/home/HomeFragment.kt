package com.example.boycott.core.ui.fragment.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.boycott.R
import com.example.boycott.core.ui.fragment.ViewBindingFragment
import com.example.boycott.databinding.FragmentHomeBinding

class HomeFragment : ViewBindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding { binding ->
            binding.apply {
                btnProducts.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_productsTypeFragment)
                }
                btnScanner.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_scannerFragment)
                }
            }
        }
    }
}