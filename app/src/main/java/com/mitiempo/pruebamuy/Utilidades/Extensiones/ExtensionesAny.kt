package com.mitiempo.pruebamuy.Utilidades.Extensiones

import com.google.gson.Gson

fun Any.convertirAJSON(): String?{ return Gson().toJson(this) }