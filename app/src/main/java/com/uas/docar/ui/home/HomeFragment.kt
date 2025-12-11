package com.uas.docar.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
// IMPORTS WAJIB
import com.uas.docar.data.model.Product // Model data untuk produk
import com.uas.docar.databinding.FragmentHomeBinding // View Binding untuk Fragment
import com.uas.docar.ui.adapter.ProductAdapter // Adapter untuk rekomendasi
import com.uas.docar.ui.order.food.DetailPesananActivity // Target klik item
import com.uas.docar.ui.order.food.SearchFoodActivity // Target tombol 'Makanan'
import com.uas.docar.ui.order.ride.SearchInputActivity // Target tombol 'Antar Jemput'

class HomeFragment : Fragment() {

    // Deklarasi View Binding (Fragment Standard)
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup RecyclerView Rekomendasi
        setupRecommendedRecyclerView()

        // 2. Setup Listener Tombol Layanan
        setupServiceButtons()
    }

    private fun setupRecommendedRecyclerView() {
        // DATA DUMMY (Akan menggunakan ProductAdapter yang sudah diperbaiki untuk memetakan gambar)
        val productList = listOf(
            // Product(id, name, price: Int, imageUrl, description, isAvailable: Boolean)
            Product(1, "Bakso Bakar Goreng", 15000, "", "Bakso pedas mantap", true),
            Product(2, "Mie Ayam Uenak", 20000, "", "Mie ayam enak dan porsi besar", true),
            Product(3, "Kari Seafood", 35000, "", "Kari dengan aneka seafood segar", true),
            Product(4, "Nasi Goreng Cihuy", 18000, "", "Nasi goreng spesial pedas", true)
        )

        // Adapter menerima data dan lambda function untuk klik item
        val adapter = ProductAdapter(productList) { product ->
            // Aksi klik: Pindah ke Detail Pesanan Makanan
            navigateToDetailPesanan(product.id)
        }

        // Setup RecyclerView (Horizontal)
        binding.rvRecomended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecomended.adapter = adapter
    }

    private fun navigateToDetailPesanan(productId: Int) {
        val intent = Intent(requireContext(), DetailPesananActivity::class.java).apply {
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }

    private fun setupServiceButtons() {
        // 1. Tombol Antar Jemput (Ride)
        binding.btnRide.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }

        // 2. Tombol Makanan (Food)
        binding.btnFood.setOnClickListener {
            val intent = Intent(requireContext(), SearchFoodActivity::class.java)
            startActivity(intent)
        }

        // 3. Search Bar (mengarah ke Search Input Ride, asumsi)
        binding.etSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        // Wajib: Menghapus referensi binding untuk menghindari kebocoran memori (memory leak)
        super.onDestroyView()
        _binding = null
    }
}