package com.mitiempo.pruebamuy.DataAccess.Errores

class ErrorConexionInternet: Error("Este dispositivo no tiene conexion a internet")
class ErrorSinEscuchadorExito: Error("No ha ingresado un escuchador para notificaciones exitosas")
class ErrorNoHaIngresadoServicioApi: Error("No ha ingresado un servicio de consulta")
class ErrorUnObjetoAEnviar: Error("No ha ingresado un objeto a enviar")
class ErrorUnaClaseARecibir: Error("No ha ingresado una clase a recibir")