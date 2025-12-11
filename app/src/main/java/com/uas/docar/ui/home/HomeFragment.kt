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

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecommendedRecyclerView()
        setupServiceButtons()
    }

    private fun setupRecommendedRecyclerView() {
        val productList = listOf(
            Product(1, "Bakso Bakar Goreng", 15000, "", "Bakso pedas mantap", true),
            Product(2, "Mie Ayam Uenak", 20000, "", "Mie ayam enak dan porsi besar", true),
            Product(3, "Kari Seafood", 35000, "", "Kari dengan aneka seafood segar", true),
            Product(4, "Nasi Goreng Cihuy", 18000, "", "Nasi goreng spesial pedas", true)
        )

        val adapter = ProductAdapter(productList) { product ->
            navigateToDetailPesanan(product.id)
        }

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