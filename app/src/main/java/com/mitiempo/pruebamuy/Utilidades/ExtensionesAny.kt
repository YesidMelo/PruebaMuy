package com.mitiempo.pruebamuy.Utilidades

import com.google.gson.Gson

fun Any.convertirAJSON(): String?{ return Gson().toJson(this) }