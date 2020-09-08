package com.mitiempo.pruebamuy.Utilidades.Extensiones

import android.content.Context
import android.net.ConnectivityManager

fun Context.verificarConexionInternet(conectado : (()->Unit),desconectado : (()->Unit)){
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo

    if (networkInfo != null && networkInfo.isConnected){
        conectado.invoke()
        return
    }
    desconectado.invoke()
}