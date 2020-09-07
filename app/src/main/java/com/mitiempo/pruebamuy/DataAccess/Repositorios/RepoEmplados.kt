package com.mitiempo.pruebamuy.DataAccess.Repositorios

import android.content.Context
import android.util.Log
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorSinEscuchadorExito
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorSinEscuchadorFallas
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ProxyVolley
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ServiciosApi
import com.mitiempo.pruebamuy.Modelos.Compania
import com.mitiempo.pruebamuy.Modelos.ModeloBase

class RepoEmplados(private val context: Context) {

    private var EscuchadorExito : ((Any?)->Unit) ?= null
    fun conEscuchadorExito(EscuchadorExito : ((Any?)->Unit)) : RepoEmplados{
        this.EscuchadorExito = EscuchadorExito
        return this
    }

    private var EscuchadorFalla : ((Throwable?)->Unit) ?= null
    fun conEscuchadorFalla(EscuchadorFalla : ((Throwable?)->Unit)) : RepoEmplados{
        this.EscuchadorFalla = EscuchadorFalla
        return this
    }

    fun consultarListaEmpleados(){

        if (!tengoLosEscuchadores()){ return }

        val servicioConfigurado = ServiciosApi
            .ListaEmpleados
            .conClaseARecibir(Compania::class.java)
            .conObjetoAEnviar(object : ModeloBase {})

        ProxyVolley(context)
            .conEscuchadorExito (EscuchadorExito!!)
            .conEscuchadorFalla (EscuchadorFalla!!)
            .conServicioAConsultar(servicioConfigurado)
            .realizarConsulta()
    }

    private fun tengoLosEscuchadores() : Boolean{
        if(EscuchadorExito == null ){
            Log.e("Error","",ErrorSinEscuchadorExito())
            return false
        }
        if(EscuchadorFalla == null ){
            Log.e("Error","",ErrorSinEscuchadorFallas())

            return false
        }
        return true
    }

}