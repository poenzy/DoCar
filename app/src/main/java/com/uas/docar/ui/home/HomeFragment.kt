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
        setupRecommendedRecyclerView()
        setupSpesialForYouRecyclerView()
        setupServiceButtons()
    }

    private fun setupRecommendedRecyclerView() {
        // DATA DUMMY: Nama-nama produk ini yang akan dianalisis oleh ProductAdapter
        val productList = listOf(
            // Product(id, name, price: Int, imageUrl, description, isAvailable: Boolean)
            Product(1, "Bakso Bakar Goreng", 15000, "", "Bakso pedas mantap", true), // Akan dipetakan ke R.drawable.bakso
            Product(2, "Mie Ayam Uenak", 20000, "", "Mie ayam enak dan porsi besar", true),       // Akan dipetakan ke R.drawable.mie_ayam
            Product(3, "Kari Seafood Seger", 35000, "", "Kari dengan aneka seafood segar", true),         // Akan dipetakan ke R.drawable.kari
            Product(4, "Nasi Goreng Cihuy", 18000, "", "Nasi goreng spesial pedas", true)         // Akan dipetakan ke R.drawable.nasi_goreng
        )


        // KONEKSI: ProductAdapter menerima list data ini
        val adapter = ProductAdapter(productList) { product ->
            navigateToDetailPesanan(product.id)
        }

        binding.rvRecomended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecomended.adapter = adapter
    }
    private fun setupSpesialForYouRecyclerView() {
        val productList = listOf(
            // Product(id, name, price: Int, imageUrl, description, isAvailable: Boolean)

            Product(1, "Mie Ayam Pakpri", 20000, "", "Mie ayam enak dan porsi besar", true),       // Akan dipetakan ke R.drawable.mie_ayam
            Product(2, "Oseng Seafood Mantab", 35000, "", "Kari dengan aneka seafood segar", true),         // Akan dipetakan ke R.drawable.kari
            Product(3, "Bakso Pojok Sleko", 15000, "", "Bakso pedas mantap", true), // Akan dipetakan ke R.drawable.bakso
            Product(4, "Nasi Mawut Prapatan", 18000, "", "Nasi goreng spesial pedas", true)         // Akan dipetakan ke R.drawable.nasi_goreng
        )


        // KONEKSI: ProductAdapter menerima list data ini
        val adapter = ProductAdapter(productList) { product ->
            navigateToDetailPesanan(product.id)
        }

        binding.rvSpecial.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSpecial.adapter = adapter
    }
    private fun navigateToDetailPesanan(productId: Int) {
        val intent = Intent(requireContext(), DetailPesananActivity::class.java).apply {
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }

    private fun setupServiceButtons() {
        binding.btnRide.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
        binding.btnFood.setOnClickListener {
            val intent = Intent(requireContext(), SearchFoodActivity::class.java)
            startActivity(intent)
        }
        binding.etSearch.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}