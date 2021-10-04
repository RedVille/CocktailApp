package com.amalip.cocktailapp.core.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amalip.cocktailapp.core.exception.Failure

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), OnFailure {

    val navController by lazy { findNavController() }
    val baseActivity by lazy { requireActivity() as BaseActivity }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.setUpNavigation(navController) // le asigna el navbar

        setBinding(view) // setea el binding de la vista que se quiere mostrar
    }

    abstract fun setBinding(view: View)

    //region progress
    // mostrar o no el Loading xml
    open fun onViewStateChanged(state: BaseViewState?) {
        when(state) {
            // llama el showLoader que muestra el xml del progress dependiendo de si se envía un true o false hasta el main activity
            BaseViewState.ShowLoading -> showLoader(true)
            BaseViewState.HideLoading -> showLoader(false)
        }
    }

    open fun showLoader(show: Boolean) = baseActivity.showProgress(show)
    //endregion

    //region failures
    fun showToast(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    // dependiendo del fallo se hace algo
    override fun handleFailure(failure: Failure?) {
        when(failure) {
            is Failure.DatabaseError -> {

            }
            is Failure.FeatureFailure -> {

            }
            is Failure.NetworkConnection -> {

            }
            is Failure.ServerError -> {
                showToast(failure.serverMessage ?: "")
            }
            is Failure.Unauthorized -> {

            }
            null -> {

            }
        }
    }
    //endregion

}