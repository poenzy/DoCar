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

    // Variabel penampung harga
    private var currentPrice = hargaMotor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Inisiasi Tampilan Awal dan Set harga default
        updatePriceDisplay(hargaMotor)

        // Radio ganti halaman
        binding.rgKendaraan.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                // pilih Motor -> Set variabel ke harga motor -> Update Teks
                binding.rbMotor.id -> {
                    currentPrice = hargaMotor
                    updatePriceDisplay(hargaMotor)
                }
                binding.rbMobil.id -> {
                    currentPrice = hargaMobil
                    updatePriceDisplay(hargaMobil)
                }
            }
        }

        binding.btnCariDriver.setOnClickListener {
            val intent = Intent(this, DriverStatusRideActivity::class.java).apply {
                // Meneruskan Tipe Order
                putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_RIDE)

                //Meneruskan harga terakhir
                putExtra("EXTRA_TOTAL", currentPrice)

                // Data tambahan (Simulasi Jarak & Waktu)
                putExtra("EXTRA_DISTANCE", 500)
                putExtra("EXTRA_TIME", 7)
            }
            startActivity(intent)
            finish()
        }
    }

    private fun updatePriceDisplay(price: Int) {
        // format ke rupiah
        val formattedPrice = Formatter.toRupiah(price)

        // Menambahkan akhiran ",-"
        binding.tvBasePrice.text = "$formattedPrice,-"
    }
}