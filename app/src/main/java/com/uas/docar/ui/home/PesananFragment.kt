package com.uas.docar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uas.docar.databinding.FragmentPesananBinding

class PesananFragment : Fragment() {

    // _binding untuk disimpan (bisa null), binding untuk dipakai
    private var _binding: FragmentPesananBinding? = null
    private val binding get() = _binding!!

    // Menyiapkan Tampilan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPesananBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Logika Tampilan
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    //Membersihkan Memori
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}