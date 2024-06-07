package com.equipouno.app.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentDeliveryConfirmationDialogBinding

class DeliveryConfirmationDialog : DialogFragment() {
    private var _binding: FragmentDeliveryConfirmationDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeliveryConfirmationDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.closeButton.setOnClickListener {
            Log.d("DeliveryConfirmationDialog", "Close button clicked")
            try {
                val navController = findNavController()
                Log.d("DeliveryConfirmationDialog", "Current destination: ${navController.currentDestination?.id}")
                if (navController.currentDestination?.id == R.id.deliveryConfirmationDialog) {
                    Log.d("DeliveryConfirmationDialog", "Navigating to typesFoodFragment")
                    navController.navigate(R.id.action_deliveryConfirmationDialog_to_typesFoodFragment)
                } else {
                    Log.e("DeliveryConfirmationDialog", "Current destination is not deliveryConfirmationDialog")
                }
            } catch (e: Exception) {
                Log.e("DeliveryConfirmationDialog", "Error navigating", e)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
