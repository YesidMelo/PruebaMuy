package com.mitiempo.pruebamuy.Presentacion

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mitiempo.pruebamuy.DataAccess.Repositorios.RepoEmplados
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import kotlinx.android.synthetic.main.lista_empleados_fragment.*

@SuppressLint("InflateParams")
class ListaEmpleadosFragment  : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_empleados_fragment,null,false)
    }

    private var EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit) ?= null
    fun conEscuchadorEmpleadoSeleccionado (EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit)): ListaEmpleadosFragment{
        this.EscuchadorEmpleadoSeleccionado = EscuchadorEmpleadoSeleccionado
        return this
    }

    override fun onResume() {
        super.onResume()
        cargarListaEmpleados()
    }

    private fun cargarListaEmpleados(){
        if (context == null){ return }

        RepoEmplados(context!!)
            .conEscuchadorExito { empleados ->

                contenedor_lista_empleados
                    .conListaEmpleados(traerListaEmpleadosValido(empleados))
                    .conEscuchadorEmpleadoSeleccionado { EscuchadorEmpleadoSeleccionado?.invoke(it) }
                    .actualizarVista()

            }
            .conEscuchadorFalla {
                Log.e("Error","")
            }
            .consultarListaEmpleados()
    }

    private fun traerListaEmpleadosValido(objeto : Any?) : MutableList<Empleado>{
        return if (objeto == null){
            emptyList<Empleado>().toMutableList()
        }else{
            objeto as MutableList<Empleado>
        }
    }
}