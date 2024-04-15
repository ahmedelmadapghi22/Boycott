package com.example.boycott.core.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.boycott.core.ui.adapter.viewholder.listener.SetOnClickProductsType
import com.example.boycott.databinding.ItemProductsTypeBinding
import com.example.boycott.domain.model.BoycottProduct
import com.example.boycott.domain.model.ProductType

class ProductViewHolder<T>(private val itemProductsTypeBinding: ItemProductsTypeBinding) :
    RecyclerView.ViewHolder(itemProductsTypeBinding.root) {

    fun bind(productType: T, setOnClickProductsType: SetOnClickProductsType?) {
        when(productType){
            is ProductType->{
                itemProductsTypeBinding.tvProductsType.text = productType.type
                itemProductsTypeBinding.tvProductsTypeNum.text = productType.id.toString()
                itemProductsTypeBinding.tvProductsType.setOnClickListener {
                    setOnClickProductsType?.onClick(productType.id)
                }

            }
            is BoycottProduct ->{
                itemProductsTypeBinding.tvProductsType.text = productType.name
                itemProductsTypeBinding.tvProductsTypeNum.text = (adapterPosition+1).toString()

            }
        }


    }

}