package com.equipouno.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // Inicialización de Firebase si es necesario
}
