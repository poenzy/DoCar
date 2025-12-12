package com.uas.docar.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.uas.docar.R
import com.uas.docar.databinding.ActivityHomeBinding // View Binding untuk activity_home.xml

class HomeActivity : AppCompatActivity() {

    // View Binding: Jembatan ke desain XML (activity_home.xml)
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Menyiapkan Tampilan (Inflating)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cek Kondisi Awal Aplikasi
        // "savedInstanceState == null" aplikasi benar-benar baru berjalan, bukan habis di-rotate layarnya.
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Logika (Bottom Navigation)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // Jika tombol Home diklik -> Muat HomeFragment
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true // jikatombol terpilih aktif warnanya
                }
                R.id.nav_pesanan -> {
                    loadFragment(PesananFragment())
                    true
                }
                R.id.nav_riwayat -> {
                    loadFragment(RiwayatFragment())
                    true
                }

                R.id.nav_chat -> {
                    loadFragment(ChatFragment())
                    true
                }
                else -> false
            }
        }
    }

    //FRAGMENT TRANSACTION
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            // .replace:Ganti  dengan fragment baru
            .replace(binding.fragmentContainer.id, fragment)
            .commit() //perintah penggantian
    }
}