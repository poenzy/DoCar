package com.uas.docar.ui.order.ride

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.databinding.ActivitySearchInputBinding
import com.uas.docar.ui.adapter.SearchHistoryAdapter // <-- Adapter Riwayat akan dibuat

class SearchInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchInputBinding

    private val searchHistory = listOf(
        "Politeknik Negeri Madiun",
        "Alun-Alun Madiun",
        "Stasiun Madiun",
        "Rumah Sakit Soedono"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupHistoryRecyclerView()

        binding.etDestinationInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && binding.etDestinationInput.text.isNotEmpty()) {
                navigateToOrderConfirmation()
            }
        }
    }

    private fun setupHistoryRecyclerView() {
        val adapter = SearchHistoryAdapter(searchHistory) { location ->
            binding.etDestinationInput.setText(location)
            navigateToOrderConfirmation()
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

    private fun navigateToOrderConfirmation() {
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        startActivity(intent)
    }
}