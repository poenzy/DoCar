package com.uas.docar.ui.order.ride

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uas.docar.databinding.FragmentRatingDialogBinding

//muncul dari bawah.
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

        // tombol 'kirim'
        binding.btnSubmitRating.setOnClickListener {
            //mengambil nilai dari Input
            val ratingValue = binding.ratingBar.rating
            val komentar = binding.etKomentar.text.toString()

            //validasi input
            if (ratingValue > 0) {
                // skenario Sukses
                Toast.makeText(requireContext(),
                    "Rating $ratingValue bintang berhasil dikirim. Terima kasih!",
                    Toast.LENGTH_LONG).show()


                dismiss()
            } else {
                // skenario gagal
                Toast.makeText(requireContext(), "Mohon berikan bintang terlebih dahulu.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}