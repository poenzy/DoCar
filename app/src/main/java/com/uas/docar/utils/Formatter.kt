package com.uas.docar.utils

import java.text.NumberFormat
import java.util.Locale

object Formatter {
    private val localeID: Locale = Locale.Builder()
        .setLanguage("in")
        .setRegion("ID")
        .build()

    fun toRupiah(amount: Int): String {
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)

        // ini biar ga nampilin desimal
        formatRupiah.maximumFractionDigits = 0

        // set format
        val result = formatRupiah.format(amount)

        return result.replace("Rp", "Rp. ").replace(" ", "")
            .trim()
            .replace("Rp. ", "Rp. ")
    }

    fun formatNumber(amount: Int): String {
        val formatter = NumberFormat.getNumberInstance(localeID)
        return formatter.format(amount)
    }
}