package com.uas.docar.ui.order.ride

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityDriverStatusRideBinding
import com.uas.docar.ui.order.ride.OrderFinishedActivity

class DriverStatusRideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriverStatusRideBinding

    // delay 5 Detik
    private val TRIP_SIMULATION_TIME: Long = 5000

    // Handleruntuk menunda eksekusi kode
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverStatusRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startProgressAnimation()

        //Timer untuk Pindah Halaman
        handler.postDelayed({
            // dijalankan setelah 5 detik
            navigateToTripFinished(intent)
        }, TRIP_SIMULATION_TIME)
    }

    // logika animasi
    private fun startProgressAnimation() {
        val animator = ObjectAnimator.ofInt(
            binding.driverProgress,
            "progress",
            0,
            100
        )

        animator.duration = TRIP_SIMULATION_TIME

        //Kecepatan konstan
        animator.interpolator = LinearInterpolator()

        animator.start()
    }

    private fun navigateToTripFinished(originalIntent: Intent) {
        val finishedIntent = Intent(this, OrderFinishedActivity::class.java)

        // Mengoper semua data dari halaman sebelumnya
        if (originalIntent.extras != null) {
            finishedIntent.putExtras(originalIntent.extras!!)
        }

        // konfirmasi ini adalah order RIDE
        finishedIntent.putExtra(
            OrderFinishedActivity.ORDER_TYPE_KEY,
            OrderFinishedActivity.TYPE_RIDE
        )

        startActivity(finishedIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        // pembatalkan Timer aplikasi bila keluar sebelum 5 detik"
        handler.removeCallbacksAndMessages(null)
    }
}