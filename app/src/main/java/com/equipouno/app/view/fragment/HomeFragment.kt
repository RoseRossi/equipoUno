package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.equipouno.app.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.viewmodel.LoginModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val loginViewModel: LoginModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        this.listener()
    }

    private fun listener()
    {
        binding.loginButton.setOnClickListener {
            this.loginUser()
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_to_registerFragment)
        }
    }

    private fun loginUser(){
        val email = binding.emailEditText.text.toString()
        val pass = binding.passwordEditText.text.toString()
        loginViewModel.loginUser(email,pass){ isLogin ->
            if (isLogin){
                // Aqui va un nav @pau
                Toast.makeText( context, "Todo Bien", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText( context, "Login incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}