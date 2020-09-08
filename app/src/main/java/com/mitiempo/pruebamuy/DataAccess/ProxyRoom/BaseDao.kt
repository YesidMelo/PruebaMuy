package com.mitiempo.pruebamuy.DataAccess.ProxyRoom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao {

    interface RoomParcelable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun <T : RoomParcelable> insertar(objeto : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun <T : RoomParcelable> insertarTodo(listaObjetos : MutableList<T>)

    @Delete
    fun <T : RoomParcelable> borrar(objeto : T)


}