package com.equipouno.app.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.equipouno.app.R
import android.os.Handler
import com.equipouno.app.view.MainActivity

class Splash : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 1000 // Tiempo de duración del splash screen en milisegundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Temporizador para mostrar la actividad principal después del splash screen
        Handler().postDelayed({
            // Iniciar la actividad principal
            startActivity(Intent(this, MainActivity::class.java))
            // Cerrar la actividad del splash screen
            finish()
        }, SPLASH_TIME_OUT)
    }
}