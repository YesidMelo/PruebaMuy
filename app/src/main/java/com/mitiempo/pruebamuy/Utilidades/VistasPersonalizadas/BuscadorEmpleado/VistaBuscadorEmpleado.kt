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

        if (empleado_a_buscar.text!!.toString().isNotEmpty()){
            filtro.conFiltro {
                val validacion = it.name!!.toLowerCase().contains(empleado_a_buscar.text.toString().toLowerCase())
                return@conFiltro validacion
            }
        }

        filtro.filtrarLista()

    }

}