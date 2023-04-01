package com.example.employeelist.userinterface.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel
import com.example.employeelist.databinding.FragmentRoomsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!
    private val roomsViewModel by viewModels<RoomsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)

        roomsViewModel.rooms.observe(viewLifecycleOwner){
            it?.let {
                setupUI(it)
            }
        }

        roomsViewModel.getRoomsData()

        return binding.root
    }

    private fun setupUI(rooms: RoomsModel){
        val roomsAdapter = RoomsAdapter(rooms as ArrayList<RoomsItemModel>)
        binding.rvRooms.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = roomsAdapter
        }
    }

}