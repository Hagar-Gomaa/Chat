package com.example.chat.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityMainBinding
import com.example.chat.databinding.ActivityRegistrationBinding
import com.example.chat.ui.addroom.AddRoomActivity
import com.example.newsapp.ui.category.RoomsAdapter

class MainActivity : AppCompatActivity(), HomeNavigator {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel
    lateinit var adapter: RoomsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.vm = viewModel
        viewModel.homeNavigator = this
        subscribeToLiveData()
        viewModel.getAllRooms()
        adapter= RoomsAdapter(null)
        binding.roomsRecyclerView.adapter=adapter
    }

    private fun subscribeToLiveData() {
        viewModel.roomslist.observe(this, Observer {
         adapter.setList(it)
        })
    }

    override fun goToAddRoom() {
        val intent = Intent(this, AddRoomActivity::class.java)
        startActivity(intent)

    }
}