package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import com.android.volley.Response
import com.android.volley.VolleyError

class EscuchadorFallaProxyVolley (
    private val servicio : ProxyVolley.ProxyVolleyServicio,
    private val EscuchadorFalla : (Throwable?)->Unit
) : Response.ErrorListener {

    override fun onErrorResponse(error: VolleyError?) {
        EscuchadorFalla.invoke(error)
    }

}