package com.uas.docar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.R // Import untuk mengakses sumber daya drawable
import com.uas.docar.databinding.ItemFoodCardBinding
import com.uas.docar.data.model.Product
import com.uas.docar.utils.Formatter

//memperpendek penulis onclik
typealias OnItemClickListener = (Product) -> Unit

// jembatan Data (List Product) ke Tampilan List (RecyclerView).
class ProductAdapter(
    private val productList: List<Product>, // Data mentah
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ngeset layout item_food_card.xml agar bisa diisi data
    inner class ProductViewHolder(private val binding: ItemFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

//      LOGIKA GAMBAR DUMMY fungsi ini otomatis milih gambar dari folder drawable berdasarkan Nama Makanannya.
        private fun getProductImageResource(productName: String): Int {
            // Ubah ke huruf kecil
            val normalizedName = productName.lowercase()

            return when {
                normalizedName.contains("ayam") && normalizedName.contains("bakar") -> R.drawable.ayam_bakar

                normalizedName.contains("ayam") && normalizedName.contains("krispi") -> R.drawable.ayam_goreng_tepung

                normalizedName.contains("pecel") -> R.drawable.nasi_pecel

                normalizedName.contains("mie") && normalizedName.contains("goreng") -> R.drawable.mie_goreng

                normalizedName.contains("bakso") -> R.drawable.bakso

                normalizedName.contains("mie") -> R.drawable.mie_ayam

                normalizedName.contains("nasi") || normalizedName.contains("goreng") -> R.drawable.nasi_goreng

                //Kalau tidak ada yang cocok, pakai gambar default (kari)
                else -> R.drawable.kari
            }
        }

        // Fungsi BIND proses "menempelkan" data ke layar
        fun bind(product: Product) {
            //Set Nama Makanan
            binding.tvProductName.text = product.name

            //Set Harga
            binding.tvProductPrice.text = Formatter.toRupiah(product.price)

            //Set Gambar
            val drawableId = getProductImageResource(product.name)
            binding.ivProductImage.setImageResource(drawableId)

            binding.root.setOnClickListener {
                onItemClick(product) // Kirim data produk ke Activity/Fragment
            }
        }
    }

    // Membuat tampilan kosonguntuk setiap item list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemFoodCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    // Mengisi tampilan kosong dengan data sesuai urutann
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    // berapa banyak data yang harus ditampilkan
    override fun getItemCount(): Int = productList.size
}