package com.example.babershopcomms

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

private const val TAG = "StylesHaircutscreen"
class StylesHaircutscreen : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
TimePickerDialog.OnTimeSetListener{
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minutes = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinutes = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_styles_haircut_screen)

        pickDate()


    }

    private fun pickDate() {

        val time = findViewById<Button>(R.id.btn_date)
            time.setOnClickListener {
                getDateTimeCalendar()

                Log.d(TAG, "Date is set... ")
                DatePickerDialog(this,this,year,month,day).show()

            }

    }

    private fun getDateTimeCalendar(){
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minutes = cal.get(Calendar.MINUTE)

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month
        saveYear = year

        getDateTimeCalendar()
        Log.d(TAG, "Time has been set... ")
        TimePickerDialog(this,this,hour,minutes,true).show()

    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

        val displaySet = findViewById<TextView>(R.id.dateDisplay)
        saveHour = hourOfDay
        saveMinutes = minutes
        Log.d(TAG, "Schedule created... ")
        displaySet.text = "$saveMonth-$saveDay-$saveYear\n @ $saveHour:$saveMinutes"
        Toast.makeText(this, "Great, setting your appointment time.", Toast.LENGTH_SHORT).show()
    }

}