package com.uas.docar.ui.order.food

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback // *** IMPORT BARU ***
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.docar.databinding.ActivitySearchFoodLocationBinding
import com.uas.docar.ui.adapter.SearchHistoryAdapter
import com.uas.docar.ui.order.food.DetailPesananActivity.Companion.EXTRA_SELECTED_LOCATION

class SearchFoodLocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchFoodLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchFoodLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Listener tombol kembali di toolbar/header
        binding.btnBack.setOnClickListener {
            cancelAndFinish()
        }

        // V MIGRASI KE OnBackPressedDispatcher (KOREKSI TIPE ARGUMEN) V
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // Logika ini dipanggil ketika tombol/gestur back ditekan
                cancelAndFinish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
        // ^ KODE SUDAH BENAR SESUAI DENGAN API OnBackPressedDispatcher ^

        setupSearchHistory()
    }

    private fun setupSearchHistory() {
        // Data Dummy untuk riwayat lokasi
        val historyList = listOf(
            "Rumah (Magetan)",
            "Kampus (Politeknik Negeri Madiun)",
            "Mall PCC Madiun"
        )

        val adapter = SearchHistoryAdapter(historyList) { location ->
            sendResultAndFinish(location)
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

    private fun sendResultAndFinish(selectedLocation: String) {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_SELECTED_LOCATION, selectedLocation)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    private fun cancelAndFinish() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}