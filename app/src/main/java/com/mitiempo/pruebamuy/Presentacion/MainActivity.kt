package com.mitiempo.pruebamuy.Presentacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mitiempo.pruebamuy.DataAccess.Repositorios.RepoEmplados
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Extensiones.navegarAFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = ListaEmpleadosFragment()
            .conEscuchadorEmpleadoSeleccionado {

            }

        navegarAFragment(R.id.contenedor_vistas,fragment)
    }
}
