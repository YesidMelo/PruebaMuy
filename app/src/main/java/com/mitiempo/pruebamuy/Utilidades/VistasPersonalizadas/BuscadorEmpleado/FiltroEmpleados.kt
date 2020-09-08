package com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.BuscadorEmpleado

import com.mitiempo.pruebamuy.Modelos.Empleado
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FiltroEmpleados( private val listaEmpleados : MutableList<Empleado>) {

    private var listaFiltros = emptyList<((Empleado) -> Boolean)>().toMutableList()
    fun conFiltro(filtro: ((Empleado) -> Boolean)): FiltroEmpleados {
        this.listaFiltros.add(filtro)
        return this
    }

    private var EscuchadorListaFiltrada : ((MutableList<Empleado>)->Unit) ?= null
    fun conEscuchadorListaFiltrada(EscuchadorListaFiltrada : ((MutableList<Empleado>)->Unit)) : FiltroEmpleados{
        this.EscuchadorListaFiltrada = EscuchadorListaFiltrada
        return this
    }

    private val listaFiltrada = emptyList<Empleado>().toMutableList()

    fun filtrarLista(){

        if(listaFiltros.isEmpty()){
            EscuchadorListaFiltrada?.invoke(listaEmpleados)
            return
        }

        GlobalScope.launch {
            listaFiltrada.clear()

            for(empleado in listaEmpleados){

                for(filtro in listaFiltros){

                    if(!filtro(empleado)){ continue }
                    listaFiltrada.add(empleado)

                }
            }

            EscuchadorListaFiltrada?.invoke(listaFiltrada)
        }
    }

}