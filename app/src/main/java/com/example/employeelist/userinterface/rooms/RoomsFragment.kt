package com.example.employeelist.userinterface.rooms

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeelist.R
import com.example.employeelist.data.errorhandling.ErrorInline.doIfFailure
import com.example.employeelist.data.errorhandling.ErrorInline.doIfSuccess
import com.example.employeelist.data.model.rooms.RoomsItemModel
import com.example.employeelist.data.model.rooms.RoomsModel
import com.example.employeelist.databinding.FragmentRoomsBinding
import com.example.employeelist.userinterface.employees.EmployeesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!
    private val roomsViewModel by viewModels<RoomsViewModel>()
    private var roomsAdapter : RoomsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)

        roomsViewModel.rooms.observe(viewLifecycleOwner, Observer { result ->

            result.doIfSuccess { items ->
                setupUI(items)
            }

            result.doIfFailure{ message, throwable ->
                showErrorPopup(message ?: "Unkown error message")
            }

        })

        roomsViewModel.getRoomsData()

        return binding.root
    }

    private fun setupUI(rooms: RoomsModel){
        roomsAdapter = RoomsAdapter(rooms as ArrayList<RoomsItemModel>)
        binding.rvRooms.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = roomsAdapter
        }

        binding.rgOccupancyFilter.setOnCheckedChangeListener{ _, checkedId ->
            when(checkedId){
                R.id.rbAll -> {
                        roomsAdapter!!.updateData(rooms)
                    }
                R.id.rbOccupied -> {
                    val filteredList = rooms.filter {
                        (it.isOccupied == true)
                    }
                    val filteredRooms = RoomsModel()
                    for(p in filteredList){
                        filteredRooms.add(p)
                    }
                    roomsAdapter!!.updateData(filteredRooms)
                }
                R.id.rbVacant -> {
                    val filteredList = rooms.filter {
                        (!it.isOccupied!!)
                    }
                    val filteredRooms = RoomsModel()
                    for(p in filteredList){
                        filteredRooms.add(p)
                    }
                    roomsAdapter!!.updateData(filteredRooms)
                }

            }
        }
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