package com.uas.docar.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.uas.docar.R
import com.uas.docar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Muat Fragment Home sebagai Fragment default saat pertama kali dibuka
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        // Listener untuk Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
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

    // Fungsi untuk mengganti Fragment di dalam FrameLayout
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}