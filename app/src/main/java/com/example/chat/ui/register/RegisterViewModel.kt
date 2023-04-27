package com.example.chat.ui.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chat.database.FireStoreUtils
import com.example.chat.ui.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {
    var userName = ObservableField<String>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var confirmPasword = ObservableField<String>()
    var showLoading = ObservableField<Boolean>()
    var userNameError = ObservableField<Boolean>(true)
    var emailError = ObservableField<Boolean>()
    var passwordError = ObservableField<Boolean>()
    var confirmPaswordErorr = ObservableField<Boolean>()

    var registerNavigator: RegisterNavigator? = null
    val auth = FirebaseAuth.getInstance()
    fun register() {
//        if (!isValid())return
        auth.createUserWithEmailAndPassword(email.get().toString(), password.get().toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    insertUserToDb(user?.uid.toString())
                    Log.e("user", user?.uid.toString())
                } else {
                    Log.e("error", task.exception!!.localizedMessage)
                }
            }
    }

    fun insertUserToDb(uid: String) {
        var user = User(
            uid,
            userName.get().toString(),
            email.get().toString(),
            password.get().toString()
        )
        FireStoreUtils.insertUserToDb(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) registerNavigator?.goToHome()
                else {
                    Log.e("error", task.exception!!.localizedMessage)
                }
            }

    }


    fun isValid(): Boolean {
        var isValid = true
        if (userName.get().isNullOrBlank()) {
            isValid = false
            userNameError.set(true)
        }
        if (email.get().isNullOrBlank()) {
            isValid = false
            emailError.set(true)
        }
        if (password.get().isNullOrBlank()) {
            isValid = false
            passwordError.set(true)
        }
        if (confirmPasword.get().isNullOrBlank()) {
            isValid = false
            confirmPaswordErorr.set(true)
        }
        return isValid
    }

}