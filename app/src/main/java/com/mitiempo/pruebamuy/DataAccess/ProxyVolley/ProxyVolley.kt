package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.mitiempo.pruebamuy.DataAccess.Errores.*
import com.mitiempo.pruebamuy.Utilidades.verificarConexionInternet

class ProxyVolley (private val context: Context) {

    interface ProxyVolleyParcelable

    interface ProxyVolleyServicio{
        fun conMetodo() : MetodoProxyVolley
        fun url(): String
        fun conObjetoAEnviar(objeto : Any?) : ProxyVolleyServicio
        fun traerObjetoAEnviar() : Any?
        fun conClaseARecibir(clase : Class<Any>): ProxyVolleyServicio
        fun traerClaseARecibir(): Class<*>?
    }

    companion object{ private var requestQueue : RequestQueue ?= null }

    private var EscuchadorExito : ((Any?)->Unit)?= null
    fun conEscuchadorExito(EscuchadorExito : ((Any?)->Unit)): ProxyVolley{
        this.EscuchadorExito = EscuchadorExito
        return this
    }

    private var EscuchadorFalla : ((Throwable?)->Unit)?= null
    fun conEscuchadorFalla(EscuchadorFalla : ((Throwable?)->Unit)): ProxyVolley{
        this.EscuchadorFalla = EscuchadorFalla
        return this
    }

    private var servicio : ProxyVolleyServicio ?= null
    fun conServicioAConsultar(servicio : ServiciosApi) : ProxyVolley{
        this.servicio = servicio
        return this
    }

    fun realizarConsulta(){
        context.verificarConexionInternet(::consultarApi) {
            EscuchadorFalla?.invoke(ErrorConexionInternet())
        }
    }

    private fun consultarApi(){
        if(!estanTodosLosParametrosParaConsulta()){ return }
    }

    private fun estanTodosLosParametrosParaConsulta() : Boolean{

        if(EscuchadorFalla == null ){ Log.e("Error","No ha ingresado un escuchador para fallas"); return false }

        var error : Error ?= null
        if(EscuchadorExito == null){
             error = ErrorSinEscuchadorExito()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio == null){
             error = ErrorNoHaIngresadoServicioApi()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio!!.traerObjetoAEnviar() == null){
            error = ErrorUnObjetoAEnviar()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio!!.traerClaseARecibir() == null){
            error = ErrorUnaClaseARecibir()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        return true
    }


}