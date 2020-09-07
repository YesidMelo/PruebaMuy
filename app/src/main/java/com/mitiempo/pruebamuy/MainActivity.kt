package com.mitiempo.pruebamuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ProxyVolley
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ServiciosApi
import com.mitiempo.pruebamuy.DataAccess.Repositorios.RepoEmplados
import com.mitiempo.pruebamuy.Modelos.Compania
import com.mitiempo.pruebamuy.Modelos.ModeloBase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RepoEmplados(this)
            .conEscuchadorExito {
                Log.e("Error","")
            }
            .conEscuchadorFalla {
                Log.e("Error","")
            }
            .consultarListaEmpleados()
    }
}
