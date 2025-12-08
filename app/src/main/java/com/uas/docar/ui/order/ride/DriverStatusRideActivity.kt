package com.uas.docar.ui.order.ride

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityDriverStatusRideBinding // Binding khusus Ride
// Import OrderFinishedActivity untuk mengakses konstanta
import com.uas.docar.ui.order.ride.OrderFinishedActivity

class DriverStatusRideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriverStatusRideBinding

    private val TRIP_SIMULATION_TIME: Long = 5000
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriverStatusRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        startProgressAnimation()

        handler.postDelayed({
            navigateToTripFinished(intent)
        }, TRIP_SIMULATION_TIME)
    }

    private fun startProgressAnimation() {
        val animator = ObjectAnimator.ofInt(
            binding.driverProgress,
            "progress",
            0,
            100
        )
        animator.duration = TRIP_SIMULATION_TIME
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private fun navigateToTripFinished(originalIntent: Intent) {
        val finishedIntent = Intent(this, OrderFinishedActivity::class.java)

        if (originalIntent.extras != null) {
            finishedIntent.putExtras(originalIntent.extras!!)
        }

        finishedIntent.putExtra(
            OrderFinishedActivity.ORDER_TYPE_KEY,
            OrderFinishedActivity.TYPE_RIDE
        )

        startActivity(finishedIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}