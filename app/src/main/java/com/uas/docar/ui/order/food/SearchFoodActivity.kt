package com.uas.docar.ui.order.food

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.data.model.Product
import com.uas.docar.databinding.ActivitySearchFoodBinding
import com.uas.docar.ui.adapter.ProductAdapter

class SearchFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupRecyclerView()
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.etSearchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearchInput.text.toString()
                if (query.isNotEmpty()) {
                    Toast.makeText(this, "Mencari: $query", Toast.LENGTH_SHORT).show()
                    // TODO: Implementasi logika filter/panggilan API di sini
                }
                true
            } else {
                false
            }
        }
    }

    private fun setupRecyclerView() {
        val searchResults = listOf(
            Product(5, "Ayam Geprek Sai Amin Yapper", 12000, "", "Ayam geprek crispy level 1-5", true),
            Product(6, "Nasi Goreng Pak Budi", 15000, "", "Nasi goreng kampung favorit", true),
            Product(7, "Soto Ayam Mbak Rini", 18000, "", "Soto ayam bening segar", true),
            Product(8, "Bakso Granat Pedas", 25000, "", "Bakso pedas mantap", true),
        )

        val adapter = ProductAdapter(searchResults) { product ->
            navigateToDetailPesanan(product.id)
        }

        binding.rvSearchResults.layoutManager = LinearLayoutManager(this)
        binding.rvSearchResults.adapter = adapter
    }

    private fun navigateToDetailPesanan(productId: Int) {
        val intent = Intent(this, DetailPesananActivity::class.java).apply {
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }
}