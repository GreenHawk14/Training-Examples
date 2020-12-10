package com.example.babershopcomms.database.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.babershopcomms.R
import com.example.babershopcomms.database.CustomerResponse
import com.example.babershopcomms.database.personItem

class DisplayPopupAdapter(
    private val dataSet: CustomerResponse,
    private val CustomerCallback: (customerItem: personItem) -> Unit,
)
    : RecyclerView.Adapter<DisplayPopupAdapter.Viewholder>() {


    class Viewholder(private val displayItem: View)
        :RecyclerView.ViewHolder(displayItem){
        val DisplayName: TextView = displayItem.findViewById(R.id.txt_CustName)
        val DisplayDate: TextView = displayItem.findViewById(R.id.dateDisplay)
        val DisplayDescript: TextView = displayItem.findViewById(R.id.txtDescription)
        val DisplaySpecBarber: TextView = displayItem.findViewById(R.id.txtBarberSpecial)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}