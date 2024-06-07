package com.equipouno.app.view.fragment

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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

        val userEmail = "user@example.com"

        userViewModel.getUserByEmail(userEmail).observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.fieldNameValue.text = it.name
                binding.fieldAddressValue.text = it.address
                binding.fieldPhoneValue.text = it.tel
            }
        })

        val selectedIngredients = arguments?.getStringArrayList("selectedIngredients")
        displaySelectedIngredients(selectedIngredients)

        binding.deliveryButton.setOnClickListener {
            val dialog = DeliveryConfirmationDialog()
            dialog.show(parentFragmentManager, "DeliveryConfirmationDialog")
        }
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
