package com.example.boycott.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boycott.core.ui.adapter.viewholder.ProductViewHolder
import com.example.boycott.core.ui.adapter.viewholder.listener.SetOnClickProductsType
import com.example.boycott.databinding.ItemProductsTypeBinding

class ProductAdapter<T>(private val setOnClickProductsType: SetOnClickProductsType?) :
    RecyclerView.Adapter<ProductViewHolder<T>>() {
    private var productsTypesList = listOf<T>()
    fun injectList(productsTypesList: List<T>) {
        this.productsTypesList = productsTypesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder<T> {
        return ProductViewHolder(
            ItemProductsTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productsTypesList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder<T>, position: Int) {
        holder.bind(productsTypesList[position], setOnClickProductsType)
    }
}