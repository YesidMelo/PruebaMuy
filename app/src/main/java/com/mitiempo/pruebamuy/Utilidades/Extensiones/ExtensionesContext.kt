package com.mitiempo.pruebamuy.Utilidades.Extensiones

import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.mitiempo.pruebamuy.Presentacion.Dialogos.DialogoProgress

fun Context.verificarConexionInternet(conectado : (()->Unit),desconectado : (()->Unit)){
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo

    if (networkInfo != null && networkInfo.isConnected){
        conectado.invoke()
        return
    }
    desconectado.invoke()
}

fun Context.mostrarProgress(){
    if (!esUnContextoValidoParaMostrarMensaje(this)){ return }
    if(this !is AppCompatActivity){
        traerContextValido(this).mostrarProgress()
        return
    }

    runOnUiThread {
        DialogoProgress
            .traerInstancia()
            .show(this.supportFragmentManager,"progress")
    }

}

private fun esUnContextoValidoParaMostrarMensaje(contex: Context) : Boolean{
    return contex is AppCompatActivity || contex is ContextWrapper
}

private fun traerContextValido(contex : Context) : Context{
    var finalContex : Context = contex
    while((finalContex !is AppCompatActivity) && (finalContex is ContextWrapper)){
        finalContex = (finalContex as ContextWrapper).baseContext
    }
    return finalContex
}

fun Context.ocultarProgress(){
    if(this !is AppCompatActivity){ return }
    runOnUiThread {
        DialogoProgress
            .traerInstancia()
            .dismiss()
    }
}