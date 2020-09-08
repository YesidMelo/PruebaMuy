package com.mitiempo.pruebamuy.DataAccess.ProxyRoom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao <T : BaseDao.RoomParcelable> {

    interface RoomParcelable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertar(objeto : T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertarTodo(listaObjetos : MutableList<T>)

    @Delete
    fun borrar(objeto : T)


}