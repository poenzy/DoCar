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

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDaftar.setOnClickListener {
            handleRegistration()
        }
    }

    private fun handleRegistration() {
        val namaLengkap = binding.etNamaLengkap.text.toString().trim()
        val emailHp = binding.etEmailHp.text.toString().trim()
        val password = binding.etRegPassword.text.toString().trim()
        val konfirmasiPassword = binding.etKonfirmasiPassword.text.toString().trim()
        val isChecked = binding.cbUpdate.isChecked

        if (namaLengkap.isEmpty() || emailHp.isEmpty() || password.isEmpty() || konfirmasiPassword.isEmpty()) {
            Toast.makeText(this, "Semua kolom wajib diisi.", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != konfirmasiPassword) {
            Toast.makeText(this, "Konfirmasi Password tidak cocok.", Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "Registrasi Berhasil! Anda akan masuk.", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}