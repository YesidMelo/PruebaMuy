package com.mitiempo.pruebamuy.Utilidades.Constantes

enum class EtiquetasDB(private val etiqueta : String) {
    NombreDB("PruebaMuy");
    fun traerEtiqueta() : String{
        return etiqueta
    }
}