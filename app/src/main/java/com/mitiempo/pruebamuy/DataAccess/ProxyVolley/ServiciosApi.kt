package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import com.mitiempo.pruebamuy.BuildConfig

enum class ServiciosApi(
    private val complemento : String,
    private val metodo : MetodoProxyVolley
) : ProxyVolley.ProxyVolleyServicio {
    ListaEmpleados("sapardo10/content/master/RH.json",MetodoProxyVolley.GET)
    ;
    private var objetoAEnviar : Any ?= null
    override fun conObjetoAEnviar(objeto: Any?): ProxyVolley.ProxyVolleyServicio {
        this.objetoAEnviar = objeto
        return this
    }

    override fun traerObjetoAEnviar(): Any? {
        return objetoAEnviar
    }

    private var claseARecibir: Class<*> ?= null
    override fun conClaseARecibir(clase: Class<Any>) : ProxyVolley.ProxyVolleyServicio {
        this.claseARecibir = clase
        return this
    }

    override fun traerClaseARecibir(): Class<*> {
        return claseARecibir!!
    }
    ;
    override fun conMetodo(): MetodoProxyVolley {
        return this.metodo
    }

    override fun url(): String {
        return BuildConfig.HOST + complemento
    }


}