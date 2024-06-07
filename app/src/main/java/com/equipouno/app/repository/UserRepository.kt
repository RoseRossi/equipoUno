package com.equipouno.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.equipouno.app.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepository {
    private val db = Firebase.firestore

    fun getUserByEmail(email: String): LiveData<User?> {
        val userLiveData = MutableLiveData<User?>()

        val userCollection = db.collection("person")
        userCollection.whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0]
                    val user = document.toObject(User::class.java)
                    userLiveData.postValue(user)
                } else {
                    userLiveData.postValue(null)
                }
            }
            .addOnFailureListener { exception ->
                userLiveData.postValue(null)
            }

        return userLiveData
    }
}

