package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.equipouno.app.databinding.FragmentRegisterBinding
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.model.User
import com.equipouno.app.viewmodel.LoginModel

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val loginViewModel: LoginModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val address = binding.addressEditText.text.toString()
            val birthdate = binding.birthdateEditText.text.toString()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty() || birthdate.isEmpty()) {
                Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newUser = User(
                id = "",
                name = name,
                password = password,
                tel = phone,
                address = address,
                birthdate = birthdate,
                email = email
            )

            loginViewModel.registerUser(newUser){ isCreated ->
                if (isCreated){
                    Toast.makeText( context, "Se creo correctamente", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_nav_home)
                }else {
                    Toast.makeText( context, "Ups!!, paso un error al crear el usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.loginButtonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_nav_home)
        }
    }
}