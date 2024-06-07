package com.equipouno.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.equipouno.app.model.User
import com.equipouno.app.repository.UserRepository

class UserModel : ViewModel() {
    private val repository = UserRepository()

    fun getUserByEmail(email: String): LiveData<User?> {
        return repository.getUserByEmail(email)
    }
}
