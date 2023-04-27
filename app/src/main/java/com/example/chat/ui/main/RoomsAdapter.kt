package com.example.newsapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.databinding.ItemRoomBinding
import com.example.chat.ui.model.Room


class RoomsAdapter(var roomsList: List<Room>?):RecyclerView.Adapter<RoomsAdapter.RoomViewHoler> (){

    class RoomViewHoler( val viewBinding:ItemRoomBinding) : RecyclerView.ViewHolder(viewBinding.root){

        fun bind(room: Room){
            viewBinding.item=room
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHoler {
        val viewBinding= ItemRoomBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RoomViewHoler(viewBinding)
    }

    override fun onBindViewHolder(holder: RoomViewHoler, position: Int) {
        holder.bind(roomsList!![position])
       }

    fun setList(list:List<Room>){
        roomsList= list
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return  roomsList!!.size
    }
}




