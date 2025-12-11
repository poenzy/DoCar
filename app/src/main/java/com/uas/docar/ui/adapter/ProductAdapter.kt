package com.uas.docar.ui.adapter// Sesuaikan dengan package Anda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.R // *** IMPOR KRITIS UNTUK MENGAKSES SUMBER DAYA (DRAWABLE) ***
import com.uas.docar.databinding.ItemFoodCardBinding // Asumsi layout item makanan Anda
import com.uas.docar.data.model.Product
import com.uas.docar.utils.Formatter // Utilitas untuk memformat Rupiah

// Definisikan tipe fungsi untuk onClickListener
typealias OnItemClickListener = (Product) -> Unit

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: OnItemClickListener // Fungsi yang akan dijalankan saat item diklik
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = Formatter.toRupiah(product.price)

            // V IMPLEMENTASI GAMBAR PLACEHOLDER V
            // Karena menggunakan data dummy, kita tampilkan drawable lokal.
            // ic_delivery_box adalah contoh placeholder yang tersedia di proyek Anda.
            binding.ivProductImage.setImageResource(R.drawable.ic_delivery_box)

            // Listener untuk item
            binding.root.setOnClickListener {
                onItemClick(product) // Memicu fungsi yang diberikan oleh HomeFragment/SearchActivity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemFoodCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size
}