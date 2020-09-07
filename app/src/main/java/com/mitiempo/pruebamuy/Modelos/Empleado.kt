package com.mitiempo.pruebamuy.Modelos

class Empleado : ModeloBase {

    var id :Int  ?= null
    var name :String  ?= null
    var position :String  ?= null
    var wage :String  ?= null
    var employees :MutableList<Empleado>  ?= null
}