package com.equipouno.app.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentProfileBinding
import com.equipouno.app.model.User
import com.equipouno.app.viewmodel.UserModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val userViewModel: UserModel by viewModels()
    private var isEditing = false
    private var currentUser: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = arguments?.getString("email")
        if (email != null) {
            Log.d("ProfileFragment", "Email received: $email")
            observeUserData(email)
        } else {
            Log.d("ProfileFragment", "Email not received")
        }

        binding.editButton.setOnClickListener {
            toggleEditMode()
        }

        // Configura el OnClickListener para el back_icon
        binding.backIcon.setOnClickListener {
            // Navega de vuelta a TypesFoodFragment
            findNavController().navigate(R.id.action_profileFragment_to_typesFoodFragment)
        }
    }

    private fun observeUserData(email: String) {
        userViewModel.getUserByEmail(email).observe(viewLifecycleOwner) { user ->
            user?.let { userData ->
                currentUser = userData
                updateUI(userData)
            }
        }
    }

    private fun updateUI(user: User) {
        binding.nameTextView.text = user.name
        binding.phoneTextView.text = user.tel
        binding.addressTextView.text = user.address
        binding.emailTextView.text = user.email
        binding.birthdateTextView.text = user.birthdate

        binding.nameEditText.setText(user.name)
        binding.phoneEditText.setText(user.tel)
        binding.addressEditText.setText(user.address)
        binding.emailEditText.setText(user.email)
        binding.birthdateEditText.setText(user.birthdate)
    }

    private fun toggleEditMode() {
        isEditing = !isEditing

        binding.nameTextView.visibility = if (isEditing) View.GONE else View.VISIBLE
        binding.phoneTextView.visibility = if (isEditing) View.GONE else View.VISIBLE
        binding.addressTextView.visibility = if (isEditing) View.GONE else View.VISIBLE
        binding.emailTextView.visibility = if (isEditing) View.GONE else View.VISIBLE
        binding.birthdateTextView.visibility = if (isEditing) View.GONE else View.VISIBLE

        binding.nameEditText.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.phoneEditText.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.addressEditText.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.emailEditText.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.birthdateEditText.visibility = if (isEditing) View.VISIBLE else View.GONE

        binding.editButton.text = if (isEditing) "Guardar" else "Editar"

        if (!isEditing) {
            saveUserData()
        }
    }

    private fun saveUserData() {
        val updatedUser = currentUser?.copy(
            name = binding.nameEditText.text.toString(),
            tel = binding.phoneEditText.text.toString(),
            address = binding.addressEditText.text.toString(),
            email = binding.emailEditText.text.toString(),
            birthdate = binding.birthdateEditText.text.toString()
        )

        updatedUser?.let {
            userViewModel.updateUser(it)
            currentUser = it
            updateUI(it) // Immediately update the UI after saving
        }
    }
}