package com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.ListaEmpleados

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import kotlinx.android.synthetic.main.vista_lista_empleados.view.*

class VistaListaEmpleados @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.vista_lista_empleados,this,true)
    }

    private var listaEmpleados = emptyList<Empleado>().toMutableList()
    fun conListaEmpleados(listaEmpleados : MutableList<Empleado>) : VistaListaEmpleados {
        this.listaEmpleados = listaEmpleados
        return this
    }

    private var EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit) ?= null
    fun conEscuchadorEmpleadoSeleccionado(EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit)) : VistaListaEmpleados {
        this.EscuchadorEmpleadoSeleccionado = EscuchadorEmpleadoSeleccionado
       return this
    }

    fun actualizarVista(){
        post {
            AdaptadorListaEmpleados(
                listaEmpleados,
                recycler_lista_empleados
            )
                .conEscuchadorEmpleadoSeleccionado { EscuchadorEmpleadoSeleccionado?.invoke(it) }
                .actualizarLista()
        }
    }
}