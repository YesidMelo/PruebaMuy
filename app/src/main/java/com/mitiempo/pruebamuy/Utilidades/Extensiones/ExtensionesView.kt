package com.mitiempo.pruebamuy.Utilidades.Extensiones

import android.animation.AnimatorInflater
import android.view.View
import com.mitiempo.pruebamuy.R

fun View.mostrarConAnimacion(){
    post {
        visibility = View.VISIBLE
        val set = AnimatorInflater.loadAnimator(context,R.animator.fade_in)
        set.setTarget(this)
        set.start()
    }

}

fun View.ocultarConAnimacion(){
    post {
        visibility = View.GONE
        val set = AnimatorInflater.loadAnimator(context,R.animator.fade_out)
        set.setTarget(this)
        set.start()
    }
}