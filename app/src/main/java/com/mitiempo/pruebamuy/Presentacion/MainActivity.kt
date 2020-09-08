package com.mitiempo.pruebamuy.Presentacion

import android.os.Bundle
import com.mitiempo.pruebamuy.Base.BaseActivity
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasBundle
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasNavegacionFragmentos
import com.mitiempo.pruebamuy.Utilidades.Extensiones.navegarAFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostrarFragmentListaEmpleados()

    }

    private fun mostrarFragmentListaEmpleados(){
        val fragment = ListaEmpleadosFragment()
            .conEscuchadorEmpleadoSeleccionado {
                mostrarFragmentDetalleEmpleado(it)
            }

        navegarAFragment(R.id.contenedor_vistas,fragment,EtiquetasNavegacionFragmentos.LISTA_EMPLEADOS)

    }

    private fun mostrarFragmentDetalleEmpleado(empleado: Empleado) {

        val bundle = Bundle()
        bundle.putSerializable(EtiquetasBundle.EMPLEADO.traerEtiqueta(),empleado)

        val fragment = DetalleEmpleadoFragment()
        fragment.arguments = bundle

        navegarAFragment(R.id.contenedor_vistas,fragment,EtiquetasNavegacionFragmentos.DETALLE_EMPLEADO)
    }
}
