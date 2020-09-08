package com.mitiempo.pruebamuy.Modelos


class Compania : ModeloBase {

    var company_name : String ?= null
    var address : String ?= null
    var employees : MutableList<Empleado> ?= null

}