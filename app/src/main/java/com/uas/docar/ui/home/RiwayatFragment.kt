package com.uas.docar.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uas.docar.data.model.RiwayatItem
import com.uas.docar.databinding.FragmentRiwayatBinding
import com.uas.docar.databinding.ItemRiwayatBinding
import com.uas.docar.utils.Formatter

class RiwayatFragment : Fragment() {

    private var _binding: FragmentRiwayatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRiwayatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Memanggil fungsi untuk menyusun daftar riwayat secara manual
        setupManualList()
    }

    private fun getDummyData(): List<RiwayatItem> {
        return listOf(
            RiwayatItem(1, "Nasi Goreng Pak Budi", "15 Des 2025", 18000, "Selesai", "FOOD"),
            RiwayatItem(2, "DoCar ke Stasiun", "13 Des 2025", 25000, "Selesai", "RIDE"),
            RiwayatItem(3, "Ayam Geprek Sai", "12 Des 2025", 22000, "Dibatalkan", "FOOD"),
            RiwayatItem(4, "DoRide ke Kampus", "10 Des 2025", 10000, "Selesai", "RIDE"),
            RiwayatItem(5, "Martabak Manis", "08 Des 2025", 35000, "Selesai", "FOOD")
        )
    }

    // LOGIKA MENAMPILKAN LIST TANPA ADAPTER
    private fun setupManualList() {
        val dataList = getDummyData()

        // Membersihkan Wadah agar tidak terjadi duplikasi
        binding.llRiwayatContainer.removeAllViews()

        // Looping Data
        for (item in dataList) {

            // mengambil desain 'item_riwayat.xml' mengubahnya menjadi View
            val itemBinding = ItemRiwayatBinding.inflate(layoutInflater, binding.llRiwayatContainer, false)

            // Mengisi Data
            itemBinding.tvTitle.text = item.title
            itemBinding.tvDate.text = item.date
            itemBinding.tvPrice.text = Formatter.toRupiah(item.price) // Format harga jadi Rp...
            itemBinding.tvStatus.text = item.status

            // Logika Warna Status Jika batal warna Merah, jika sukses warna Hijau
            if (item.status == "Dibatalkan") {
                itemBinding.tvStatus.setTextColor(Color.RED)
            } else {
                itemBinding.tvStatus.setTextColor(Color.parseColor("#4CAF50"))
            }

            // Menempelkan ke Layar view yang ke dalam ContainerLinearLayout di ScrollView."
            binding.llRiwayatContainer.addView(itemBinding.root)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}