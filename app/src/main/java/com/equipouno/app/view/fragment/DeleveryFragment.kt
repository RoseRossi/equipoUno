package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.equipouno.app.databinding.FragmentDeleveryBinding
import com.equipouno.app.viewmodel.UserModel

class DeleveryFragment : Fragment() {
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

        // Supongamos que tienes el email del usuario de alguna manera
        val userEmail = "user@example.com"

        // Observa los datos del usuario
        userViewModel.getUserByEmail(userEmail).observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                binding.fieldNameValue.text = it.name
                binding.fieldAddressValue.text = it.address
                binding.fieldPhoneValue.text = it.tel
            }
        })
    }
}
