package com.example.employeelist.userinterface.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
            txtViewPeople.text = "No of people employed\n${it.toString()}"
        }

        val txtViewRooms: TextView = binding.tvRoomsNo
        homeViewModel.roomsData.observe(viewLifecycleOwner) {
            txtViewRooms.text = "No of total rooms\n${it.toString()}"
        }

        val txtRoomsOccupied: TextView = binding.tvRoomsNoOccupied
        homeViewModel.roomOccupied.observe(viewLifecycleOwner) {
            txtRoomsOccupied.text = "No of occupied rooms\n${it.toString()}"
        }

        val txtRoomsVacant: TextView = binding.tvRoomsNoVacant
        homeViewModel.roomVacant.observe(viewLifecycleOwner) {
            txtRoomsVacant.text = "No of vacant rooms\n${it.toString()}"
        }

        homeViewModel.loadPeopleData()
        homeViewModel.loadRoomsData()
        homeViewModel.loadRoomsOccupancy()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}