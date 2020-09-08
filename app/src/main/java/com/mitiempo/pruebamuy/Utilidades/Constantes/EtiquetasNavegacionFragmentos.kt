package com.mitiempo.pruebamuy.Utilidades.Constantes

enum class EtiquetasNavegacionFragmentos (private var etiqueta : String?) {
    LISTA_EMPLEADOS(null ),
    DETALLE_EMPLEADO("Detalle empleados")
    ;

    fun traerEtiqueta() : String?{
        return etiqueta
    }
}