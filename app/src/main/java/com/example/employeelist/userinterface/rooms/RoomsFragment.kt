package com.example.employeelist.userinterface.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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

        val textView: TextView = binding.textNotifications
        roomsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }




        roomsViewModel.getRoomsData()

        return binding.root
    }

}