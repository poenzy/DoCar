package com.uas.docar.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uas.docar.databinding.ActivityLoginBinding
import com.uas.docar.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    // deklasari awal tanpa variabel terlebih dahulu
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        // Menetapkan root view dari binding sebagai tampilan Activity.
        setContentView(binding.root)

        binding.btnMasuk.setOnClickListener {
            handleLogin()
        }

        binding.tvDaftar.setOnClickListener {
            navigateToRegister()
        }
    }

    private fun handleLogin() {

        // '.trim()' digunakan untuk menghilangkan spasi di awal atau akhir string.
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validasi  apakah ada yang kosong.
        if (username.isEmpty() || password.isEmpty()) {
            // tampilkan pesan error jika kosong
            Toast.makeText(this, "Mohon isi Username dan Password.", Toast.LENGTH_SHORT).show()
            return // Menghentikan eksekusi fungsi
        }

        // Jika validasi lolos
        Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
        // pindah ke HomeActivity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRegister() {
        // Intent untuk pindah ke RegisterActivity.
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        // tidak menggunakan 'finish()' karena apabila pengguna ingin kembali ke halaman login jika tidak jadi regist.
    }
}