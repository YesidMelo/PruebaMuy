package com.mitiempo.pruebamuy.Utilidades.Constantes

enum class EtiquetasBundle(private val nombreBundle : String) {
    EMPLEADO("empleado");
    fun traerEtiqueta() : String{
        return nombreBundle
    }
}