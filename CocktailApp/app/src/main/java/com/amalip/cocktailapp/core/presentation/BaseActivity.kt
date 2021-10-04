package com.amalip.cocktailapp.core.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController

abstract class BaseActivity : AppCompatActivity() {

    abstract fun layoutId(): Int // pasar el id del layout
    abstract val fragmentContainer: FragmentContainerView // pasar el contenedor

    abstract fun setUpNavigation(navController: NavController)

    abstract fun showProgress(show: Boolean)

    abstract fun setBinding()

}