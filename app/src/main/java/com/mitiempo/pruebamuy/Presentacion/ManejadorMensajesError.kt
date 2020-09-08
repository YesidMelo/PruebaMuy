package com.mitiempo.pruebamuy.Presentacion

import android.content.Context
import android.util.Log
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorConexionInternet
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Extensiones.mostrarDialogoDetallado
import com.mitiempo.pruebamuy.Utilidades.Extensiones.ocultarProgress

class ManejadorMensajesError(private val context: Context,private val errorGenerado : Throwable?) {

    fun mostrarDialogo(){
        Log.e("Error","",errorGenerado)
        context.ocultarProgress()
        when(errorGenerado){
            is ErrorConexionInternet ->{
                context.mostrarDialogoDetallado(R.string.sin_internet,R.string.este_dispositivo_no_tiene_internet)
            }
            else ->{
                context.mostrarDialogoDetallado(R.string.problema_inesperado,R.string.surgio_un_problema_inesperado)
            }
        }
    }

}