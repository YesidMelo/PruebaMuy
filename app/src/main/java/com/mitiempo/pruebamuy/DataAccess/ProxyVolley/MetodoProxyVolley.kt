package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import com.android.volley.Request

enum class MetodoProxyVolley(private val metodo : Int) {
    GET(Request.Method.GET)
    ;

    fun traerMetodoVolley() : Int{
        return metodo
    }
}