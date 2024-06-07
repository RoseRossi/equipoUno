package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentDeleveryFavBinding

class DeleveryFavFragment : Fragment() {
    private lateinit var binding: FragmentDeleveryFavBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleveryFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura el OnClickListener para el back_icon
        binding.backIcon.setOnClickListener {
            // Navega de vuelta a TypesFoodFragment
            findNavController().navigate(R.id.action_deleveryFavFragment_to_typesFoodFragment)
        }
    }
}
