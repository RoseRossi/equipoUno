package com.equipouno.app.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentDeleveryBinding
import com.equipouno.app.viewmodel.UserModel

class DeliveryFragment : Fragment() {
    private lateinit var binding: FragmentDeleveryBinding
    private val userViewModel: UserModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener el email del usuario desde SharedPreferences (o alguna otra fuente)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val email = sharedPref?.getString("user_email", null)

        if (email != null) {
            observeUserData(email)
        } else {
            Log.e("DeliveryFragment", "No email found")
        }

        val selectedIngredients = arguments?.getStringArrayList("selectedIngredients")
        displaySelectedIngredients(selectedIngredients)

        binding.deliveryButton.setOnClickListener {
            val dialog = DeliveryConfirmationDialog()
            dialog.show(parentFragmentManager, "DeliveryConfirmationDialog")
        }
    }

    private fun observeUserData(email: String) {
        userViewModel.getUserByEmail(email).observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.fieldNameValue.text = it.name
                binding.fieldAddressValue.text = it.address
                binding.fieldPhoneValue.text = it.tel
            }
        })
    }

    private fun displaySelectedIngredients(selectedIngredients: List<String>?) {
        binding.cardIngredientsContainer.removeAllViews()
        selectedIngredients?.forEach { ingredient ->
            val textView = TextView(context).apply {
                text = ingredient
                textSize = 18f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            }
            binding.cardIngredientsContainer.addView(textView)
        }
    }
}
