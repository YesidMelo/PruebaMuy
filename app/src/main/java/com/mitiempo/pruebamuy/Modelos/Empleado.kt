package com.mitiempo.pruebamuy.Modelos

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Empleado : ModeloBase {

    @PrimaryKey var id :Int  ?= null
    @Ignore var name :String  ?= null
    @Ignore var position :String  ?= null
    @Ignore var wage :String  ?= null

    var esNuevo : Boolean ?= false

    @Ignore var employees :MutableList<Empleado>  ?= null
}