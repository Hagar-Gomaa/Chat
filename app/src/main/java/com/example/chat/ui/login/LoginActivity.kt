package com.example.chat.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.main.MainActivity
import com.example.chat.ui.register.RegistrationActivity

class LoginActivity : AppCompatActivity() ,LoginNavigator {
    private lateinit var viewModel: LoginViewModel
    lateinit var binding :ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel.loginNavigator=this
        binding.vmm=viewModel

    }
    override fun goToHome() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }

    override fun goToRegister() {
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)

    }
}