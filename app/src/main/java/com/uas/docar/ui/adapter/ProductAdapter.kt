package com.uas.docar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.R // Import untuk mengakses sumber daya drawable
import com.uas.docar.databinding.ItemFoodCardBinding
import com.uas.docar.data.model.Product
import com.uas.docar.utils.Formatter

// alis untuk memperpendek penulis tipe function untuk klik
typealias OnItemClickListener = (Product) -> Unit

// Adapter sebagai JEMBATAN Data (List Product) ke Tampilan List (RecyclerView).
class ProductAdapter(
    private val productList: List<Product>, // Data mentah yang akan ditampilkan
    private val onItemClick: OnItemClickListener // Fungsi yang jalan kalau item diklik
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // "memegang" layout item_food_card.xml supaya bisa diisi data
    inner class ProductViewHolder(private val binding: ItemFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

//      LOGIKA GAMBAR DUMMY fungsi ini otomatis milih gambar dari folder drawable berdasarkan Nama Makanannya.
        private fun getProductImageResource(productName: String): Int {
            // Ubah ke huruf kecil
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
                // Fallback: Kalau tidak ada yang cocok, pakai gambar default (kari)
                else -> R.drawable.kari
            }
        }

        // Fungsi BIND: proses "menempelkan" data ke layar
        fun bind(product: Product) {
            // 1. Set Nama Makanan
            binding.tvProductName.text = product.name

            // 2. Set Harga (sudah diformat jadi Rupiah, misal: Rp 15.000)
            binding.tvProductPrice.text = Formatter.toRupiah(product.price)

            // 3. Set Gambar (Panggil logika dummy di atas)
            val drawableId = getProductImageResource(product.name)
            binding.ivProductImage.setImageResource(drawableId)

            // 4. Pasang (Listener) kalau kartu ini diklik
            binding.root.setOnClickListener {
                onItemClick(product) // Kirim data produk ke Activity/Fragment
            }
        }
    }

    // Membuat tampilan kosong (inflate layout) untuk setiap item list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemFoodCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    // Mengisi tampilan kosong tadi dengan data sesuai posisi urutannya
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    // Memberi tahu RecyclerView ada berapa banyak data yang harus ditampilkan
    override fun getItemCount(): Int = productList.size
}