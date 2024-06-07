package com.equipouno.app.view.fragment

import android.content.Context
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
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                val email = sharedPref?.getString("user_email", null)
                val bundle = Bundle().apply {
                    putString("email", email)
                }
                val navController = findNavController()
                navController.navigate(R.id.action_deliveryFragment_to_typesFoodFragment, bundle)
                dismiss()
            } catch (e: Exception) {
                Log.e("DeliveryConfirmationDialog", "Error navigating", e)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
