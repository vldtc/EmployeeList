package com.example.employeelist.userinterface.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.employeelist.R
import com.example.employeelist.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val txtViewPeople: TextView = binding.tvPeopleNo
        homeViewModel.peopleData.observe(viewLifecycleOwner) {
            txtViewPeople.text = it.toString()
        }

        val txtViewRooms: TextView = binding.tvRoomsNo
        homeViewModel.roomsData.observe(viewLifecycleOwner) {
            txtViewRooms.text = it.toString()
        }

        val txtRoomsOccupied: TextView = binding.tvRoomsNoOccupied
        homeViewModel.roomOccupied.observe(viewLifecycleOwner) {
            txtRoomsOccupied.text = it.toString()
        }

        val txtRoomsVacant: TextView = binding.tvRoomsNoVacant
        homeViewModel.roomVacant.observe(viewLifecycleOwner) {
            txtRoomsVacant.text = it.toString()
        }

        homeViewModel.loadPeopleData()
        homeViewModel.loadRoomsData()
        homeViewModel.loadRoomsOccupancy()

        return binding.root
    }


}