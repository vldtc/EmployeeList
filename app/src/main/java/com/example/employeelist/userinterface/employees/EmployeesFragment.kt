package com.example.employeelist.userinterface.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.employeelist.databinding.FragmentEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeesFragment : Fragment() {

    private var _binding: FragmentEmployeesBinding? = null
    private val binding get() = _binding!!
    private val employeesViewModel by viewModels<EmployeesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        employeesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        employeesViewModel.loadPeopleData()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}