package com.mitiempo.pruebamuy.Presentacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasBundle
import kotlinx.android.synthetic.main.detalle_empleado_fragment.*

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
    }


    private fun mostrarDetalleEmpleado() {
        if (empleado == null ){ return }
        label_nombre_detalle.text = String.format(" %s : %s ",context!!.getString(R.string.nombre_empleado),empleado!!.name)
        label_posicion.text = String.format(" %s : %s ",context!!.getString(R.string.cargo),empleado!!.position)
        label_salario.text = String.format(" %s : $%s ",context!!.getString(R.string.salario),empleado!!.wage)
        label_es_nuevo.text = String.format(" %s : %s",context!!.getString(R.string.es_nuevo),traerRespuestaAEsNuevo())
        mostrarSubalternos()
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

}