package com.uas.docar.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
// IMPORTS WAJIB
import com.uas.docar.data.model.Product // Model data untuk produk (Struktur data: id, nama, harga, dll.)
import com.uas.docar.databinding.FragmentHomeBinding // View Binding untuk Fragment (Menggantikan findViewById)
import com.uas.docar.ui.adapter.ProductAdapter // Adapter untuk menampilkan daftar rekomendasi produk
import com.uas.docar.ui.order.food.DetailPesananActivity // Activity tujuan saat item makanan diklik
import com.uas.docar.ui.order.food.SearchFoodActivity // Activity tujuan tombol layanan 'Makanan'
import com.uas.docar.ui.order.ride.SearchInputActivity // Activity tujuan tombol layanan 'Antar Jemput' & kotak Search

class HomeFragment : Fragment() {

    // Deklarasi View Binding (Fragment Standard):
    // '_binding' adalah properti nullable untuk menampung instance binding.
    private var _binding: FragmentHomeBinding? = null
    // 'binding' adalah properti getter non-nullable yang aman digunakan setelah onCreateView.
    private val binding get() = _binding!!

    // 1. onCreateView: Dipanggil saat Fragment harus membuat hierarki View-nya.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi binding menggunakan Fragment binding class yang dihasilkan.
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Mengembalikan root view dari binding.
        return binding.root
    }

    // 2. onViewCreated: Dipanggil setelah view Fragment berhasil dibuat. Tempat yang ideal untuk inisialisasi View.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Memisahkan inisialisasi ke fungsi yang lebih spesifik untuk kode yang lebih bersih.
        setupRecommendedRecyclerView()
        setupSpesialForYouRecyclerView()
        setupServiceButtons()
    }

    /**
     * @function setupRecommendedRecyclerView
     * @description Menyiapkan RecyclerView untuk daftar rekomendasi produk teratas.
     */
    private fun setupRecommendedRecyclerView() {
        // DATA DUMMY: Membuat daftar produk sementara.
        // Di aplikasi nyata, data ini akan diambil dari server (API) atau database lokal.
        val productList = listOf(
            // Product(id, name, price, imageUrl, description, isAvailable)
            Product(1, "Bakso Bakar Goreng", 15000, "", "Bakso pedas mantap", true),
            Product(2, "Mie Ayam Uenak", 20000, "", "Mie ayam enak dan porsi besar", true),
            Product(3, "Kari Seafood Seger", 35000, "", "Kari dengan aneka seafood segar", true),
            Product(4, "Nasi Goreng Cihuy", 18000, "", "Nasi goreng spesial pedas", true)
        )

        // KONEKSI Adapter: Membuat ProductAdapter, meneruskan list, dan mendefinisikan
        // aksi ketika salah satu item diklik (memanggil navigateToDetailPesanan).
        val adapter = ProductAdapter(productList) { product ->
            navigateToDetailPesanan(product.id)
        }

        // Mengatur LayoutManager menjadi horizontal, agar produk ditampilkan menyamping.
        binding.rvRecomended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        // Menetapkan adapter ke RecyclerView.
        binding.rvRecomended.adapter = adapter
    }

    /**
     * @function setupSpesialForYouRecyclerView
     * @description Menyiapkan RecyclerView kedua (Spesial untuk Anda).
     * Logika sama dengan rekomendasi, hanya datanya yang berbeda.
     */
    private fun setupSpesialForYouRecyclerView() {
        // DATA DUMMY untuk bagian 'Spesial untuk Anda'
        val productList = listOf(
            Product(1, "Nasi Pecel", 20000, "", "Nasi Pecel Legendaris", true),
            Product(2, "Ayam Krispi", 35000, "", "Ayam Krispi kekinian", true),
            Product(3, "Ayam Bakar", 15000, "", "Ayam Bakar viral", true),
            Product(4, "Mie Goreng", 18000, "", "Mie Goreng spesial mbak Lina", true)
        )

        val adapter = ProductAdapter(productList) { product ->
            navigateToDetailPesanan(product.id)
        }

        binding.rvSpecial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSpecial.adapter = adapter
    }

    /**
     * @function navigateToDetailPesanan
     * @description Fungsi untuk pindah ke halaman detail pesanan makanan.
     * @param productId ID produk yang dipilih.
     */
    private fun navigateToDetailPesanan(productId: Int) {
        // Membuat Intent ke DetailPesananActivity.
        val intent = Intent(requireContext(), DetailPesananActivity::class.java).apply {
            // Meneruskan ID produk yang diklik sebagai ekstra data.
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }

    /**
     * @function setupServiceButtons
     * @description Menetapkan listener klik untuk tombol layanan utama (Antar Jemput, Makanan)
     * dan kotak pencarian di bagian atas.
     */
    private fun setupServiceButtons() {
        // 1. Tombol 'Antar Jemput' (Ride/Car)
        binding.btnRide.setOnClickListener {
            // Navigasi ke Activity input pencarian 'Antar Jemput'.
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
        // 2. Tombol 'Makanan' (Food)
        binding.btnFood.setOnClickListener {
            // Navigasi ke Activity pencarian 'Makanan'.
            val intent = Intent(requireContext(), SearchFoodActivity::class.java)
            startActivity(intent)
        }
        // 3. Kotak Pencarian Utama
        binding.etSearch.setOnClickListener {
            // Saat kotak pencarian diklik, kita arahkan juga ke halaman input 'Antar Jemput' (atau bisa disesuaikan ke halaman pencarian umum).
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
    }

    // 3. onDestroyView: Dipanggil saat view Fragment dihancurkan.
    // Ini PENTING untuk Fragments yang menggunakan View Binding!
    override fun onDestroyView() {
        super.onDestroyView()
        // Mengatur _binding menjadi null untuk menghindari kebocoran memori (memory leak)
        // karena view binding menahan referensi ke hierarki view.
        _binding = null
    }
}