package com.uas.docar.utils

import java.text.NumberFormat
import java.util.Locale

// kelas Utilitas (Helper) agar tidak menulis rumus rupiah berulang-ulang."
object Formatter {

    // Menyiapkan standar lokasi Indonesia (Bahasa: in, Negara: ID)
    // Supaya format angkanya pakai titik (.) untuk ribuan, bukan koma (,) ala Amerika.
    private val localeID: Locale = Locale.Builder()
        .setLanguage("in")
        .setRegion("ID")
        .build()

    // Fungsi Mengubah Angka (Int) menjadi Format Rupiah (String)
    fun toRupiah(amount: Int): String {
        // Mengambil format mata uang bawaan Java sesuai lokasi Indonesia
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        // Membuang angka desimal di belakang koma (misal: ,00) agar tampilan bersih
        formatRupiah.maximumFractionDigits = 0

        // Proses format angka menjadi string (Contoh output bawaan: "Rp15.000")
        val result = formatRupiah.format(amount)

        // Custom Styling:
        // Mengubah "Rp15.000" menjadi "Rp. 15.000"
        // .replace("Rp", "Rp. ") menambahkan titik dan spasi setelah Rp
        return result.replace("Rp", "Rp. ")
    }

    // Fungsi untuk mengonversi Format angka biasa tanpa mata uang
    // Berguna untuk menampilkan jarak (km) atau jumlah item
    fun formatNumber(amount: Int): String {
        val formatter = NumberFormat.getNumberInstance(localeID)
        return formatter.format(amount)
    }
}