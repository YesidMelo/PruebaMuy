package com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.BuscadorEmpleado

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.ListaEmpleados.VistaListaEmpleados
import kotlinx.android.synthetic.main.vista_buscador_empleado.view.*

class VistaBuscadorEmpleado @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.vista_buscador_empleado,this,true)
        ponerEscuchadores()
    }

    private fun ponerEscuchadores() {


        boton_buscar.setOnClickListener {
            actualizarListaEmpleados()
        }
    }

    private var listaEmpleados = emptyList<Empleado>().toMutableList()
    fun conListaEmpleados(listaEmpleados : MutableList<Empleado>) : VistaBuscadorEmpleado {
        this.listaEmpleados = listaEmpleados
        return this
    }

    private var vistaListaEmpleados : VistaListaEmpleados ?= null
    fun conVistaListaEmpleados(vistaListaEmpleados : VistaListaEmpleados) : VistaBuscadorEmpleado{
        this.vistaListaEmpleados = vistaListaEmpleados
        return this
    }


    fun actualizarListaEmpleados(){

        val filtro = FiltroEmpleados(listaEmpleados)
            .conEscuchadorListaFiltrada {
                vistaListaEmpleados
                    ?.conListaEmpleados(it)
                    ?.actualizarVista()
            }

        adicionarFiltroPalabra(filtro)
        adicionarFiltroSalario(filtro)
        adicionarFiltroNuevos(filtro)

        filtro.filtrarLista()

    }


    private fun adicionarFiltroPalabra(filtro: FiltroEmpleados) {
        if (empleado_a_buscar.text!!.toString().isNotEmpty()){
            filtro.conFiltro {
                val validacion = it.name!!.toLowerCase().contains(empleado_a_buscar.text.toString().toLowerCase())
                return@conFiltro validacion
            }
        }
    }

    private fun adicionarFiltroSalario(filtro: FiltroEmpleados) {

        if(radio_button_menor_a_mayor.isChecked){
            filtro.ordernarSalarioDeMayorAMenor(false)
            return
        }

        if (radio_button_mayor_a_menor.isChecked) {
            filtro.ordernarSalarioDeMayorAMenor(true)
        }
    }

    private fun adicionarFiltroNuevos(filtro: FiltroEmpleados) {

        if(check_empleados_nuevos.isChecked) {
            filtro.conFiltro { return@conFiltro it.esNuevo == true }
        }

    }

}