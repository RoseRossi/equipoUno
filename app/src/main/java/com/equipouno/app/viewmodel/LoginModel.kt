package com.equipouno.app.viewmodel

import androidx.lifecycle.ViewModel
import com.equipouno.app.model.User
import com.equipouno.app.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth

class LoginModel :  ViewModel() {
    private val repository = LoginRepository()

    fun registerUser(newUser: User, isRegister: (Boolean) -> Unit) {
        repository.registerUser(newUser) { response ->
            isRegister(response)
        }
    }

    fun loginUser(email: String, pass: String, isLogin: (Boolean) -> Unit) {

        if (email.isEmpty() && pass.isEmpty()) {
            return isLogin(false)
        }

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    isLogin(true)
                } else {
                    isLogin(false)
                }
            }
    }

    fun sesion(email: String?, isEnableView: (Boolean) -> Unit) {
        if (email != null) {
            isEnableView(true)
        } else {
            isEnableView(false)
        }
    }
}