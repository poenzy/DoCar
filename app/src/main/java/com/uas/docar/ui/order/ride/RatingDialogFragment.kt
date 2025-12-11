package com.uas.docar.ui.order.ride

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uas.docar.databinding.FragmentRatingDialogBinding

class RatingDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentRatingDialogBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "RatingDialogFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatingDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Atur Listener untuk Tombol Submit
        binding.btnSubmitRating.setOnClickListener {
            val ratingValue = binding.ratingBar.rating
            val komentar = binding.etKomentar.text.toString()

            if (ratingValue > 0) {
                // Logika Dummy: Kirim data rating ke Toast
                Toast.makeText(requireContext(),
                    "Rating $ratingValue bintang berhasil dikirim. Komentar: $komentar",
                    Toast.LENGTH_LONG).show()
                dismiss() // Tutup dialog setelah pengiriman
            } else {
                Toast.makeText(requireContext(), "Mohon berikan rating (minimal 1 bintang)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}