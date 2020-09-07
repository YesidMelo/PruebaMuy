package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import android.content.Context
import com.android.volley.RequestQueue

class ProxyVolley (private val context: Context) {

    interface ProxyVolleyParcelable

    interface ProxyVolleyServicio{
        fun conMetodo() : MetodoProxyVolley
        fun url(): String
        fun conObjetoAEnviar(objeto : Any?) : ProxyVolleyServicio
        fun traerObjetoAEnviar() : Any?
        fun conClaseARecibir(clase : Class<Any>): ProxyVolleyServicio
        fun traerClaseARecibir(): Class<*>
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

    }


}