package com.mitiempo.pruebamuy.Base

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onBackPressed() {
        Log.e("Error", " numero fragments : ${supportFragmentManager.backStackEntryCount}");
        if (supportFragmentManager.backStackEntryCount == 1){
            finish()
            return
        }

        super.onBackPressed()
    }
}