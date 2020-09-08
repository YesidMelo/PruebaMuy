package com.mitiempo.pruebamuy.DataAccess.ProxyRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mitiempo.pruebamuy.DataAccess.ProxyRoom.Daos.EmpleadoDao
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasDB


@Database(
    entities = arrayOf(Empleado::class),
    version = 1
)
abstract class BaseDatos : RoomDatabase(){

    companion object{

        private var baseDatos : BaseDatos ?= null

        fun traerInstancia(context: Context) : BaseDatos?{

            if (baseDatos == null ){
                baseDatos = Room.databaseBuilder(
                    context,
                    BaseDatos::class.java,
                    EtiquetasDB.NombreDB.traerEtiqueta()
                ).build()

            }

            return baseDatos
        }
    }

    abstract fun empleadoDao () : EmpleadoDao

}