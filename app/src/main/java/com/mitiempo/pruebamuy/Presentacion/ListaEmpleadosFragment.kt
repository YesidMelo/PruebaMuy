package com.mitiempo.pruebamuy.Presentacion

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mitiempo.pruebamuy.DataAccess.Repositorios.RepoEmplados
import com.mitiempo.pruebamuy.Modelos.Compania
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Extensiones.mostrarProgress
import com.mitiempo.pruebamuy.Utilidades.Extensiones.ocultarProgress
import kotlinx.android.synthetic.main.lista_empleados_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        GlobalScope.launch {
            context!!.mostrarProgress()
            RepoEmplados(context!!)
                .conEscuchadorExito { compania ->
                    configurarBuscadorEmpleado(compania)
                    configurarContendorListaEmpleados()
                }
                .conEscuchadorFalla {
                    ManejadorMensajesError(context!!,it).mostrarDialogo()
                }
                .consultarListaEmpleados()

        }

    }

    private fun configurarBuscadorEmpleado( compania: Any? ){

        buscador_empleado
            .conListaEmpleados(traerListaEmpleadosValido(compania))
            .conVistaListaEmpleados(contenedor_lista_empleados)
            .actualizarListaEmpleados()

    }

    private fun configurarContendorListaEmpleados(){

        contenedor_lista_empleados
            .conEscuchadorEmpleadoSeleccionado { EscuchadorEmpleadoSeleccionado?.invoke(it) }
        context!!.ocultarProgress()
    }

    private fun traerListaEmpleadosValido(objeto : Any?) : MutableList<Empleado>{
        return if (objeto == null){
            emptyList<Empleado>().toMutableList()
        }else if( (objeto as Compania).employees == null  ){
            emptyList<Empleado>().toMutableList()
        }else {
            (objeto as Compania).employees!!
        }
    }
}