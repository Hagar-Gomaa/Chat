package com.example.chat.ui.model

import com.example.chat.R

data class Category(var id:String ,var name:String, var imageId :Int){
    companion object{
        fun getCategories():List<Category>{
            return listOf(Category("sports","Sports", R.drawable.sports),
                Category("musics","Musics", R.drawable.music)
             ,Category("movies","Movies", R.drawable.movies))
        }
    }
}
