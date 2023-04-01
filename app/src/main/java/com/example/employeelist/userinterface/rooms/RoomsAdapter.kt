package com.example.employeelist.userinterface.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.employeelist.R
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.databinding.ItemRoomBinding

class RoomsAdapter(val rooms: ArrayList<RoomsItemModel>) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRoomBinding.bind(view)
        fun handleData(item: RoomsItemModel?) {

            binding.tvRoomID.text = item?.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomsAdapter.ViewHolder, position: Int) {
        holder?.handleData(rooms.get(position))
    }

    override fun getItemCount(): Int = rooms.size!!


}
