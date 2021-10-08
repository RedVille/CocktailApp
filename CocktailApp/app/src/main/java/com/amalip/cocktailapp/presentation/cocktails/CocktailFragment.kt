package com.amalip.cocktailapp.presentation.cocktails

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.amalip.cocktailapp.R
import com.amalip.cocktailapp.core.extension.failure
import com.amalip.cocktailapp.core.extension.observe
import com.amalip.cocktailapp.core.presentation.BaseFragment
import com.amalip.cocktailapp.core.presentation.BaseViewState
import com.amalip.cocktailapp.core.utils.LayoutType
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

    private val adapter: CocktailAdapter by lazy { CocktailAdapter() }
    private val cocktailViewModel by viewModels<CocktailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CocktailViewState.CocktailsReceived -> setUpAdapter(state.cocktails)
        }
    }

    private fun setUpAdapter(cocktails: List<Cocktail>) {
        binding.emptyView.isVisible = cocktails.isEmpty()

        adapter.addData(cocktails)

        binding.rcCocktails.apply {
            isVisible = cocktails.isNotEmpty()
            adapter = this@CocktailFragment.adapter
        }
    }

    override fun setBinding(view: View) {
        binding = CocktailFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        binding.svCocktail.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                cocktailViewModel.doGetCocktailsByName(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cocktailViewModel.doGetCocktailsByName(newText ?: "")
                return true
            }

        })

        binding.fabSwapView.setOnClickListener {
            val newLayout = if (adapter.layoutType == LayoutType.LINEAR) {
                binding.rcCocktails.layoutManager = GridLayoutManager(requireContext(), 3)
                LayoutType.GRID
            } else {
                binding.rcCocktails.layoutManager = LinearLayoutManager(requireContext())
                LayoutType.LINEAR
            }

            adapter.changeView(newLayout)
        }

    }

}