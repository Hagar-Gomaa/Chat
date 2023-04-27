package com.example.chat.ui.login

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chat.UserProvider
import com.example.chat.database.FireStoreUtils
import com.example.chat.ui.model.User
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var showLoading = ObservableField<Boolean>()
    private var emailError = ObservableField<Boolean>(true)
    private var passwordError = ObservableField<Boolean>()

    private val auth = FirebaseAuth.getInstance()
    var loginNavigator: LoginNavigator? = null

    fun login() {
        auth.signInWithEmailAndPassword(email.get().toString(), password.get().toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.e("user", user?.uid.toString())
                    loginNavigator?.goToHome()
                } else {
                    Log.e("error", "${task.exception!!.message.toString()}-${task.exception!!.localizedMessage}")
                }
            }
    }

    private fun getUserFromDb(uid: String) {
        FireStoreUtils.getUserFromDb(uid)
            .addOnCompleteListener {
                UserProvider.user = it.result.toObject(User::class.java)
                if (it.isSuccessful) {
                    loginNavigator?.goToHome()
                    Log.e("user", UserProvider.user.toString())
                } else {
                    Log.e("error", it.exception?.localizedMessage!!)
                }

            }

    }

    fun goToRegister() {
        loginNavigator?.goToRegister()
    }

    private fun isValid(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()) {
            isValid = false
            emailError.set(true)
        }
        if (password.get().isNullOrBlank()) {
            isValid = false
            passwordError.set(true)
        }
        return isValid
    }


}