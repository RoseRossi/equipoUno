package com.equipouno.app.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentRecipesBinding
import com.equipouno.app.model.Recipe
import com.equipouno.app.view.adapter.ListRecipesAdapter
import com.equipouno.app.viewmodel.RecipeListModel
import com.equipouno.app.viewmodel.UserModel

class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    val viewModel: RecipeListModel by viewModels()
    private val userViewModel: UserModel by viewModels()
    private var overlayContainer: ConstraintLayout? = null
    private var overlay: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type_ = arguments?.getString("type")
        val name_ = arguments?.getString("name")

        if (type_ != null && name_ != null) {
            this.updateListRecipes(type_)
            binding.titleTextView.setText(type_)
            binding.profileName.text = name_
        }

        // Listener
        this.listeners()
        this.handlerHamburger()
    }

    private fun listeners() {
        binding.profileButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_profileFragment)
        }

        binding.logOut.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_nav_home)
        }

        binding.favRecipes.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_deleveryFavFragment)
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

    private fun updateListRecipes(type: String) {
        viewModel.getRecipes(type).observe(viewLifecycleOwner) { recipes ->
            val recyclerView = binding.recyclerViewRecipes
            val adapter = ListRecipesAdapter(recipes) { recipe ->
                val bundle = Bundle().apply {
                    putString("recipeName", recipe.name)
                }
                Log.d("RecipesFragment", "Navigating to RecipeDetailFragment with recipeName: ${recipe.name}")
                findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundle)
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
