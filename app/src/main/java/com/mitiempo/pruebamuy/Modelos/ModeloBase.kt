package com.mitiempo.pruebamuy.Modelos

import com.mitiempo.pruebamuy.DataAccess.ProxyRoom.BaseDao
import com.mitiempo.pruebamuy.DataAccess.ProxyVolley.ProxyVolley
import java.io.Serializable

interface ModeloBase :
    ProxyVolley.ProxyVolleyParcelable,
    Serializable,
    BaseDao.RoomParcelable
{}