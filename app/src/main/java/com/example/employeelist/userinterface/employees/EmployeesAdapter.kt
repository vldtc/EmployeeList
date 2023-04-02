package com.example.employeelist.userinterface.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.employeelist.R
import com.example.employeelist.data.model.people.PeopleItemModel
import com.example.employeelist.databinding.ItemEmployeeBinding
import java.util.*
import kotlin.collections.ArrayList

class EmployeesAdapter(val employees: ArrayList<PeopleItemModel>) :
    RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    //Copy of the inital data list | To be retrieved once search view is empty
    val initialEmployeesDataList = ArrayList<PeopleItemModel>().apply {
        employees?.let { addAll(it) }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEmployeeBinding.bind(view)

        fun handleData(item: PeopleItemModel?) {
            item?.let {
                with(it) {
                    binding.tvEmplolyeeJob.text = jobtitle
                    binding.tvEmployeeName.text =
                        "${firstName.toString().uppercase()} ${lastName.toString().uppercase()}"
                    binding.tvID.text = "ID $id"
                    binding.tvEmployeeCreatedAt.text = createdAt
                    binding.tvEmployeeEmail.text = email
                    binding.tvEmployeeFavouriteColour.text = favouriteColor.toString().uppercase()
                    binding.sivEmployee.load(
                        data = "$avatar"
                    ) {
                        listener { request, result ->
                            binding.productImageViewLoadingProgressBar.isGone = true
                        }
                    }
                    setupListeners()
                }
            }
        }


        private fun setupListeners() {
            binding.mcvEmployees.setOnClickListener {
                binding.tvEmail.apply {
                    isVisible = !isVisible
                }
                binding.tvEmployeeEmail.apply {
                    isVisible = !isVisible
                }
                binding.vDivider1.apply {
                    isVisible = !isVisible
                }
                binding.tvFavouriteColour.apply {
                    isVisible = !isVisible
                }
                binding.tvEmployeeFavouriteColour.apply {
                    isVisible = !isVisible
                }
                binding.vDivider2.apply {
                    isVisible = !isVisible
                }
                binding.tvCreatedAt.apply {
                    isVisible = !isVisible
                }
                binding.tvEmployeeCreatedAt.apply {
                    isVisible = !isVisible
                }
                binding.vDivider3.apply {
                    isVisible = !isVisible
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = employees.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.handleData(employees?.get(position))
    }

}
