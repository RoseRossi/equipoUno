package com.equipouno.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentTypesFoodBinding

class TypesFoodFragment : Fragment() {
    private lateinit var binding: FragmentTypesFoodBinding
    private var overlayContainer: ConstraintLayout? = null
    private var overlay: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTypesFoodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.listeners()
        this.handlerHamburger()
        this.handlerRecipesListener()
    }

    private fun listeners() {
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_typesFoodFragment_to_profileFragment)
        }

        binding.logOut.setOnClickListener {
            findNavController().navigate(R.id.action_typesFoodFragment_to_nav_home)
        }

        binding.favRecipes.setOnClickListener {
            findNavController().navigate(R.id.action_typesFoodFragment_to_deleveryFavFragment)
        }
    }

    private fun handlerHamburger() {
        binding.menuButton.setOnClickListener { toggleOverlay() }
        overlayContainer = binding.overlayContainer
        overlay = binding.overlay
        overlay?.setOnClickListener { hideOverlay() }
    }

    private fun toggleOverlay() {
        if (overlayContainer?.visibility == View.VISIBLE) {
            overlayContainer?.visibility = View.GONE
            overlay?.visibility = View.GONE
        } else {
            overlayContainer?.visibility = View.VISIBLE
            overlay?.visibility = View.VISIBLE
        }
    }

    private fun hideOverlay() {
        overlayContainer?.visibility = View.GONE
        overlay?.visibility = View.GONE
    }

    private fun handlerRecipesListener()
    {
        val gridLayout: GridLayout = binding.gridLayout

        for (i in 0 until gridLayout.childCount) {
            val child: View = gridLayout.getChildAt(i)

            if (child is Button) {
                child.setOnClickListener {
                    findNavController().navigate(R.id.action_typesFoodFragment_to_recipesFragment)
                }
            }
        }
    }
}
