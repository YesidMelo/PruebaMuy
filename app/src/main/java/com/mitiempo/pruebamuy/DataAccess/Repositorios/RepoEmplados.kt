package com.mitiempo.pruebamuy.DataAccess.Repositorios

import android.content.Context
import android.util.Log
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorAlConsultarListaDeUsuariosNuevos
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorAlMomentoDeInsertarUnObjeto
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorSinEscuchadorExito
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorSinEscuchadorFallas
import com.mitiempo.pruebamuy.DataAccess.ProxyRoom.BaseDatos
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ProxyVolley
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ServiciosApi
import com.mitiempo.pruebamuy.Modelos.Compania
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.Modelos.ModeloBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        GlobalScope.launch {

            val servicioConfigurado = ServiciosApi
                .ListaEmpleados
                .conClaseARecibir(Compania::class.java)
                .conObjetoAEnviar(object : ModeloBase {})

            ProxyVolley(context)
                .conEscuchadorExito {
                    actualizaEstadosDeLosEmpleados(it)
                }
                .conEscuchadorFalla (EscuchadorFalla!!)
                .conServicioAConsultar(servicioConfigurado)
                .realizarConsulta()
        }
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

    private fun actualizaEstadosDeLosEmpleados(objetoRespuesta : Any?){
        GlobalScope.launch {
            try {
                val compania = objetoRespuesta as Compania

                if(compania.employees.isNullOrEmpty()){
                    EscuchadorExito?.invoke(compania)
                    return@launch
                }


                val listaEmpleados = BaseDatos
                    .traerInstancia(context)
                    ?.empleadoDao()
                    ?.traerEmpleadosNuevos()

                if (listaEmpleados.isNullOrEmpty()){
                    EscuchadorExito?.invoke(compania)
                    return@launch
                }

                for (empleadoNuevo in listaEmpleados){
                    for (empleadoServidor in compania.employees!!){
                        if (empleadoNuevo.id != empleadoServidor.id!!) { continue }
                        empleadoServidor.esNuevo = true
                    }
                }

                EscuchadorExito?.invoke(compania)

            } catch (e: Exception) {
                Log.e("Error","",e)
                EscuchadorFalla?.invoke(ErrorAlConsultarListaDeUsuariosNuevos())
            }
        }


    }



    fun actualizarEsNuevoEmpleado(empleado: Empleado){

        GlobalScope.launch {
            if(!tengoLosEscuchadores()){ return@launch }

            try {

                BaseDatos
                    .traerInstancia(context)
                    ?.empleadoDao()
                    ?.insertar(empleado)

                EscuchadorExito?.invoke(null)
            } catch (e: Exception) {
                Log.e("Error","",e)
                EscuchadorFalla?.invoke(ErrorAlMomentoDeInsertarUnObjeto())
            }
        }

    }

}