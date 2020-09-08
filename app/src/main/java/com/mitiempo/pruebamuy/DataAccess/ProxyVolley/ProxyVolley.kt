package com.mitiempo.pruebamuy.DataAccess.ProxyVolley

import android.content.Context
import android.util.Log
import com.android.volley.NetworkResponse
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mitiempo.pruebamuy.DataAccess.Errores.*
import com.mitiempo.pruebamuy.Utilidades.Extensiones.convertirAJSON
import com.mitiempo.pruebamuy.Utilidades.Extensiones.verificarConexionInternet

class ProxyVolley (private val context: Context) {

    interface ProxyVolleyParcelable

    interface ProxyVolleyServicio{
        fun traerMetodo() : MetodoProxyVolley
        fun url(): String
        fun conObjetoAEnviar(objeto : Any?) : ProxyVolleyServicio
        fun traerObjetoAEnviar() : Any?
        fun conClaseARecibir(clase : Class<*>): ProxyVolleyServicio
        fun traerClaseARecibir(): Class<*>?
    }

    companion object{ private var requestQueue : RequestQueue ?= null }

    private var EscuchadorExito : ((Any?)->Unit)?= null
    fun conEscuchadorExito(EscuchadorExito : ((Any?)->Unit)): ProxyVolley{
        this.EscuchadorExito = EscuchadorExito
        return this
    }

    private var EscuchadorFalla : ((Throwable?)->Unit)?= null
    fun conEscuchadorFalla(EscuchadorFalla : ((Throwable?)->Unit)): ProxyVolley{
        this.EscuchadorFalla = EscuchadorFalla
        return this
    }

    private var servicio : ProxyVolleyServicio ?= null
    fun conServicioAConsultar(servicio : ServiciosApi) : ProxyVolley{
        this.servicio = servicio
        return this
    }

    private var manejadorCabezera : ManejadorCabezera = ManejadorCabezera()
    fun conCabezeras(manejadorCabezera : ManejadorCabezera) : ProxyVolley{
        this.manejadorCabezera = manejadorCabezera
        return this
    }


    private var EscuchadorNetworkResponse : ((NetworkResponse?)->Unit) ?= null
    fun conEscuchadorNetworkResponse (EscuchadorNetworkResponse : ((NetworkResponse?)->Unit)): ProxyVolley {
        this.EscuchadorNetworkResponse = EscuchadorNetworkResponse
        return this
    }

    fun realizarConsulta(){
        context.verificarConexionInternet(::consultarApi) { EscuchadorFalla?.invoke(
            ErrorConexionInternet()
        ) }
    }

    private fun consultarApi(){

        if(!estanTodosLosParametrosParaConsulta()){ return }
        NukeSSLCerts.nuke()
        val stringRequest = generarStringRequest()
        requestQueue = Volley.newRequestQueue(context)
        requestQueue?.cache?.clear()
        requestQueue!!.add(stringRequest)

    }

    private fun estanTodosLosParametrosParaConsulta() : Boolean{

        if(EscuchadorFalla == null ){ Log.e("Error","No ha ingresado un escuchador para fallas"); return false }

        var error : Error ?= null
        if(EscuchadorExito == null){
             error = ErrorSinEscuchadorExito()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio == null){
             error = ErrorNoHaIngresadoServicioApi()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio!!.traerObjetoAEnviar() == null){
            error = ErrorUnObjetoAEnviar()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        if(servicio!!.traerClaseARecibir() == null){
            error = ErrorUnaClaseARecibir()
            Log.e("Error","",error)
            EscuchadorFalla?.invoke(error)
            return false
        }

        return true
    }

    private fun generarStringRequest() : StringRequest{
        val escuchadorExito = EscuchadorCasoExitosoProxyVolley(servicio!!,EscuchadorExito!!,EscuchadorFalla!!)
        return object : StringRequest(
            servicio!!.traerMetodo().traerMetodoVolley(),
            servicio!!.url(),
            escuchadorExito,
            EscuchadorFallaProxyVolley(servicio!!,EscuchadorFalla!!)
        )
        {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                return servicio!!.traerObjetoAEnviar()!!.convertirAJSON()!!.toByteArray()
            }

            override fun getHeaders(): MutableMap<String, String> {
                return manejadorCabezera.traerCabezeras()
            }

            override fun parseNetworkResponse(response: NetworkResponse?): Response<String> {
                EscuchadorNetworkResponse?.invoke(response)
                return super.parseNetworkResponse(response)
            }

            override fun parseNetworkError(volleyError: VolleyError?): VolleyError {
                EscuchadorFalla?.invoke(volleyError)
                return super.parseNetworkError(volleyError)
            }

        }
    }

}