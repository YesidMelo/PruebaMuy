package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import com.mitiempo.pruebamuy.BuildConfig

enum class ServiciosApi(
    private val complemento : String,
    private val metodo : MetodoProxyVolley
) : ProxyVolley.ProxyVolleyServicio {
    ListaEmpleados("sapardo10/content/master/RH.json",MetodoProxyVolley.GET)
    ;
    private var objetoAEnviar : Any ?= null
    override fun conObjetoAEnviar(objeto: Any?): ServiciosApi {
        this.objetoAEnviar = objeto
        return this
    }

    override fun traerObjetoAEnviar(): Any? {
        return objetoAEnviar
    }

    private var claseARecibir: Class<*> ?= null
    override fun conClaseARecibir(clase: Class<*>) : ServiciosApi {
        this.claseARecibir = clase
        return this
    }

    override fun traerClaseARecibir(): Class<*>? {
        return claseARecibir!!
    }
    ;
    override fun traerMetodo(): MetodoProxyVolley {
        return this.metodo
    }

    override fun url(): String {
        return BuildConfig.HOST + complemento
    }


}