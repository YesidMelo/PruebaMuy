package com.mitiempo.pruebamuy.Presentacion.Dialogos

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mitiempo.pruebamuy.R

class DialogoProgress : DialogFragment() {

    companion object{
        private var estoyVisible = false
        private var instancia : DialogoProgress ?= null

        fun traerInstancia() : DialogoProgress{
            if (instancia == null ){
                instancia = DialogoProgress()
            }
            return instancia!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.progress_personalizado,container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
    }

    override fun dismiss() {
        try {
            estoyVisible = false
            if (fragmentManager == null ){ return }
            super.dismiss()
            super.dismissAllowingStateLoss()
        } catch (e: Exception) {
            Log.e("Error","",e)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (estoyVisible){ return }
        if (isAdded){ return }
        estoyVisible = true
        super.show(manager, tag)
    }

}