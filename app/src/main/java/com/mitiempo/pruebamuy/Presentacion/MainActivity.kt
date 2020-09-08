package com.mitiempo.pruebamuy.Presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasBundle
import com.mitiempo.pruebamuy.Utilidades.Extensiones.navegarAFragment

class MainActivity : AppCompatActivity() {

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

        navegarAFragment(R.id.contenedor_vistas,fragment)

    }

    private fun mostrarFragmentDetalleEmpleado(empleado: Empleado) {

        val bundle = Bundle()
        bundle.putSerializable(EtiquetasBundle.EMPLEADO.traerEtiqueta(),empleado)

        val fragment = DetalleEmpleadoFragment()
        fragment.arguments = bundle

        navegarAFragment(R.id.contenedor_vistas,fragment)
    }
}
