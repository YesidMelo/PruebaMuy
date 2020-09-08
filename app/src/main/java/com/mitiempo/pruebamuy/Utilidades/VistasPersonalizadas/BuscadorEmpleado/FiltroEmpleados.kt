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

    private var organizoSalarioDeMayorAMenor = false
    fun ordernarSalarioDeMayorAMenor(organizoSalarioDeMayorAMenor : Boolean = false) : FiltroEmpleados{
        this.organizoSalarioDeMayorAMenor = organizoSalarioDeMayorAMenor
        return this
    }

    private val listaFiltrada = emptyList<Empleado>().toMutableList()

    fun filtrarLista(){

        GlobalScope.launch {

            listaFiltrada.clear()
            aplicarFiltros()
            reordenarEmpleadosPorSalario()
            EscuchadorListaFiltrada?.invoke(listaFiltrada)

        }
    }

    private fun aplicarFiltros(){

        if (listaFiltros.isEmpty()){

            for(empleado in listaEmpleados){
                listaFiltrada.add(empleado)
            }

            return
        }


        for(empleado in listaEmpleados){

            for(filtro in listaFiltros){

                if(!filtro(empleado)){ continue }
                listaFiltrada.add(empleado)

            }
        }
    }

    private fun reordenarEmpleadosPorSalario(){
        if (listaFiltrada.size <= 1){ return }

        val listaReordenada = emptyList<Empleado>().toMutableList()
        listaFiltrada.forEach {
            listaReordenada.add(it)
        }

        for(contador1 in 0 until listaReordenada.size - 1){
            for (contador2 in contador1 until listaReordenada.size){
                organizarMayorAMenor(contador1,contador2,listaReordenada)
                organizarMenorMayor(contador1,contador2,listaReordenada)
            }
        }

        listaFiltrada.clear()
        listaReordenada.forEach {
            listaFiltrada.add(it)
        }
    }

    private fun organizarMayorAMenor(posicion1 : Int, posicion2: Int, lista : MutableList<Empleado>){
        if(!organizoSalarioDeMayorAMenor){ return }
        if (lista[posicion1].wage!!.toDouble() > lista[posicion2].wage!!.toDouble()) { return }

        val tmp = lista[posicion1]
        lista[posicion1] = lista[posicion2]
        lista[posicion2] = tmp

    }

    private fun organizarMenorMayor(posicion1 : Int, posicion2: Int, lista : MutableList<Empleado>){
        if(organizoSalarioDeMayorAMenor){ return }
        if (lista[posicion1].wage!!.toDouble() < lista[posicion2].wage!!.toDouble()) { return }

        val tmp = lista[posicion1]
        lista[posicion1] = lista[posicion2]
        lista[posicion2] = tmp

    }



}