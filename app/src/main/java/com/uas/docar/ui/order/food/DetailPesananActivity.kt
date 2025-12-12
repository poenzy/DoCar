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

    // Agar tidak typo saat mengirim/menerima data antar halaman.
    companion object {
        const val EXTRA_SELECTED_LOCATION = "extra_selected_location"
    }

    // DATA DUMMY
    private val HARGA_DUMMY = 10500
    private val DISKON_DUMMY = 2000
    private val BIAYA_KIRIM_DUMMY = 7000
    private val TOTAL_DUMMY = HARGA_DUMMY - DISKON_DUMMY + BIAYA_KIRIM_DUMMY


    // Fungsi untuk Membuka halaman Ganti Lokasi, lalu MENUNGGU data lokasi baru dikirim balik ke sini.
    private val locationResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Ambil data yang dikirim balik
            val data: Intent? = result.data
            val selectedLocation = data?.getStringExtra(EXTRA_SELECTED_LOCATION)

            // Jika ada datanya, update tampilan
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

        // Set lokasi default saat pertama buka
        updateLocationDisplay("Lokasi Saat Ini: Madiun (Default)")

        setupPriceDisplay()
        setupListeners()
    }

    private fun updateLocationDisplay(newLocation: String) {
        binding.tvLokasiValue.text = newLocation
    }

    private fun setupPriceDisplay() {
        // Menggunakan Class Formatter'
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

        // Tombol Ganti Lokasi menggunakan Launcher (Dua Arah)
        binding.btnGantiLokasi.setOnClickListener {
            val intent = Intent(this, SearchFoodLocationActivity::class.java)
            // .launch() :Pergi dan lapor balik kalau sudah selesai
            locationResultLauncher.launch(intent)
        }
    }

    private fun handleOrderConfirmation() {
        Toast.makeText(this, "Pesanan dikonfirmasi. Mencari driver...", Toast.LENGTH_SHORT).show()

        // Kirim data rincian biaya ke halaman selanjutnya
        val intent = Intent(this, DriverStatusFoodActivity::class.java).apply {
            putExtra(OrderFinishedActivity.ORDER_TYPE_KEY, OrderFinishedActivity.TYPE_FOOD)
            putExtra("EXTRA_HARGA", HARGA_DUMMY)
            putExtra("EXTRA_DISKON", DISKON_DUMMY)
            putExtra("EXTRA_BIAYA_KIRIM", BIAYA_KIRIM_DUMMY)
            putExtra("EXTRA_TOTAL", TOTAL_DUMMY)
        }
        startActivity(intent)
        finish() // Tutup halaman ini agar tidak kembali ke halaman checkout yang sudah selesai
    }

    // Menampilkan Bottom  untuk memilih metode pembayaran
    private fun showPaymentMethodBottomSheet() {
        val paymentMethodFragment = PaymentMethodFragment()
        paymentMethodFragment.show(supportFragmentManager, paymentMethodFragment.tag)
    }
}