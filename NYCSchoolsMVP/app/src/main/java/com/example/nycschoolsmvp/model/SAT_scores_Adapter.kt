package com.example.nycschoolsmvp.model

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschoolsmvp.R

class SAT_scores_Adapter(val SAT_Scores_List: ArrayList<NYCListSatResponse>)
{
    class SATscoreViewHolder(val SAT_scores_AdapterView: View): RecyclerView.ViewHolder(SAT_scores_AdapterView)
    {
        fun bindSATItems()
        {
            val dbn = SAT_scores_AdapterView.findViewById(R.id.tv_item_sat_name)as TextView
            val NumOf_Test_Takers = SAT_scores_AdapterView.findViewById(R.id.tv_item_sat_test_taker) as TextView
            val Critical_reading_AVG = SAT_scores_AdapterView.findViewById(R.id.tv_item_sat_critical_reading)as TextView
            val Math_AVG = SAT_scores_AdapterView.findViewById(R.id.tv_item_sat_math_avg)as TextView
            val Writing_AVG = SAT_scores_AdapterView.findViewById(R.id.tv_item_sat_writing_avg)as TextView
        }
    }
}