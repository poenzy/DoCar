package com.uas.docar.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityRegisterBinding
import com.uas.docar.ui.home.HomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inisialisasi Tampilan
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // jika tombol diklik jalankan fungsi 'handleRegistration'
        binding.btnDaftar.setOnClickListener {
            handleRegistration()
        }
    }

    // LOGIKA REGISTRASI
    private fun handleRegistration() {
        // .trim() digunakan untuk menghapus spasi tidak sengaja di awal/akhir teks
        val namaLengkap = binding.etNamaLengkap.text.toString().trim()
        val emailHp = binding.etEmailHp.text.toString().trim()
        val password = binding.etRegPassword.text.toString().trim()
        val konfirmasiPassword = binding.etKonfirmasiPassword.text.toString().trim()
        val isChecked = binding.cbUpdate.isChecked // Cek apakah user setuju update berita (opsional)

        // Cek Kekosongan Data
        // sistem menolak jika ada satu saja kolom yang kosong
        if (namaLengkap.isEmpty() || emailHp.isEmpty() || password.isEmpty() || konfirmasiPassword.isEmpty()) {
            Toast.makeText(this, "Semua kolom wajib diisi.", Toast.LENGTH_SHORT).show()
            return // Berhenti di sini, jangan lanjut ke bawah
        }

        // Pencocokan Password
        // Sistem memastikan pengguna tidak typo saat membuat password
        if (password != konfirmasiPassword) {
            Toast.makeText(this, "Konfirmasi Password tidak cocok.", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulasi Sukses (Karena belum ada Backend/Database)
        Toast.makeText(this, "Registrasi Berhasil! Anda akan masuk.", Toast.LENGTH_LONG).show()

        // Navigasi ke Home
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

        // Membersihkan Riwayat jika user menekan tombol 'Back' tidak kembali ke halaman Register tapi keluar aplikasi."
        finishAffinity()
    }
}