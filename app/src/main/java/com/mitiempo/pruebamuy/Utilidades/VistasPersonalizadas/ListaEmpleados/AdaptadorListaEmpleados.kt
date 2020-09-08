package com.mitiempo.pruebamuy.Utilidades.VistasPersonalizadas.ListaEmpleados

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitiempo.pruebamuy.Modelos.Empleado
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Extensiones.mostrarConAnimacion

class AdaptadorListaEmpleados(
    private val listaEmpleados : MutableList<Empleado>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdaptadorListaEmpleados.ItemRecyclerEmpleados>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecyclerEmpleados {
        val vista = LayoutInflater.from(recyclerView.context).inflate(R.layout.item_recycler_lista_trabajadores,null,false)
        vista.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT)
        return ItemRecyclerEmpleados(vista)
    }

    override fun getItemCount(): Int {
        return listaEmpleados.size
    }

    override fun onBindViewHolder(holder: ItemRecyclerEmpleados, position: Int) {
        (holder.vista.findViewById(R.id.item_empleado) as VistaItemListaEmpleados)
            .conEmpleado(listaEmpleados[position])
            .conEscuchadorEmpleadoSeleccionado(EscuchadorEmpleadoSeleccionado!!)
            .actualizarVista()
    }

    private var EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit) ?= null
    fun conEscuchadorEmpleadoSeleccionado(EscuchadorEmpleadoSeleccionado : ((Empleado)->Unit)) : AdaptadorListaEmpleados {
        this.EscuchadorEmpleadoSeleccionado = EscuchadorEmpleadoSeleccionado
        return this
    }

    inner class ItemRecyclerEmpleados(val vista : View) : RecyclerView.ViewHolder(vista)

    fun actualizarLista(){
        recyclerView.post {
            recyclerView.adapter = null
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = this
            recyclerView.mostrarConAnimacion()
        }
    }
}