package com.example.employeelist.userinterface.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.employeelist.R
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.databinding.ItemRoomBinding
import java.util.*
import java.util.Collections.addAll
import kotlin.collections.ArrayList

class RoomsAdapter(val rooms: ArrayList<RoomsItemModel>) :
    RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {

    val initiaRoomDataList = ArrayList<RoomsItemModel>().apply {
        rooms?.let { addAll(it) }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRoomBinding.bind(view)
        fun handleData(item: RoomsItemModel?) {
            item?.let {
                with(it){
                    //Used to reformat the date string
                    var year: String = item?.createdAt.toString().take(4)
                    var month: String = item?.createdAt?.subSequence(5, 7).toString()
                    var day: String = item?.createdAt?.subSequence(8,10).toString()
                    var hour: String = item?.createdAt?.subSequence(11,13).toString()
                    var min: String = item?.createdAt?.subSequence(14,16).toString()

                    val formatedDate = "$day/$month/$year\n$hour:$min"
                    binding.tvRoomID.text = id
                    binding.tvRoomCreated.text = formatedDate
                    binding.tvRoomMaxOccup.text = maxOccupancy.toString()
                    binding.tvRoomIsOccup.text = if (isOccupied == true) "BUSY"
                    else "VACANT"
                }
                // If statement to select the text colour of the occupancy accordingly
                if(item.isOccupied == true){
                    binding.tvRoomIsOccup.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.red_primary
                        )
                    )
                }else{
                    binding.tvRoomIsOccup.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
            }
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

    fun getFilter(): android.widget.Filter {
        return roomsFilter
    }

    private val roomsFilter = object : android.widget.Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<RoomsItemModel> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                initiaRoomDataList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().toLowerCase()
                initiaRoomDataList.forEach {
                    if (it.isOccupied == true and (query == "BUSY")) {
                        filteredList.add(it)
                    }else if(it.isOccupied == false and (query == "VACANT")) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                rooms.clear()
                rooms.addAll(results.values as ArrayList<RoomsItemModel>)
                notifyDataSetChanged()
            }
        }
    }






}
