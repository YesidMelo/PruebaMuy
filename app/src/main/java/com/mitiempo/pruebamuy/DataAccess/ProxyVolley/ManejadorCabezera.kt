package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

class ManejadorCabezera {

    private var mapaCabezeras = emptyMap<String,String>().toMutableMap()
    init {
        mapaCabezeras["Content-Type"] = "application/json"
    }

    fun adicionarCabezera(llave : String,valor : String) : ManejadorCabezera{
        mapaCabezeras[llave] = valor
        return this
    }

    fun traerCabezeras() : MutableMap<String,String>{
        return mapaCabezeras
    }

}