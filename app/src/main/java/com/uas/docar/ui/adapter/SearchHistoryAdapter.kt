package com.uas.docar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uas.docar.databinding.ItemSearchHistoryBinding // View Binding untuk item_search_history.xml

// Kelas utama Adapter, mewarisi dari RecyclerView.Adapter.
class SearchHistoryAdapter(
    // Parameter konstruktor 1: Daftar data (Riwayat pencarian, berupa List of String).
    private val historyList: List<String>,
    // Parameter konstruktor 2: Sebuah fungsi lambda yang akan dipanggil ketika item diklik.
    // Fungsi ini menerima satu String (lokasi yang diklik) dan tidak mengembalikan nilai.
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder>() {

    // 1. ViewHolder: Digunakan untuk menampung dan mengelola tampilan satu item dalam daftar.
    inner class HistoryViewHolder(private val binding: ItemSearchHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Fungsi 'bind' bertugas mengisi data ke dalam tampilan item.
        fun bind(location: String) {
            // Mengatur teks riwayat pencarian ke TextView yang ada di layout item.
            binding.tvHistoryLocation.text = location

            // Menambahkan OnClickListener pada root view (seluruh item).
            binding.root.setOnClickListener {
                // Ketika item diklik, panggil fungsi lambda yang diberikan di konstruktor,
                // dan berikan data 'location' sebagai argumen.
                onItemClick(location)
            }
        }
    }

    // 2. onCreateViewHolder: Bertanggung jawab membuat ViewHolder baru.
    // Dipanggil ketika RecyclerView membutuhkan ViewHolder baru untuk menampilkan item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        // Menggunakan View Binding untuk meng-inflate layout item (item_search_history.xml).
        val binding = ItemSearchHistoryBinding.inflate(
            LayoutInflater.from(parent.context), // Mendapatkan LayoutInflater dari konteks parent.
            parent, // Parent (ViewGroup)
            false // 'false' karena RecyclerView yang akan menambahkan view ini, bukan kita.
        )
        // Membuat dan mengembalikan instance HistoryViewHolder dengan binding yang baru dibuat.
        return HistoryViewHolder(binding)
    }

    // 3. onBindViewHolder: Bertanggung jawab mengikat data ke ViewHolder pada posisi tertentu.
    // Dipanggil berulang kali untuk mengisi data ke ViewHolder yang sudah ada.
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        // Mengambil data String dari list sesuai 'position'.
        val item = historyList[position]
        // Memanggil fungsi 'bind' pada ViewHolder untuk mengisi data tersebut ke UI.
        holder.bind(item)
    }

    // 4. getItemCount: Mengembalikan jumlah total item dalam dataset.
    override fun getItemCount(): Int = historyList.size
}