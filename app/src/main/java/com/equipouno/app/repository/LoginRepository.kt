package com.equipouno.app.repository

import com.google.firebase.auth.FirebaseAuth

class LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, pass:String, isRegisterComplete: (Boolean)->Unit){
        if(email.isEmpty() && pass.isEmpty()){
            return isRegisterComplete(false)
        }

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
            .addOnCompleteListener {
                    if (it.isSuccessful) {
                        isRegisterComplete(true)
                    } else {
                        isRegisterComplete(false)
                    }
            }
    }
}