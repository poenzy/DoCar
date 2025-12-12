package com.uas.docar.ui.order.food

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityDriverStatusFoodBinding
import com.uas.docar.ui.order.ride.OrderFinishedActivity

class DriverStatusFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriverStatusFoodBinding

    // ngeset durasi 5 detik.
    private val DELAY_TIME: Long = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverStatusFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startProgressAnimation()

        // Mengambil total harga dari halaman sebelumnya dengan default 0
        val totalHarga = intent.getIntExtra("EXTRA_TOTAL", 0)

        // Memulai Simulasi
        simulateDriverSearch(totalHarga)
    }

    //Simulasi delay
    private fun simulateDriverSearch(totalHarga: Int) {

        // menunda eksekusi kode
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, OrderFinishedActivity::class.java).apply {
                putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_FOOD)

                // Meneruskan total Harga ke halaman akhir
                putExtra("EXTRA_TOTAL", totalHarga)
            }

            startActivity(intent)

            finish()

        }, DELAY_TIME)
    }
    private fun startProgressAnimation() {
        val animator = ObjectAnimator.ofInt(
            binding.driverProgress,
            "progress",
            0,
            100
        )
        animator.duration = DELAY_TIME
        animator.interpolator = LinearInterpolator()
        animator.start()
    }
}