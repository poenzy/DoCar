package com.uas.docar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uas.docar.databinding.FragmentChatBinding

class ChatFragment : Fragment() {


    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    //Menyiapkan Tampilan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menghubungkan file XML 'fragment_chat' ke kode Kotlin ini menggunakan Binding
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    //Logika Tampilan
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    //Pembersihan Memori
    override fun onDestroyView() {
        super.onDestroyView()
        // Menghapus binding saat Fragment tidak terlihat agar memori HP tidak penuh
        _binding = null
    }
}