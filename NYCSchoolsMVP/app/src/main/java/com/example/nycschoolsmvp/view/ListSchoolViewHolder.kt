package com.example.nycschoolsmvp.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschoolsmvp.R
import com.example.nycschoolsmvp.model.NYCListSchoolResponse

class ListSchoolViewHolder (schoolView: View): RecyclerView.ViewHolder(schoolView)
{
    private val tvSchoolName: TextView = schoolView.findViewById(R.id.tv_item_school_name)
    private val tvSchoolAdd: TextView = schoolView.findViewById(R.id.tv_school_address)
    private val tvSchoolPhone: TextView = schoolView.findViewById(R.id.tv_school_phone)

    fun onBind(dataItem: NYCListSchoolResponse, onClickCallback: (String) -> Unit)
    {
        tvSchoolName.text = dataItem.school_name
        tvSchoolAdd.text = dataItem.location
        tvSchoolPhone.text = dataItem.phone_number
        itemView.setOnClickListener { onClickCallback.invoke(dataItem.dbn) }
    }
}