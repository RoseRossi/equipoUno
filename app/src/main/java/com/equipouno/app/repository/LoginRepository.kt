package com.equipouno.app.repository

import com.equipouno.app.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    fun registerUser(newUser: User, isRegisterComplete: (Boolean)->Unit){

        firebaseAuth.createUserWithEmailAndPassword(newUser.email,newUser.password)
            .addOnCompleteListener {
                    if (it.isSuccessful) {

                        val userCollection = db.collection("person")
                        userCollection.add(newUser)
                            .addOnSuccessListener {
                                isRegisterComplete(true)
                            }
                            .addOnFailureListener { e ->
                                isRegisterComplete(false)
                            }
                    } else {
                        isRegisterComplete(false)
                    }
            }
    }
}