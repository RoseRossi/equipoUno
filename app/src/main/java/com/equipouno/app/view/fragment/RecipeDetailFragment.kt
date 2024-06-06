package com.equipouno.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
//import com.bumptech.glide.Glide
import com.equipouno.app.databinding.FragmentRecipeDetailBinding
import com.equipouno.app.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén el ID de la receta desde los argumentos o cualquier otro método
        val recipeId = "French-Onion-Soup-The-Pioneer-Woman-Cooks-_-Ree-Drummond-41364" // Usa el ID real de la receta
        val appId = "YOUR_ID"
        val appKey = "YOUR_APP_KEY"

        // Carga la receta
        viewModel.loadRecipe(recipeId, appId, appKey)

        // Observa los datos de la receta
//        viewModel.recipe.observe(viewLifecycleOwner, Observer { recipe ->
//            recipe?.let {
//                binding.toolbarTitle.text = it.name
//                binding.descriptionText.text = it.nutritionEstimates?.joinToString("\n") { estimate ->
//                    "${estimate.description}: ${estimate.value} ${estimate.unit.abbreviation}"
//                }
//                binding.collapsibleContent.removeAllViews()
//                it.ingredientLines.forEach { ingredient ->
//                    val checkBox = CheckBox(context).apply {
//                        text = ingredient
//                    }
//                    binding.collapsibleContent.addView(checkBox)
//                }
//                // Usa Glide para cargar la imagen
//                if (it.images.isNotEmpty()) {
//                    Glide.with(this).load(it.images.first().hostedLargeUrl).into(binding.recipeImage)
//                }
//
//                binding.collapsibleTitle.setOnClickListener {
//                    binding.collapsibleContent.visibility = if (binding.collapsibleContent.visibility == View.VISIBLE) {
//                        View.GONE
//                    } else {
//                        View.VISIBLE
//                    }
//                }
//            }
//        })

        // Configura el icono de retroceso
        /*binding.backIcon.setOnClickListener {
            requireActivity().onBackPressed()
        }

         */
    }
}
