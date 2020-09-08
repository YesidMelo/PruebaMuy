package com.mitiempo.pruebamuy.Presentacion.Dialogos

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mitiempo.pruebamuy.R
import com.mitiempo.pruebamuy.Utilidades.Constantes.EtiquetasNavegacionFragmentos
import kotlinx.android.synthetic.main.dialogo_generico.*

class DialogoGenerico : DialogFragment()  {

    companion object{
        private var estoyVisible = false
        private var instancia  = DialogoGenerico()
        fun traerInstancia () : DialogoGenerico{
            return instancia
        }
    }

    private var stringResTitulo : Int = R.string.titulo_dialogo
    fun conTitulo(@StringRes rutaTitulo : Int) : DialogoGenerico{
        this.stringResTitulo = rutaTitulo
        return this
    }

    private var stringResMensaje : Int = R.string.mensaje
    fun conMensaje(@StringRes rutaMensaje : Int) : DialogoGenerico {
        this.stringResMensaje = rutaMensaje
        return this
    }

    private var EscuchadorAccionBotonAceptar : (()->Unit) ?= null
    fun conEscuchadorAccionBotonAceptar (EscuchadorAccionBotonAceptar : (()->Unit) ) : DialogoGenerico{
        this.EscuchadorAccionBotonAceptar = EscuchadorAccionBotonAceptar
        return this
    }

    override fun dismiss() {
        try {
            if (fragmentManager == null ){ return }
            estoyVisible = false
            super.dismiss()
            super.dismissAllowingStateLoss()
        } catch (e: Exception) {
            Log.e("Error","",e);
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            if (estoyVisible){ return }
            if(isAdded){ return }
            estoyVisible = true
            super.show(manager, tag)
        } catch (e: Exception) {
            Log.e("Error","",e)
        }
    }

     fun mostrarDialogo(fragmentManager: FragmentManager,etiqueta : String = "dialogo generico"){
         showNow(fragmentManager,etiqueta)
     }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return inflater.inflate(R.layout.dialogo_generico,container, false)
    }

    override fun onStart() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        ponerEscuchadores()
        actualizarDetalleDialogo()
    }

    private fun ponerEscuchadores(){
        button_dialogo.setOnClickListener {
            dismiss()
            EscuchadorAccionBotonAceptar?.invoke()
        }
    }

    private fun actualizarDetalleDialogo(){
        titulo_dialogo.text = context!!.getString(stringResTitulo)
        mensaje_dialogo.text = context!!.getString(stringResMensaje)
    }

}