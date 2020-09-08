package com.mitiempo.pruebamuy.Utilidades.Extensiones

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.navegarAFragment(@IdRes idContenedor : Int, fragment : Fragment,tag : String? = null ){
    runOnUiThread {
        supportFragmentManager
            .beginTransaction()
            .replace(idContenedor,fragment)
            .addToBackStack(tag)
            .commit()
    }
}