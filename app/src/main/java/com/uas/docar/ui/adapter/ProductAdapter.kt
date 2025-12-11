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
                // 1. PALING SPESIFIK: Menggunakan operator 'DAN' untuk membedakan jenis Ayam
                // 'Ayam Bakar'
                normalizedName.contains("ayam") && normalizedName.contains("bakar") -> R.drawable.ayam_bakar

                // 'Ayam Krispi' / 'Ayam Goreng Tepung'
                normalizedName.contains("ayam") && normalizedName.contains("krispi") -> R.drawable.ayam_goreng_tepung

                // 2. SPESIFIK: Kondisi unik 'Pecel'
                normalizedName.contains("pecel") -> R.drawable.nasi_pecel

                // 3. AGAK SPESIFIK: Urutan penting karena konflik dengan kata 'goreng'
                // 'Mie Goreng' (harus di atas Nasi Goreng/yang mengandung 'goreng')
                normalizedName.contains("mie") && normalizedName.contains("goreng") -> R.drawable.mie_goreng

                // 'Bakso Bakar Goreng' (harus di atas yang mengandung 'goreng' saja)
                normalizedName.contains("bakso") -> R.drawable.bakso

                // 4. LEBIH UMUM: Hanya menggunakan satu kata utama
                // 'Mie Ayam'
                normalizedName.contains("mie") -> R.drawable.mie_ayam

                // 'Nasi Goreng' / Nasi lainnya (diutamakan 'goreng' di sini karena sudah lolos dari 'mie goreng')
                normalizedName.contains("nasi") || normalizedName.contains("goreng") -> R.drawable.nasi_goreng

                // 5. PALING UMUM / DEFAULT
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