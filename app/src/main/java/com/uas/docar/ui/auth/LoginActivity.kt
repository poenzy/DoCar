package com.uas.docar.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityLoginBinding
import com.uas.docar.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    // Deklarasi variabel untuk View Binding.
    // 'lateinit' digunakan karena inisialisasi akan dilakukan di 'onCreate'.
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi View Binding. Ini menggantikan findViewById untuk mengakses elemen UI.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        // Menetapkan root view dari binding sebagai tampilan Activity.
        setContentView(binding.root)

        // Listener untuk tombol Masuk (Login).
        // Ketika tombol diklik, fungsi 'handleLogin' akan dipanggil.
        binding.btnMasuk.setOnClickListener {
            handleLogin()
        }

        // Listener untuk TextView Daftar (Register).
        // Ketika teks diklik, fungsi 'navigateToRegister' akan dipanggil untuk pindah ke halaman registrasi.
        binding.tvDaftar.setOnClickListener {
            navigateToRegister()
        }
    }

    /**
     * @function handleLogin
     * @description Menangani logika validasi input dan proses login.
     */
    private fun handleLogin() {

        // Mengambil teks dari EditText Username dan Password.
        // '.trim()' digunakan untuk menghilangkan spasi di awal atau akhir string.
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validasi input: Cek apakah ada field yang kosong.
        if (username.isEmpty() || password.isEmpty()) {
            // Jika ada yang kosong, tampilkan pesan error menggunakan Toast.
            Toast.makeText(this, "Mohon isi Username dan Password.", Toast.LENGTH_SHORT).show()
            return // Menghentikan eksekusi fungsi lebih lanjut.
        }

        // Jika validasi lolos dan (seolah-olah) login berhasil:
        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
        // Membuat Intent untuk pindah ke HomeActivity.
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        // 'finish()' digunakan agar pengguna tidak bisa kembali ke LoginActivity
        // menggunakan tombol back setelah berhasil login.
        finish()
    }

    /**
     * @function navigateToRegister
     * @description Navigasi ke Activity pendaftaran (RegisterActivity).
     */
    private fun navigateToRegister() {
        // Membuat Intent untuk pindah ke RegisterActivity.
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        // Kita tidak menggunakan 'finish()' di sini karena pengguna mungkin ingin
        // kembali ke halaman login jika mereka berubah pikiran saat mendaftar.
    }
}