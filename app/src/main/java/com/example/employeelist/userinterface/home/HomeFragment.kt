package com.example.employeelist.userinterface.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.employeelist.R
import com.example.employeelist.data.errorhandling.ErrorInline.doIfFailure
import com.example.employeelist.data.errorhandling.ErrorInline.doIfSuccess
import com.example.employeelist.data.errorhandling.ResultOf
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
        homeViewModel.peopleData.observe(viewLifecycleOwner, Observer { result ->
            result.doIfSuccess { items ->
                txtViewPeople.text = items.toString()
            }

            result.doIfFailure{ message, throwable ->
                showErrorPopup(message ?: "Unkown error message")
            }
        })

        val txtViewRooms: TextView = binding.tvRoomsNo
        homeViewModel.roomsData.observe(viewLifecycleOwner, Observer { result ->
            result.doIfSuccess { items ->
                txtViewRooms.text = items.toString()
            }

            result.doIfFailure{ message, throwable ->
                showErrorPopup(message ?: "Unkown error message")
            }
        })

        val txtRoomsOccupied: TextView = binding.tvRoomsNoOccupied
        homeViewModel.roomOccupied.observe(viewLifecycleOwner, Observer { result ->
            result.doIfSuccess { items ->
                txtRoomsOccupied.text = items.toString()
            }

            result.doIfFailure{ message, throwable ->
                showErrorPopup(message ?: "Unkown error message")
            }
        })

        val txtRoomsVacant: TextView = binding.tvRoomsNoVacant
        homeViewModel.roomVacant.observe(viewLifecycleOwner, Observer { result ->
            result.doIfSuccess { items ->
                txtRoomsVacant.text = items.toString()
            }

            result.doIfFailure{ message, throwable ->
                showErrorPopup(message ?: "Unkown error message")
            }
        })

        homeViewModel.loadPeopleData()
        homeViewModel.loadRoomsData()

        return binding.root
    }

    private fun showErrorPopup(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Error")
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}