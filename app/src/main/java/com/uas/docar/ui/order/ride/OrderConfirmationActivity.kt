package com.uas.docar.ui.order.ride

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityOrderConfirmationBinding
import com.uas.docar.utils.Formatter

class OrderConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderConfirmationBinding

    private val hargaMotor = 7500
    private val hargaMobil = 60500
    private var currentPrice = hargaMotor // Simpan harga yang sedang dipilih

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tampilkan Harga Awal (Default Motor)
        updatePriceDisplay(hargaMotor)

        // 2. Listener untuk RadioGroup Kendaraan
        binding.rgKendaraan.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.rbMotor.id -> { currentPrice = hargaMotor; updatePriceDisplay(hargaMotor) }
                binding.rbMobil.id -> { currentPrice = hargaMobil; updatePriceDisplay(hargaMobil) }
            }
        }

        // 3. Listener Tombol Mencari Driver
        binding.btnCariDriver.setOnClickListener {
            val intent = Intent(this, DriverStatusRideActivity::class.java).apply {
                // Meneruskan data harga ride yang sudah diupdate
                putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_RIDE)
                putExtra("EXTRA_TOTAL", currentPrice)
                // Tambahkan data ride lainnya
                putExtra("EXTRA_DISTANCE", 500)
                putExtra("EXTRA_TIME", 7)
            }
            startActivity(intent)
            finish()
        }
    }

    // FUNGSI INI ADALAH SOLUSI UNTUK ERROR UNRESOLVED REFERENCE
    private fun updatePriceDisplay(price: Int) {
        val formattedPrice = Formatter.toRupiah(price)
        binding.tvBasePrice.text = formattedPrice + ",-"
    }
}