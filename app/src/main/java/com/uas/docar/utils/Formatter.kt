package com.uas.docar.utils

import java.text.NumberFormat
import java.util.Locale

object Formatter {

    // DEFINE LOCALE ONCE USING THE MODERN BUILDER METHOD
    private val localeID: Locale = Locale.Builder()
        .setLanguage("in") // 'in' for Indonesian
        .setRegion("ID")   // 'ID' for Indonesia
        .build()           // The full Locale object

    /**
     * Memformat angka integer menjadi string Rupiah yang mudah dibaca (Rp. 7.500).
     */
    fun toRupiah(amount: Int): String {

        // 1. Dapatkan instance NumberFormat menggunakan objek Locale yang baru
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        // 2. Atur agar tidak menampilkan desimal
        formatRupiah.maximumFractionDigits = 0

        // 3. Format angka
        val result = formatRupiah.format(amount)

        // 4. Koreksi format default
        return result.replace("Rp", "Rp. ").replace(" ", "")
            .trim()
            .replace("Rp. ", "Rp. ")
    }

    /**
     * Contoh fungsi lain: Memformat angka tanpa mata uang (e.g., 12.000)
     */
    fun formatNumber(amount: Int): String {
        // Gunakan localeID yang sudah didefinisikan
        val formatter = NumberFormat.getNumberInstance(localeID)
        return formatter.format(amount)
    }
}