package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentDeleveryBinding
import com.equipouno.app.databinding.FragmentTypesFoodBinding

class DeleveryFragment : Fragment() {
    private lateinit var binding: FragmentDeleveryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentDeleveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
    }
}