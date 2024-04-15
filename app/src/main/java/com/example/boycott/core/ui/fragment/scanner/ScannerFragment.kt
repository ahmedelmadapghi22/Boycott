package com.example.boycott.core.ui.fragment.scanner

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.boycott.R
import com.example.boycott.core.ui.fragment.ViewBindingFragment
import com.example.boycott.databinding.FragmentScannerBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScannerFragment :
    ViewBindingFragment<FragmentScannerBinding>(FragmentScannerBinding::inflate) {

    private val viewModel: ScannerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding { binding ->

            binding.btnSearch.setOnClickListener {
                val barcode = binding.edBarcode.text.toString()
                viewModel.checkBarcode(barcode)
                it.hideKeyboard()
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel._productBarcodeState.collect { productState ->
                    when (productState) {
                        ProductState.YES -> {
                            setInBoycott()

                        }

                        ProductState.NO -> {
                            setOutBoycott()

                        }

                        ProductState.EMPTY -> {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.unknown_barcode),
                                Toast.LENGTH_SHORT
                            ).show()
                            requireBinding().tvBoycott.text = getString(R.string.unknown_barcode)

                        }

                    }
                }
            }
        }
    }


    private fun setInBoycott() {
        requireBinding().tvBoycott.apply {
            text = getString(R.string.in_boycott)
            setTextColor(Color.RED)
        }
    }

    private fun setOutBoycott() {
        requireBinding().tvBoycott.apply {
            text = getString(R.string.out_boycott)
            setTextColor(Color.GREEN)
        }
    }

    override fun onResume() {
        super.onResume()

        initScanner()
        requireBinding().barcodeView.resume()

    }

    override fun onPause() {
        super.onPause()
        requireBinding().barcodeView.pause()

    }

    private fun initScanner() {
        val newDecoderFactory = DefaultDecoderFactory(
            listOf(
                BarcodeFormat.QR_CODE,
                BarcodeFormat.CODE_39,
                BarcodeFormat.CODE_128,
                BarcodeFormat.UPC_A,
                BarcodeFormat.UPC_E,
                BarcodeFormat.EAN_13,
                BarcodeFormat.EAN_8,
                BarcodeFormat.ITF,
                BarcodeFormat.CODABAR,
                BarcodeFormat.CODE_93,
                // Add more barcode formats if needed
            )
        )
        requireBinding().barcodeView.apply {
            decoderFactory = newDecoderFactory
            setStatusText(null)
            viewFinder.setMaskColor(Color.parseColor("#FFA500"))
            decodeContinuous { result ->
                requireBinding().tvBoycott.text = result.text
                viewModel.checkBarcode(result.text)
            }


        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}