package com.equipouno.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.equipouno.app.model.User
import com.equipouno.app.repository.UserRepository

class UserModel : ViewModel() {
    private val repository = UserRepository()
    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> get() = _currentUser

    fun getUserByEmail(email: String): LiveData<User?> {
        _currentUser.value = null
        //return repository.getUserByEmail(email)
        return repository.getUserByEmail(email).also {
            it.observeForever { user ->
                _currentUser.value = user
            }
        }
    }

    fun updateUser(user: User) {
        repository.updateUser(user)
        _currentUser.value = user // Update LiveData with the new user
    }
}
