package com.equipouno.app.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.equipouno.app.R
import com.equipouno.app.databinding.FragmentRecipeDetailBinding
import com.equipouno.app.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeName = arguments?.getString("recipeName")
        Log.d("RecipeDetailFragment", "Received recipeName: $recipeName")

        if (recipeName != null) {
            viewModel.getRecipeByName(recipeName).observe(viewLifecycleOwner, Observer { recipe ->
                recipe?.let {
                    Log.d("RecipeDetailFragment", "Recipe details: $it")

                    binding.toolbarTitle.text = it.name // Mostrar el nombre de la receta en el toolbar

                    binding.collapsibleContentIngredients.removeAllViews()
                    it.ingredients.forEach { ingredient ->
                        val checkBox = CheckBox(context).apply {
                            text = ingredient
                        }
                        binding.collapsibleContentIngredients.addView(checkBox)
                    }

                    binding.collapsibleContentSteps.removeAllViews()
                    it.steps.forEachIndexed { index, step ->
                        val textView = TextView(context).apply {
                            text = "${index + 1}. $step"
                        }
                        binding.collapsibleContentSteps.addView(textView)
                    }

                    binding.descriptionText.text = it.description
                }
            })
        } else {
            Log.e("RecipeDetailFragment", "No recipeName received")
        }

        // Configurar el botón de Delivery
        binding.deliveryButton.setOnClickListener {
            val selectedIngredients = getSelectedIngredients()
            val bundle = Bundle().apply {
                putStringArrayList("selectedIngredients", ArrayList(selectedIngredients))
            }
            findNavController().navigate(R.id.action_recipeDetailFragment_to_deleveryFragment, bundle)
        }

        // Configura el OnClickListener para el back_icon
        binding.backIcon.setOnClickListener {
            // Navega de vuelta a TypesFoodFragment
            findNavController().navigate(R.id.action_recipeDetailFragment_to_typesFoodFragment)
        }

        // Configurar los botones colapsables
        setupCollapsibleButtons()
    }

    private fun getSelectedIngredients(): List<String> {
        val selectedIngredients = mutableListOf<String>()
        for (i in 0 until binding.collapsibleContentIngredients.childCount) {
            val checkBox = binding.collapsibleContentIngredients.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                selectedIngredients.add(checkBox.text.toString())
            }
        }
        return selectedIngredients
    }

    private fun setupCollapsibleButtons() {
        binding.headerIngredientsNew.setOnClickListener {
            toggleSection(binding.collapsibleContentIngredients, binding.arrowIconIngredients)
        }
        binding.headerStepsNew.setOnClickListener {
            toggleSection(binding.collapsibleContentSteps, binding.arrowIconSteps)
        }
    }

    private fun toggleSection(content: View, arrow: View) {
        if (content.visibility == View.VISIBLE) {
            content.visibility = View.GONE
            arrow.rotation = 0f
        } else {
            content.visibility = View.VISIBLE
            arrow.rotation = 180f
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
