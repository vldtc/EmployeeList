package com.example.employeelist.userinterface.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeelist.data.model.people.PeopleItemModel
import com.example.employeelist.data.model.people.PeopleModel
import com.example.employeelist.databinding.FragmentEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesFragment : Fragment() {

    private var _binding: FragmentEmployeesBinding? = null
    private val binding get() = _binding!!
    private val employeesViewModel by viewModels<EmployeesViewModel>()
    private var employeesAdapter : EmployeesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        val root: View = binding.root


        employeesViewModel.employees.observe(viewLifecycleOwner){
            setupUI(it)
        }

        setUpSearchView()

        employeesViewModel.loadPeopleData()

        return root
    }

    private fun setupUI(employees: PeopleModel){
        employeesAdapter = EmployeesAdapter(employees as ArrayList<PeopleItemModel>)
        binding.ervEmployees.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = employeesAdapter
        }
    }

    private fun setUpSearchView() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                employeesAdapter?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                employeesAdapter?.getFilter()?.filter(newText);
                return true
            }

        })
    }





}