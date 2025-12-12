package com.uas.docar.ui.order.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uas.docar.databinding.FragmentPaymentMethodBinding

//Mewarisi 'BottomSheetDialogFragment' agar muncul dari bawah (Slide Up)
class PaymentMethodFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPaymentMethodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        //PENGATURAN RADIO GROUP mendeteksi perubahan pilihan
        binding.radioGroupPayment.setOnCheckedChangeListener { _, checkedId ->

            // Logika memilih nama metode berdasarkan ID tombol yang ditekan
            val selectedMethod = when (checkedId) {
                binding.rbDocarWallet.id -> "DoCar Pay"
                binding.rbDanaWallet.id -> "Dana"
                else -> ""
            }

            if (selectedMethod.isNotEmpty()) {
                Toast.makeText(requireContext(), "Metode dipilih: $selectedMethod", Toast.LENGTH_SHORT).show()

                //Tutup BottomSheet otomatis
                dismiss()
            }
        }

        //PENGATURAN TOMBOL TUNAI
        binding.rbCash.setOnClickListener {
            Toast.makeText(requireContext(), "Metode dipilih: Tunai", Toast.LENGTH_SHORT).show()

            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "PaymentMethodFragment"
    }
}