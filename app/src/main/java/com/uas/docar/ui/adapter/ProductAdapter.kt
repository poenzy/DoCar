package com.uas.docar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.R // Import untuk mengakses sumber daya drawable
import com.uas.docar.databinding.ItemFoodCardBinding
import com.uas.docar.data.model.Product
import com.uas.docar.utils.Formatter

typealias OnItemClickListener = (Product) -> Unit

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // *** FUNGSI PEMETAAN GAMBAR DUMMY BERDASARKAN NAMA PRODUK ***
        // Fungsi ini membaca String 'productName' dari data dummy di HomeFragment.kt
        private fun getProductImageResource(productName: String): Int {
            val normalizedName = productName.lowercase()

            return when {
                // 'Bakso Bakar Goreng' -> Mengandung 'bakso' -> R.drawable.bakso
                normalizedName.contains("bakso") -> R.drawable.bakso

                // 'Mie Ayam Uenak' -> Mengandung 'mie' -> R.drawable.mie_ayam
                normalizedName.contains("mie") -> R.drawable.mie_ayam

                // 'Nasi Goreng Cihuy' -> Mengandung 'nasi' atau 'goreng' -> R.drawable.nasi_goreng
                normalizedName.contains("nasi") || normalizedName.contains("goreng") -> R.drawable.nasi_goreng

                // 'Kari Seafood' -> Tidak cocok di atas -> R.drawable.kari
                else -> R.drawable.kari // Drawable default
            }
        }

        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = Formatter.toRupiah(product.price)

            // V KONEKSI DENGAN DATA: Memanggil fungsi pemetaan V
            val drawableId = getProductImageResource(product.name)
            binding.ivProductImage.setImageResource(drawableId)

            binding.root.setOnClickListener {
                onItemClick(product)
            }
        }
    }
    // ... (Sisa kode adapter tidak berubah) ...
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