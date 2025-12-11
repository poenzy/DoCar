package com.uas.docar.ui.order.ride

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityOrderFinishedFoodBinding // Binding FOOD
import com.uas.docar.databinding.ActivityOrderFinishedRideBinding // Binding RIDE
import com.uas.docar.ui.home.HomeActivity
import com.uas.docar.utils.Formatter
import android.content.Intent
import android.widget.Toast

class OrderFinishedActivity : AppCompatActivity() {

    // Konstanta untuk memuat layout yang benar
    companion object {
        const val ORDER_TYPE_KEY = "order_type"
        const val TYPE_FOOD = "FOOD"
        const val TYPE_RIDE = "RIDE"
        // Kunci data dari DetailPesananActivity
        const val EXTRA_TOTAL = "EXTRA_TOTAL"
        const val EXTRA_HARGA = "EXTRA_HARGA"
        const val EXTRA_DISKON = "EXTRA_DISKON"
        const val EXTRA_BIAYA_KIRIM = "EXTRA_BIAYA_KIRIM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        val orderType = intent.getStringExtra(ORDER_TYPE_KEY) ?: TYPE_RIDE
        val extras = intent.extras

        // Muat layout yang sesuai
        if (orderType == TYPE_FOOD) {
            val binding = ActivityOrderFinishedFoodBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupFoodLayoutListeners(binding, extras)

        } else {
            val binding = ActivityOrderFinishedRideBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupRideLayoutListeners(binding, extras)
        }
    }

    // --- SETUP UNTUK FOOD LAYOUT ---
    private fun setupFoodLayoutListeners(binding: ActivityOrderFinishedFoodBinding, extras: Bundle?) {
        // Ambil data dari Intent
        val total = extras?.getInt(EXTRA_TOTAL, 0) ?: 0
        val harga = extras?.getInt(EXTRA_HARGA, 0) ?: 0
        val diskon = extras?.getInt(EXTRA_DISKON, 0) ?: 0
        val biayaKirim = extras?.getInt(EXTRA_BIAYA_KIRIM, 0) ?: 0

        // Menampilkan data biaya
        binding.tvTotalPembayaranFinish.text = Formatter.toRupiah(total)
        binding.tvHargaFinish.text = Formatter.toRupiah(harga)
        binding.tvDiskonFinish.text = "- " + Formatter.toRupiah(diskon)
        binding.tvBiayaKirimFinish.text = Formatter.toRupiah(biayaKirim)

        // Listener Tombol
        binding.btnKeBeranda.setOnClickListener { navigateHome() }
        binding.btnBeriRating.setOnClickListener { showRatingDialog() }
    }

    // --- SETUP UNTUK RIDE LAYOUT ---
    private fun setupRideLayoutListeners(binding: ActivityOrderFinishedRideBinding, extras: Bundle?) {
        // Ambil data yang dibutuhkan untuk layout RIDE (Hanya Total, Jarak, Waktu)
        val total = extras?.getInt(EXTRA_TOTAL, 0) ?: 0

        binding.tvFinalPrice.text = Formatter.toRupiah(total)

        // Listener Tombol
        binding.btnKeBeranda.setOnClickListener { navigateHome() }
        binding.btnBeriRating.setOnClickListener { showRatingDialog() }
    }

    private fun showRatingDialog() {
        val ratingDialog = RatingDialogFragment()
        ratingDialog.show(supportFragmentManager, RatingDialogFragment.TAG)
    }

    private fun navigateHome() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
}