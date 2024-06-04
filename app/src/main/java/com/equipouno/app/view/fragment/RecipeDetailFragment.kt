
package com.equipouno.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.equipouno.app.databinding.FragmentRecipeDetailBinding

class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén una referencia al título del botón desplegable
        val collapsibleTitle = binding.collapsibleTitle

        // Agrega un OnClickListener al título
        collapsibleTitle.setOnClickListener {
            // Cambia la visibilidad del contenido cuando se haga clic
            val collapsibleContent = binding.collapsibleContent
            collapsibleContent.visibility = if (collapsibleContent.visibility == View.VISIBLE) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

}