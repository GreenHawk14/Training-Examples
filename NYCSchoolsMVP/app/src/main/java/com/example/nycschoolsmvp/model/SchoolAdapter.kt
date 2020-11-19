package com.example.nycschoolsmvp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschoolsmvp.R
import com.example.nycschoolsmvp.view.ListSatViewHolder
import com.example.nycschoolsmvp.view.ListSchoolViewHolder

class SchoolAdapter(val dataset: ListDataType, val callback: (String)->Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    class SchoolViewHolder(val schoolView: View): RecyclerView.ViewHolder(schoolView)
    {
            val schoolName = schoolView.findViewById(R.id.tv_item_school_name) as TextView
            val schoolAddress = schoolView.findViewById(R.id.tv_school_address) as TextView
            val schoolNum = schoolView.findViewById(R.id.tv_school_phone) as TextView
    }
    class ListSATViewHolder(val SAT_view: View): RecyclerView.ViewHolder(SAT_view)
    {
        val dbn = SAT_view.findViewById(R.id.tv_item_sat_name)as TextView
        val NumOf_Test_Takers = SAT_view.findViewById(R.id.tv_item_sat_test_taker) as TextView
        val Critical_reading_AVG = SAT_view.findViewById(R.id.tv_item_sat_critical_reading)as TextView
        val Math_AVG = SAT_view.findViewById(R.id.tv_item_sat_math_avg)as TextView
        val Writing_AVG = SAT_view.findViewById(R.id.tv_item_sat_writing_avg)as TextView
    }
private enum class SCHOOL_TYPE_VIEWS
{
    SchoolType, SatType
}
    override fun getItemViewType(position: Int): Int {
       return when(dataset)
       {
           is ListDataType.SCHOOLTYPE -> SCHOOL_TYPE_VIEWS.SchoolType.ordinal
           is ListDataType.SATTYPE -> SCHOOL_TYPE_VIEWS.SatType.ordinal
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return when(viewType)
        {
            SCHOOL_TYPE_VIEWS.SchoolType.ordinal->{
                ListSchoolViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_list_schools,
                    parent,
                    false
                ))
            }
            SCHOOL_TYPE_VIEWS.SatType.ordinal->{
                ListSchoolViewHolder(LayoutInflater.from(parent.context).inflate(
                    R.layout.item_layout_list_schools,
                    parent,
                    false
                ))
            }
            else -> throw Exception("Undefined Type")
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is ListSchoolViewHolder -> {
                holder.onBind(
                    (dataset as ListDataType.SCHOOLTYPE).data[position],
                    callback)
            }
            is ListSatViewHolder -> {
                holder.onBind(
                    (dataset as ListDataType.SATTYPE).data
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return when(dataset){
            is ListDataType.SCHOOLTYPE-> dataset.data.size
            else -> 1
        }
    }


}