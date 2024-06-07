package com.equipouno.app.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentRecipesBinding
import com.equipouno.app.model.Recipe
import com.equipouno.app.view.adapter.ListRecipesAdapter
import com.equipouno.app.viewmodel.RecipeListModel

class RecipesFragment : Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    val viewModel: RecipeListModel by viewModels()

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
        }
    }

    private fun updateListRecipes(type: String) {
        viewModel.getRecipes(type).observe(viewLifecycleOwner) { recipes ->
            val recyclerView = binding.recyclerViewRecipes
            val adapter = ListRecipesAdapter(recipes) { recipe ->
                val bundle = Bundle().apply {
                    putString("recipeName", recipe.name)
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
