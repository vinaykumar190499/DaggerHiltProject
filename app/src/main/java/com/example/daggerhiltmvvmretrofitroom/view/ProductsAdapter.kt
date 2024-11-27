package com.example.daggerhiltmvvmretrofitroom.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhiltmvvmretrofitroom.databinding.ProductsItemBinding
import com.example.daggerhiltmvvmretrofitroom.model.data.FetchProductResponseItem

class ProductsAdapter(val products:List<FetchProductResponseItem>):RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> (){
    private lateinit var binding:ProductsItemBinding
    inner class ProductsViewHolder(binding: ProductsItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(productItem :FetchProductResponseItem){
            with(binding){
                tvId.text = productItem.id
                tvName.text = productItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        binding= ProductsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(products[position])
    }
}