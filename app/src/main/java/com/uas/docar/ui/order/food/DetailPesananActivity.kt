package com.uas.docar.ui.order.food

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityDetailPesananBinding
import com.uas.docar.ui.order.food.DriverStatusFoodActivity
import com.uas.docar.ui.order.food.SearchFoodLocationActivity
import com.uas.docar.ui.order.ride.OrderFinishedActivity
import com.uas.docar.utils.Formatter

class DetailPesananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPesananBinding

    companion object {
        const val EXTRA_SELECTED_LOCATION = "extra_selected_location"
    }

    private val HARGA_DUMMY = 10500
    private val DISKON_DUMMY = 2000
    private val BIAYA_KIRIM_DUMMY = 7000
    private val TOTAL_DUMMY = HARGA_DUMMY - DISKON_DUMMY + BIAYA_KIRIM_DUMMY

    // REGISTER BUAT NERIMA HASIL ACT DARI SearchFoodLocationAct
    private val locationResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedLocation = data?.getStringExtra(EXTRA_SELECTED_LOCATION)

            if (!selectedLocation.isNullOrEmpty()) {
                updateLocationDisplay(selectedLocation)
                Toast.makeText(this, "Lokasi diubah ke: $selectedLocation", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateLocationDisplay("Lokasi Saat Ini: Madiun (Default)")

        setupPriceDisplay()
        setupListeners()
    }

    private fun updateLocationDisplay(newLocation: String) {
        binding.tvLokasiValue.text = newLocation
    }

    private fun setupPriceDisplay() {
        binding.tvHarga.text = Formatter.toRupiah(HARGA_DUMMY)
        binding.tvDiskon.text = Formatter.toRupiah(DISKON_DUMMY)
        binding.tvBiayaPenangananDanPengiriman.text = Formatter.toRupiah(BIAYA_KIRIM_DUMMY)
        binding.tvTotalPembayaran.text = Formatter.toRupiah(TOTAL_DUMMY)
    }

    private fun setupListeners() {
        binding.btnKonfirmasi.setOnClickListener {
            handleOrderConfirmation()
        }

        binding.btnGantiMetode.setOnClickListener {
            showPaymentMethodBottomSheet()
        }

        binding.btnGantiLokasi.setOnClickListener {
            val intent = Intent(this, SearchFoodLocationActivity::class.java)
            locationResultLauncher.launch(intent)
        }
    }

    private fun handleOrderConfirmation() {
        Toast.makeText(this, "Pesanan dikonfirmasi. Mencari driver...", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DriverStatusFoodActivity::class.java).apply {
            putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_FOOD)
            putExtra("EXTRA_HARGA", HARGA_DUMMY)
            putExtra("EXTRA_DISKON", DISKON_DUMMY)
            putExtra("EXTRA_BIAYA_KIRIM", BIAYA_KIRIM_DUMMY)
            putExtra("EXTRA_TOTAL", TOTAL_DUMMY)
        }
        startActivity(intent)
        finish()
    }

    private fun showPaymentMethodBottomSheet() {
        val paymentMethodFragment = PaymentMethodFragment()
        paymentMethodFragment.show(supportFragmentManager, paymentMethodFragment.tag)
    }
}