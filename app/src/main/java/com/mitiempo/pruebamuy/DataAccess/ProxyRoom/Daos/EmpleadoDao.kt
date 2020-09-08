package com.mitiempo.pruebamuy.DataAccess.ProxyRoom.Daos

import androidx.room.Dao
import androidx.room.Query
import com.mitiempo.pruebamuy.DataAccess.ProxyRoom.BaseDao
import com.mitiempo.pruebamuy.Modelos.Empleado

@Dao
interface EmpleadoDao : BaseDao {

    @Query("select * from Empleado where Empleado.esNuevo == 1 ")
    fun traerEmpleadosNuevos() : MutableList<Empleado>
}