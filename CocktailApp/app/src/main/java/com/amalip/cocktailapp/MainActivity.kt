package com.amalip.cocktailapp

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.amalip.cocktailapp.core.presentation.BaseActivity
import com.amalip.cocktailapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    // se lo trae del BaseActivity

    // binding del activityMain
    private lateinit var binding: ActivityMainBinding
    // los id que bindea están en el activity_main
    override fun layoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
    }

    override val fragmentContainer: FragmentContainerView
        get() = binding.fcv

    // se engarga de la navegación entre componentes
    override fun setUpNavigation(navController: NavController) =
        NavigationUI.setupWithNavController(binding.bnvMain, navController)

    // bindea y muestra el progress bar cuando se llame
    override fun showProgress(show: Boolean) {
        binding.progressView.isVisible = show
    }

    // bindea el contenido de la vista
    override fun setBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId())
    }


}