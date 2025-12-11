package com.uas.docar.data.model

data class RiwayatItem(
    val id: Int,
    val title: String,    // Contoh: "Nasi Goreng Pak Budi"
    val date: String,     // Contoh: "16 Okt 2025"
    val price: Int,       // Contoh: 15000
    val status: String,   // Contoh: "Selesai", "Dibatalkan"
    val type: String      // "FOOD" atau "RIDE" (Untuk logika warna/ikon jika perlu)
)