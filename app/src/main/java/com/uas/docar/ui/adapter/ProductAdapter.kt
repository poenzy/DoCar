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

        // *** FUNGSI BARU: PEMETAAN GAMBAR DUMMY BERDASARKAN NAMA PRODUK ***
        private fun getProductImageResource(productName: String): Int {
            // Gunakan lowercase() untuk pencocokan yang tidak sensitif huruf besar/kecil
            val normalizedName = productName.lowercase()

            // Mapping berdasarkan kata kunci yang mungkin ada di nama produk dummy Anda
            return when {
                // Asumsi: Produk mengandung kata 'Bakso'
                normalizedName.contains("bakso") -> R.drawable.ic_location_pin_red // Contoh drawable

                // Asumsi: Produk mengandung kata 'Mie'
                normalizedName.contains("mie") -> R.drawable.ic_star_filled // Contoh drawable

                // Asumsi: Produk mengandung kata 'Nasi' atau 'Goreng'
                normalizedName.contains("nasi") || normalizedName.contains("goreng") -> R.drawable.ic_history // Contoh drawable

                // FALLBACK: Gambar default jika tidak cocok
                else -> R.drawable.ic_delivery_box // Drawable default yang kita pakai sebelumnya
            }
        }

        fun bind(product: Product) {
            binding.tvProductName.text = product.name
            binding.tvProductPrice.text = Formatter.toRupiah(product.price)

            // V PERBAIKAN: Panggil fungsi pemetaan V
            val drawableId = getProductImageResource(product.name)
            binding.ivProductImage.setImageResource(drawableId)

            binding.root.setOnClickListener {
                onItemClick(product)
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