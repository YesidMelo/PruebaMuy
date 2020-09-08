package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import com.android.volley.Response
import com.mitiempo.pruebamuy.Utilidades.Extensiones.convertirAObjeto

class EscuchadorCasoExitosoProxyVolley(
    private val serviciosApi: ProxyVolley.ProxyVolleyServicio,
    private val EscuchadorTransformacionExito : ((Any?)->Unit),
    private val EscuchadorFalla : ((Throwable?)->Unit)
) : Response.Listener<String> {

    override fun onResponse(response: String?) {
        val objeto = response?.convertirAObjeto(serviciosApi.traerClaseARecibir()!!,EscuchadorFalla)
        EscuchadorTransformacionExito.invoke(objeto)
    }
}