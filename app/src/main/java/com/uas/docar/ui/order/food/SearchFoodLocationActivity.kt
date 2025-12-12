package com.uas.docar.ui.order.food

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
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

        //Tombol kembali di pojok kiri atas
        binding.btnBack.setOnClickListener {
            cancelAndFinish()
        }


        val callback = object : OnBackPressedCallback(true ) {
            override fun handleOnBackPressed() {
                cancelAndFinish()
            }
        }
        // Daftarkan callback ke sistem Activity
        onBackPressedDispatcher.addCallback(this, callback)

        setupSearchHistory()
    }

    private fun setupSearchHistory() {
        val historyList = listOf(
            "Rumah (Magetan)",
            "Kampus (Politeknik Negeri Madiun)",
            "Mall PCC Madiun"
        )

        val adapter = SearchHistoryAdapter(historyList) { location ->
            // Saatdiklik irim lokasi kembali ke halaman sebelumnya
            sendResultAndFinish(location)
        }

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

    private fun sendResultAndFinish(selectedLocation: String) {
        val resultIntent = Intent().apply {
            putExtra(EXTRA_SELECTED_LOCATION, selectedLocation)
        }
        // Set status berhasil dan lampirkan datanya
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    //FUNGSI PEMBATALAN
    private fun cancelAndFinish() {
        // Set status CANCEL agar halaman sebelumnya tahu user tidak jadi memilih
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}