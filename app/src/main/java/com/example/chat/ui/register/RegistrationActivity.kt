package com.example.chat.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityRegistrationBinding
import com.example.chat.ui.login.LoginActivity
import com.example.chat.ui.main.MainActivity

class RegistrationActivity : AppCompatActivity() ,RegisterNavigator{
    lateinit var binding :ActivityRegistrationBinding
    lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
      binding =DataBindingUtil.setContentView(this,R.layout.activity_registration)
        viewModel=ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.vm=viewModel
      viewModel.registerNavigator=this

    }

    override fun goToHome() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)    }

    override fun goToLogin() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}