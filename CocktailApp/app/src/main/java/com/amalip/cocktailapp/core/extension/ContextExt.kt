package com.amalip.cocktailapp.core.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
// Saber si estamos conectados o no
val Context.networkInfo: NetworkInfo?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo