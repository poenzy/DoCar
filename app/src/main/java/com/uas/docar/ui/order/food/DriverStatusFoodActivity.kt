package com.uas.docar.ui.order.food

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityDriverStatusFoodBinding // Asumsi nama binding
import com.uas.docar.ui.order.ride.OrderFinishedActivity // Import OrderFinishedActivity

class DriverStatusFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriverStatusFoodBinding
    private val DELAY_TIME: Long = 5000 // Simulasi pencarian driver selama 5 detik

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // V KODE KRITIS INI HARUS ADA V
        binding = ActivityDriverStatusFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ^ VIEW BINDING INIT ^

        // 1. Ambil data harga dari Intent
        val totalHarga = intent.getIntExtra("EXTRA_TOTAL", 0)

        // 2. Simulasi Status Driver
        simulateDriverSearch(totalHarga)
    }

    private fun simulateDriverSearch(totalHarga: Int) {
        // Tampilkan pesan/progress bar simulasi mencari driver
        // Asumsi ada TextView/ProgressBar di layout untuk ini (disesuaikan dengan layout Anda)
        // binding.tvStatus.text = "Mencari Driver Terbaik..."

        // Setelah DELAY_TIME, simulasikan driver ditemukan dan pindah ke layar Selesai
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke OrderFinishedActivity
            val intent = Intent(this, OrderFinishedActivity::class.java).apply {
                // Pastikan tipe order dan total harga dibawa
                putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_FOOD)
                putExtra("EXTRA_TOTAL", totalHarga)
                // Anda juga bisa membawa data harga lainnya jika diperlukan untuk OrderFinishedActivity
            }
            startActivity(intent)
            finish()

        }, DELAY_TIME)
    }
}