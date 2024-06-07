package com.equipouno.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.navArgs
import com.equipouno.app.databinding.FragmentRecipeDetailBinding
import com.equipouno.app.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    //private val args: RecipeDetailFragmentArgs by navArgs() // Para recibir los argumentos de navegación

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val recipeId = args.recipeId // Obtén el ID de la receta de los argumentos de navegación

        /*viewModel.getRecipeById(recipeId).observe(viewLifecycleOwner) { recipe ->
            recipe?.let {
                binding.collapsibleContentIngredients.removeAllViews()
                it.ingredients.forEach { ingredient ->
                    val textView = TextView(context).apply {
                        text = ingredient
                    }
                    binding.collapsibleContentIngredients.addView(textView)
                }

                binding.collapsibleContentSteps.removeAllViews()
                it.steps.forEach { step ->
                    val textView = TextView(context).apply {
                        text = step
                    }
                    binding.collapsibleContentSteps.addView(textView)
                }

                binding.descriptionText.text = it.description
                binding.toolbarTitle.text = it.name
            }
        }

         */

        // Configurar el botón de Delivery
        binding.deliveryButton.setOnClickListener {
            // Acción cuando se presiona el botón de Delivery
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
