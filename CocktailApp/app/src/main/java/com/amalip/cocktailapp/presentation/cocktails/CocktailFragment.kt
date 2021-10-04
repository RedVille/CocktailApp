package com.amalip.cocktailapp.presentation.cocktails

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amalip.cocktailapp.MainActivity
import com.amalip.cocktailapp.R
import com.amalip.cocktailapp.core.extension.failure
import com.amalip.cocktailapp.core.extension.observe
import com.amalip.cocktailapp.core.presentation.BaseFragment
import com.amalip.cocktailapp.core.presentation.BaseViewState
import com.amalip.cocktailapp.databinding.CocktailFragmentBinding
import com.amalip.cocktailapp.domain.model.Cocktail
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CocktailFragment : BaseFragment(R.layout.cocktail_fragment) {

    private lateinit var binding: CocktailFragmentBinding

    private lateinit var adapter: CocktailAdapter
    private lateinit var adapterGrid: CocktailAdapterGrid

    private val cocktailViewModel by viewModels<CocktailViewModel>()

    var listlinear: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchByName("")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.fabSwitch.setOnClickListener { onSwitch() }

        binding.svCocktail.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.rcCocktails.layoutManager = LinearLayoutManager(requireContext())
                if (query != null) {
                    searchByName(query)
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                binding.rcCocktails.layoutManager = LinearLayoutManager(requireContext())
                if (newText != null) {
                    searchByName(newText)
                }

                return false
            }

        })
    }

    fun searchByName(name: String) {
        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged) // llama a la función cuando el stado de la vista cambia
            failure(failure, ::handleFailure) // llama la función cuando detecta un error

            doGetCocktailsByName(name)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CocktailViewState.CocktailsReceived -> setUpAdapter(state.cocktails)
        }
    }

    private fun setUpAdapter(cocktails: List<Cocktail>) {
        adapter = CocktailAdapter()
        adapterGrid = CocktailAdapterGrid()

        adapter.addData(cocktails)
        adapterGrid.addData(cocktails)

        binding.rcCocktails.apply {
            adapter = this@CocktailFragment.adapter
        }
    }

    // bindear la vista del fragmento sin necesidad de setear cada id de la vista
    override fun setBinding(view: View) {
        binding = CocktailFragmentBinding.bind(view)

        binding.lifecycleOwner = this
    }

    // cambiar a grid
    fun onSwitch() {
        if (listlinear) {
            listlinear = false
            binding.rcCocktails.layoutManager = GridLayoutManager(MainActivity(), 3)

            binding.rcCocktails.apply {
                adapter = this@CocktailFragment.adapterGrid
            }

        } else{
            listlinear = true
            binding.rcCocktails.layoutManager = LinearLayoutManager(MainActivity(),LinearLayoutManager.VERTICAL,false)

            binding.rcCocktails.apply {
                adapter = this@CocktailFragment.adapter
            }
        }
    }

}