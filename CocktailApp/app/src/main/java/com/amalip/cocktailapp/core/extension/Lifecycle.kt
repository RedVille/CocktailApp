package com.amalip.cocktailapp.core.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.amalip.cocktailapp.core.exception.Failure

/**
 * Created by Amalip on 8/21/2021.
 */

// observadores de los datos utilizados en el ViewModel
// Función de extención

// observar
fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))
// fallos
fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer(body))
// remover
fun <T> LifecycleOwner.removeObservers(liveDataList: List<T>) =
    liveDataList.forEach { (it as LiveData<*>).removeObservers(this) }