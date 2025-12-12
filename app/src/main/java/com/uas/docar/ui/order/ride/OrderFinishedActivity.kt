package com.uas.docar.ui.order.ride

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityOrderFinishedFoodBinding
import com.uas.docar.databinding.ActivityOrderFinishedRideBinding
import com.uas.docar.ui.home.HomeActivity
import com.uas.docar.utils.Formatter

class OrderFinishedActivity : AppCompatActivity() {

    companion object {
        const val ORDER_TYPE_KEY = "order_type"
        const val TYPE_FOOD = "FOOD"
        const val TYPE_RIDE = "RIDE"

        const val EXTRA_TOTAL = "EXTRA_TOTAL"
        const val EXTRA_HARGA = "EXTRA_HARGA"
        const val EXTRA_DISKON = "EXTRA_DISKON"
        const val EXTRA_BIAYA_KIRIM = "EXTRA_BIAYA_KIRIM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sembunyikan Toolbar
        supportActionBar?.hide()

        // cek order food/ride
        val orderType = intent.getStringExtra(ORDER_TYPE_KEY) ?: TYPE_RIDE
        val extras = intent.extras

        //logika satu Activity dua layout yang berbeda tergantung transaksinya

        if (orderType == TYPE_FOOD) {
            // jika Makanan -> Pakai XML 'activity_order_finished_food'
            val binding = ActivityOrderFinishedFoodBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Panggil fungsi tampilan makanan
            setupFoodLayoutListeners(binding, extras)

        } else {
            val binding = ActivityOrderFinishedRideBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setupRideLayoutListeners(binding, extras)
        }
    }

    //logika tampilan food
    private fun setupFoodLayoutListeners(binding: ActivityOrderFinishedFoodBinding, extras: Bundle?) {
        val total = extras?.getInt(EXTRA_TOTAL, 0) ?: 0
        val harga = extras?.getInt(EXTRA_HARGA, 0) ?: 0
        val diskon = extras?.getInt(EXTRA_DISKON, 0) ?: 0
        val biayaKirim = extras?.getInt(EXTRA_BIAYA_KIRIM, 0) ?: 0

        binding.tvTotalPembayaranFinish.text = Formatter.toRupiah(total)
        binding.tvHargaFinish.text = Formatter.toRupiah(harga)
        binding.tvDiskonFinish.text = "- " + Formatter.toRupiah(diskon)
        binding.tvBiayaKirimFinish.text = Formatter.toRupiah(biayaKirim)

        binding.btnKeBeranda.setOnClickListener { navigateHome() }
        binding.btnBeriRating.setOnClickListener { showRatingDialog() }
    }

    //logika tampilan ride
    private fun setupRideLayoutListeners(binding: ActivityOrderFinishedRideBinding, extras: Bundle?) {
        val total = extras?.getInt(EXTRA_TOTAL, 0) ?: 0

        binding.tvFinalPrice.text = Formatter.toRupiah(total)

        binding.btnKeBeranda.setOnClickListener { navigateHome() }
        binding.btnBeriRating.setOnClickListener { showRatingDialog() }
    }

    //Dialog Popup Rating
    private fun showRatingDialog() {
        val ratingDialog = RatingDialogFragment()
        ratingDialog.show(supportFragmentManager, RatingDialogFragment.TAG)
    }

    private fun navigateHome() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            // Membersihkan semua tumpukan activity sebelumnya.
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
}