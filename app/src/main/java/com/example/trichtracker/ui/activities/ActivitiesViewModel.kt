package com.example.trichtracker.ui.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivitiesViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is where the activities will go"
    }
    val text: LiveData<String> = _text
}