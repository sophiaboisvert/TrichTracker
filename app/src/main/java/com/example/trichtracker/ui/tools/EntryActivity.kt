package com.example.trichtracker.ui.tools

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trichtracker.R
import java.text.DateFormatSymbols
import java.util.Calendar

class EntryActivity : AppCompatActivity() {

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var dateButton: Button
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var timeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        //Date Button
        initDatePicker()
        dateButton = findViewById(R.id.date_picker_button)
        dateButton.text = getTodaysDate()
        //Time Button
        initTimePicker()
        timeButton = findViewById(R.id.time_picker_button)
        timeButton.text = getTime()

    }

    //Time Button
    private fun getTime(): String {
        val c = Calendar.getInstance()
        return makeTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE))
    }

    private fun initTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            val formattedTime = makeTimeString(hour, minute)
            timeButton.text = formattedTime
        }

        val c = Calendar.getInstance()
        timePickerDialog = TimePickerDialog(this, timeSetListener, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),false)
        // datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    }

    private fun makeTimeString(hour: Int, minute: Int): String {

        var adjmin = "";
        if(minute < 10)
            adjmin = "0$minute"
        else
            adjmin = "$minute"

        return if(hour == 12)
            "$hour:$adjmin PM"
        else if (hour > 12){
            val adjhour = hour - 12;
            "$adjhour:$adjmin PM"
        } else if (hour == 0)
            "12:$adjmin AM"
        else
            "$hour:$adjmin AM"
    }

    fun openTimePicker(view: View) {
        timePickerDialog.show()
    }

    //DateButton

    private fun getTodaysDate(): String {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH) + 1
        val day = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val formattedDate = makeDateString(day, month + 1, year)
            dateButton.text = formattedDate
        }

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(this, dateSetListener, year, month, day)
        // datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        val monthString = DateFormatSymbols().shortMonths[month - 1]
        return "$monthString $day $year"
    }

    fun openDatePicker(view: View) {
        datePickerDialog.show()
    }

}