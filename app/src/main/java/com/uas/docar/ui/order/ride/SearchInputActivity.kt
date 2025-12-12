package com.uas.docar.ui.order.ride

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.databinding.ActivitySearchInputBinding
import com.uas.docar.ui.adapter.SearchHistoryAdapter

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

        //Jika mengetik lalu menekan area lain dianggap sudah selesai dan langsung lanjut."
        binding.etDestinationInput.setOnFocusChangeListener { _, hasFocus ->
            // Jika menekan area lain dan teks tdak kosong
            if (!hasFocus && binding.etDestinationInput.text.isNotEmpty()) {
                navigateToOrderConfirmation()
            }
        }
    }

    private fun setupHistoryRecyclerView() {
        val adapter = SearchHistoryAdapter(searchHistory) { location ->

            // jika user mengklik salah satu riwayat tulis lokasi ke kolom input
            binding.etDestinationInput.setText(location)

            //langsung pindah ke halaman konfirmasi
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