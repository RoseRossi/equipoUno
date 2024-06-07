package com.equipouno.app.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentDeleveryFavBinding
import com.equipouno.app.view.adapter.ListRecipesAdapter
import com.equipouno.app.viewmodel.RecipeListModel

class DeleveryFavFragment : Fragment() {
    private lateinit var binding: FragmentDeleveryFavBinding
    private val viewModel: RecipeListModel by viewModels()

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
            findNavController().navigate(R.id.action_deleveryFavFragment_to_typesFoodFragment)
        }

        // Configura el RecyclerView
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.getFavoriteRecipes().observe(viewLifecycleOwner) { recipes ->
            val adapter = ListRecipesAdapter(recipes) { recipe ->
                // Acción al hacer clic en una receta
            }
            binding.recyclerViewFavorites.adapter = adapter
            binding.recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
