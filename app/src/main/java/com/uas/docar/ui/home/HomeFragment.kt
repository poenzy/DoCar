package com.uas.docar.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.data.model.Product
import com.uas.docar.databinding.FragmentHomeBinding
import com.uas.docar.ui.adapter.ProductAdapter
import com.uas.docar.ui.order.food.DetailPesananActivity
import com.uas.docar.ui.order.food.SearchFoodActivity
import com.uas.docar.ui.order.ride.SearchInputActivity

class HomeFragment : Fragment() {

    // _binding (Mutable/Bisa null) untuk disimpan, binding (Immutable) untuk dipakai
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Memasak Layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Logika UI
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Memanggil fungsi-fungsi modular
        setupRecommendedRecyclerView()
        setupSpesialForYouRecyclerView()
        setupServiceButtons()
    }

    // LIST REKOMENDASI
    private fun setupRecommendedRecyclerView() {
        val productList = listOf(
            Product(1, "Bakso Bakar Goreng", 15000, "", "Bakso pedas mantap", true),
            Product(2, "Mie Ayam Uenak", 20000, "", "Mie ayam enak dan porsi besar", true),
            Product(3, "Kari Seafood Seger", 35000, "", "Kari dengan aneka seafood segar", true),
            Product(4, "Nasi Goreng Cihuy", 18000, "", "Nasi goreng spesial pedas", true)
        )

        val adapter = ProductAdapter(productList) { product ->
            //navigasi bila diklik
            navigateToDetailPesanan(product.id)
        }

        // LayoutManager Horizontal: Agar bisa digeser ke samping
        binding.rvRecomended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecomended.adapter = adapter
    }

    // LIST SPESIAL
    private fun setupSpesialForYouRecyclerView() {
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

    //NAVIGASI KE DETAIL
    private fun navigateToDetailPesanan(productId: Int) {
        val intent = Intent(requireContext(), DetailPesananActivity::class.java).apply {
            // Mengirimkan ID produk
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }

    // NAVIGASI LAYANAN UTAMA
    private fun setupServiceButtons() {
        // Tombol Ride -> Pindah ke Input Lokasi
        binding.btnRide.setOnClickListener {
            val intent = Intent(requireContext(), SearchInputActivity::class.java)
            startActivity(intent)
        }
        binding.btnFood.setOnClickListener {
            val intent = Intent(requireContext(), SearchFoodActivity::class.java)
            startActivity(intent)
        }
    }

    // Pembersihan Memori
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}