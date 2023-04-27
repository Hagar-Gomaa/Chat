package com.example.chat.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.database.FireStoreUtils
import com.example.chat.ui.model.Room


class HomeViewModel : ViewModel() {
    var roomslist= MutableLiveData<List<Room>>()
    var homeNavigator: HomeNavigator? = null

    fun goToAddRoom() {
        homeNavigator?.goToAddRoom()
    }
    fun getAllRooms(){
        FireStoreUtils.getAllRooms()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    roomslist.value=it.result.toObjects(Room::class.java)
                return@addOnCompleteListener}
                else{Log.e("error",it.exception!!.localizedMessage!!)}
            }
    }



}