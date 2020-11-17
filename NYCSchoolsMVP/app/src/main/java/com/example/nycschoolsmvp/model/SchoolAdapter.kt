package com.example.nycschoolsmvp.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschoolsmvp.R

class SchoolAdapter (val SchoolList: ArrayList<NYCListSchoolResponse> )
{
    class SchoolViewHolder(val schoolView: View): RecyclerView.ViewHolder(schoolView)
    {
        val schoolName = schoolView.findViewById(R.id.tv_item_school_name) as TextView
        val schoolAddress = schoolView.findViewById(R.id.tv_school_address) as TextView
        val schoolNum = schoolView.findViewById(R.id.tv_school_phone) as TextView
    }

}