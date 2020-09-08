package com.mitiempo.pruebamuy.Utilidades.Extensiones

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mitiempo.pruebamuy.R

fun AppCompatActivity.navegarAFragment(@IdRes idContenedor : Int, fragment : Fragment,tag : String? = null ){
    runOnUiThread {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.animator.fade_in,R.animator.fade_out,R.animator.fade_in,R.animator.fade_out)
            .replace(idContenedor,fragment)
            .addToBackStack(tag)
            .commit()
    }
}

