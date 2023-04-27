package com.example.chat.ui.addroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityAddRoomBinding
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.login.LoginViewModel
import com.example.chat.ui.main.MainActivity
import com.example.chat.ui.model.Category

class AddRoomActivity : AppCompatActivity(),AddRoomNavigator {
    lateinit var viewModel: AddRoomViewModel
    lateinit var binding : ActivityAddRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRoomViewModel::class.java)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_room)
//        viewModel.addRoomNavigator=this
        binding.vm=viewModel
        val categryList =Category.getCategories()
        val adapter=SpinnerAdapter(categryList)
        binding.spinner.adapter=adapter
        viewModel.addRoomNavigator=this

        binding.spinner.onItemSelectedListener=object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, itemId: Long) {
                val selectedCategory = Category.getCategories().get(position)
                viewModel.selectedCategory =selectedCategory
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    override fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}