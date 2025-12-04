package com.uas.docar.ui.order.ride

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.databinding.ActivitySearchInputBinding
import com.uas.docar.ui.adapter.SearchHistoryAdapter // <-- Adapter Riwayat akan dibuat

class SearchInputActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchInputBinding

    // Data Dummy Statis untuk Riwayat Pencarian
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

        // 1. Setup Tombol Kembali
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // 2. Setup RecyclerView Riwayat Pencarian
        setupHistoryRecyclerView()

        // 3. Setup Listener Input Tujuan (Simulasi Pilih Lokasi)
        // Ketika input tujuan mendapatkan fokus dan kemudian kehilangan fokus (diisi), anggap lokasi dipilih.
        binding.etDestinationInput.setOnFocusChangeListener { _, hasFocus ->
            // Simulasi navigasi jika field diisi
            if (!hasFocus && binding.etDestinationInput.text.isNotEmpty()) {
                navigateToOrderConfirmation()
            }
        }
    }

    private fun setupHistoryRecyclerView() {
        val adapter = SearchHistoryAdapter(searchHistory) { location ->
            // Ketika item riwayat diklik, isi field tujuan dan pindah ke konfirmasi
            binding.etDestinationInput.setText(location)
            navigateToOrderConfirmation()
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

    private fun navigateToOrderConfirmation() {
        val intent = Intent(this, OrderConfirmationActivity::class.java)
        startActivity(intent)
        // Tidak di-finish() agar user bisa kembali dan mengubah lokasi jika perlu
    }
}