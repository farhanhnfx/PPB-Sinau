package com.example.ppb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ppb.databinding.ItemProductBinding
import com.example.ppb.model.Product

typealias OnClickProduct = (Product) -> Unit
class ProductAdapter(private val listProduct: List<Product>, private val onClickProduct: OnClickProduct):
    RecyclerView.Adapter<ProductAdapter.ItemProductViewHolder>() {
        inner class ItemProductViewHolder(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {
            fun bind(product: Product) {
                with(binding) {
                    txtProductName.text = product.title
                    txtProductDesc.text = product.description
                    txtProductPrice.text = "$${product.price.toString()}"
                    imgThumbnail.setBackgroundResource(0)
                    Glide.with(itemView).load(product.thumbnail).into(imgThumbnail)
                }
                itemView.setOnClickListener {
                    onClickProduct(product)
                }
            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listProduct.size
    }

    override fun onBindViewHolder(holder: ItemProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }
}