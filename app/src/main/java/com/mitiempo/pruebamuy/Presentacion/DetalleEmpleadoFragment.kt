package com.mitiempo.pruebamuy.Presentacion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mitiempo.pruebamuy.DataAccess.Repositorios.RepoEmplados
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasBundle
import com.mitiempo.pruebamuy.Utilidades.Extensiones.mostrarProgress
import com.mitiempo.pruebamuy.Utilidades.Extensiones.ocultarProgress
import kotlinx.android.synthetic.main.detalle_empleado_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetalleEmpleadoFragment : Fragment() {

    private var empleado : Empleado ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        empleado = arguments!!.get(EtiquetasBundle.EMPLEADO.traerEtiqueta()) as? Empleado
        return inflater.inflate(R.layout.detalle_empleado_fragment,container,false)
    }

    override fun onResume() {
        super.onResume()
        mostrarDetalleEmpleado()
        ponerEscuchadores()
    }


    private fun mostrarDetalleEmpleado() {

        if (empleado == null ){ return }

        label_nombre_detalle.post {

            label_nombre_detalle.text = String.format(" %s : %s ",context!!.getString(R.string.nombre_empleado),empleado!!.name)
            label_posicion.text = String.format(" %s : %s ",context!!.getString(R.string.cargo),empleado!!.position)
            label_salario.text = String.format(" %s : $%s ",context!!.getString(R.string.salario),empleado!!.wage)
            label_es_nuevo.text = String.format(" %s : %s",context!!.getString(R.string.es_nuevo),traerRespuestaAEsNuevo())
            mostrarSubalternos()

        }

    }

    private fun traerRespuestaAEsNuevo() : String{
        if (empleado!!.esNuevo == null || empleado!!.esNuevo == false){
            return context!!.getString(R.string.no)
        }
        return context!!.getString(R.string.si)
    }

    private fun mostrarSubalternos() {
        lista_empleados_detalle
            .conListaEmpleados(empleado!!.employees!!)
            .actualizarVista()
    }

    private fun ponerEscuchadores(){
        label_es_nuevo.setOnClickListener {
            if (empleado!!.esNuevo == null || !empleado!!.esNuevo!! ){
                empleado!!.esNuevo = true
                actualizarEstadoEmpleado()
                return@setOnClickListener
            }
            empleado!!.esNuevo = false
            actualizarEstadoEmpleado()
        }
    }

    private fun actualizarEstadoEmpleado(){
        GlobalScope.launch {
            RepoEmplados(context!!)
                .conEscuchadorExito {
                    mostrarDetalleEmpleado()
                }
                .conEscuchadorFalla {
                    ManejadorMensajesError(context!!,it).mostrarDialogo()
                }
                .actualizarEsNuevoEmpleado(empleado!!)
        }

    }

}