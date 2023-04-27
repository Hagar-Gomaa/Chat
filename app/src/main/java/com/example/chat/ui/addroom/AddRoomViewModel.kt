package com.example.chat.ui.addroom

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chat.UserProvider
import com.example.chat.database.FireStoreUtils
import com.example.chat.ui.model.Category
import com.example.chat.ui.model.Room

class AddRoomViewModel : ViewModel() {
    var selectedCategory: Category = Category.getCategories()[0]
    var roomName = ObservableField<String>()
    var desc = ObservableField<String>()

    var room = Room(
        null,
        name = roomName.get(),
        desc = desc.get(),
        categoryId = selectedCategory.id,
        createdBy = UserProvider.user?.userName,
        imageId = selectedCategory.imageId
    )
    var addRoomNavigator: AddRoomNavigator? = null
    fun insertRoom() {
    FireStoreUtils.insertRoom(room)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                addRoomNavigator?.goToHome()
                return@addOnCompleteListener
            }
            else {
                Log.e("error", it.exception!!.localizedMessage!!)
            }
        }}}