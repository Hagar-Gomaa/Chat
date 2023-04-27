package com.example.chat.database

import com.example.chat.ui.model.Room
import com.example.chat.ui.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

object FireStoreUtils {
    private const val collectionUsers = "users"
    private const val collectionRooms = "rooms"


    private fun getCollections(collectionName:String): CollectionReference {
        return FirebaseFirestore.getInstance()
            .collection(collectionName)
    }

    fun insertUserToDb(user: User): Task<Void> {
        return getCollections(collectionUsers)
            .document(user.id!!)
            .set(user)
    }

    fun getUserFromDb(uid: String): Task<DocumentSnapshot> {
        return getCollections(collectionUsers)
            .document(uid)
            .get()
    }


    fun insertRoom(room: Room): Task<Void> {
        val docRef = getCollections(collectionRooms).document()
        room.categoryId = docRef.id
       return docRef.set(room)
    }
    fun getAllRooms ():Task<QuerySnapshot>{
       return getCollections(collectionRooms)
            .get()
    }

}