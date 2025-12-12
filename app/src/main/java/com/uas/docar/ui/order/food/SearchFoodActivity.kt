package com.uas.docar.ui.order.food

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// Import Layout Manager yang berbeda
import androidx.recyclerview.widget.GridLayoutManager
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
        // Tombol Kembali: Menggunakan dispatcher standar Android
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // INTERAKSI KEYBOARD
        binding.etSearchInput.setOnEditorActionListener { _, actionId, _ ->
            // mengecek apakah tombol yang ditekan adalah 'Action Search'
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearchInput.text.toString()

                // Validasi input tidak boleh kosong
                if (query.isNotEmpty()) {
                    Toast.makeText(this, "Mencari: $query", Toast.LENGTH_SHORT).show()
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
            Product(9, "Kari Ayam Pedas", 30000, "", "Kari ayam dengan bumbu kuat", true),
            Product(10, "Mie Aceh Spesial", 22000, "", "Mie tebal dengan bumbu Aceh", true),
        )

        val adapter = ProductAdapter(searchResults) { product ->
            navigateToDetailPesanan(product.id)
        }

        //GRID LAYOUT
        // menggunakan spanCount = 2 tampilan akan dibagi menjadi 2 kolom (Kiri dan Kanan).
        val spanCount = 2
        binding.rvSearchResults.layoutManager = GridLayoutManager(this, spanCount)

        binding.rvSearchResults.adapter = adapter
    }

    // Navigasi ke Detail
    private fun navigateToDetailPesanan(productId: Int) {
        val intent = Intent(this, DetailPesananActivity::class.java).apply {
            putExtra("PRODUCT_ID", productId)
        }
        startActivity(intent)
    }
}