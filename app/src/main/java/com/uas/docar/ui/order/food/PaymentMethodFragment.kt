package com.uas.docar.ui.order.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uas.docar.databinding.FragmentPaymentMethodBinding

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

        // --- KOREKSI ERROR DIMULAI DI SINI ---

        // 1. KOREKSI: Gunakan 'radio_group_payment' (bukan rGEWallet)
        binding.radioGroupPayment.setOnCheckedChangeListener { _, checkedId ->
            val selectedMethod = when (checkedId) {
                // 2. KOREKSI: Gunakan 'rb_docar_wallet' (bukan rbDocarPay)
                binding.rbDocarWallet.id -> "DoCar Pay"

                // 3. KOREKSI: Gunakan 'rb_dana_wallet' (bukan rbDana)
                binding.rbDanaWallet.id -> "Dana"

                else -> ""
            }
            if (selectedMethod.isNotEmpty()) {
                Toast.makeText(requireContext(), "Metode dipilih: $selectedMethod", Toast.LENGTH_SHORT).show()
                // TODO: Simpan pilihan ke Activity/ViewModel
                dismiss()
            }
        }

        // Listener untuk Tunai (Cash)
        binding.rbCash.setOnClickListener {
            Toast.makeText(requireContext(), "Metode dipilih: Tunai", Toast.LENGTH_SHORT).show()
            // TODO: Simpan pilihan 'Cash' ke Activity/ViewModel
            dismiss()
        }
        // --- KOREKSI ERROR SELESAI ---
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "PaymentMethodFragment"
    }
}