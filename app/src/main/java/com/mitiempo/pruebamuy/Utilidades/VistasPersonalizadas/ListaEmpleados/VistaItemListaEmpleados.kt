package com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.ListaEmpleados

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import kotlinx.android.synthetic.main.item_lista_trabajador.view.*

class VistaItemListaEmpleados @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_lista_trabajador,this,true)
        ponerEscuchadores()
    }

    private fun ponerEscuchadores(){
        setOnClickListener {
            if(empleado == null ){ return@setOnClickListener }
            EscuchadorEmpleadoSeleccionado?.invoke(empleado!!)
        }
    }

    private var empleado : Empleado?= null
    fun conEmpleado(empleado : Empleado) : VistaItemListaEmpleados {
        this.empleado = empleado
        return this
    }

    private var EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit) ?= null
    fun conEscuchadorEmpleadoSeleccionado(EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit) ) : VistaItemListaEmpleados {
        this.EscuchadorEmpleadoSeleccionado = EscuchadorEmpleadoSeleccionado
        return this
    }

    fun actualizarVista(){
        if(empleado == null ){ return }
        post {
            label_nombre.setText(String.format("%s : %s",context.getString(R.string.nombre_empleado),empleado!!.name))
            mostrarCargo()
            mostrarSalario()
        }
    }

    private fun mostrarCargo(){
        if(empleado!!.position == null ){
            label_cargo.visibility = View.GONE
            return
        }
        label_cargo.setText(String.format("%s : %s",context.getString(R.string.cargo),empleado!!.position))
        label_cargo.visibility = View.VISIBLE
    }
    private fun mostrarSalario(){
        if(empleado!!.wage == null ){
            label_salario.visibility = View.GONE
            return
        }
        label_salario.visibility = View.VISIBLE
        label_salario.setText(String.format("%s : %s",context.getString(R.string.salario),empleado!!.wage!!))
    }
}