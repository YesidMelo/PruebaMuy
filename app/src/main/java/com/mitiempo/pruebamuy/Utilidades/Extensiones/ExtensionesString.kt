package com.mitiempo.pruebamuy.Utilidades.Extensiones

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mitiempo.pruebamuy.DataAccess.Errores.ErrorProblemaAlCastearElObjetoDelServidor

fun String.convertirAObjeto(clase : Class<*>,escuchadorFalla : (Throwable?)->Unit) : Any?{
    return try {
        Gson().fromJson(this,clase)
    }catch (e: Exception){
        convertirAListaObjetos(clase,escuchadorFalla)
    }
}

fun String.convertirAListaObjetos(clase : Class<*>,escuchadorFalla : (Throwable?)->Unit) : MutableList<Any> ?{
    try {
        val gson = Gson()

        val tipo = object : TypeToken<MutableList<Any>>(){}.type
        val listaCruda = gson.fromJson(this,tipo) as MutableList<*>
        val listaCasteada = emptyList<Any>().toMutableList()

        for(objetoCrudo in listaCruda){

            val objJson = gson.toJson(objetoCrudo)
            val objetoCasteado = gson.fromJson(objJson,clase)
            listaCasteada.add(objetoCasteado)

        }

        return listaCasteada
    }catch (e : Exception){
        Log.e("Error","",e)
        escuchadorFalla.invoke(ErrorProblemaAlCastearElObjetoDelServidor())
        return null
    }
}