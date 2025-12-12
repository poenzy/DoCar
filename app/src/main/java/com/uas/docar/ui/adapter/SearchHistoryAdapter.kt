package com.uas.docar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.databinding.ItemSearchHistoryBinding // View Binding untuk item_search_history.xml

// Kelas utama Adapter, mewarisi dari RecyclerView.Adapter.
class SearchHistoryAdapter(
    // Daftar data (Riwayat pencarian, berupa List of String).
    private val historyList: List<String>,
    // fungsi lambda yangdipanggil ketika item diklik.
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder>() {

    // 1. ViewHolder: Digunakan untuk menampung dan mengelola tampilan satu item dalam daftar.
    inner class HistoryViewHolder(private val binding: ItemSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //mengisi data ke dalam tampilan item.
        fun bind(location: String) {
            // Mengatur teks riwayat pencarian
            binding.tvHistoryLocation.text = location

            // Menambahkan OnClickListener pada root view
            binding.root.setOnClickListener {
                // memanggil fungsi lambda dan berikan data 'location'
                onItemClick(location)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        // mengembalikan instance HistoryViewHolder
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        // Mengambil data String sesuai posisi
        val item = historyList[position]
        // Memanggil fungsi bind
        holder.bind(item)
    }

    // Mengembalikan jumlah total item dalam dataset.
    override fun getItemCount(): Int = historyList.size
}