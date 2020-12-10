package com.example.babershopcomms.database.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.babershopcomms.R
import com.example.babershopcomms.database.CustomerResponse
import com.example.babershopcomms.database.personItem

class customerAdapter (private val dataSet: CustomerResponse, private val callback: (item: personItem) -> Unit )
    : RecyclerView.Adapter<customerAdapter.ViewHolder>() {

    class ViewHolder(private val customerItem: View):
    RecyclerView.ViewHolder(customerItem)

    {
        val customerName: TextView = customerItem.findViewById(R.id.txt_CustName)
        val dateSet: TextView = customerItem.findViewById(R.id.date_txt)

        fun onBind(person: personItem, callback: (item: personItem) -> Unit){
            customerItem.setOnClickListener{
                callback.invoke(person)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.customer_listings,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(dataSet.result[position],callback)
    }

    override fun getItemCount(): Int {
        return dataSet.result.size
    }

}