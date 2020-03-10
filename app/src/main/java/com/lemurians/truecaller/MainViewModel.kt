package com.lemurians.truecaller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val tenthCharLiveData= MutableLiveData<String>()
    lateinit var listCharLiveData: MutableList<String>
    lateinit var uniqueWordCount: LiveData<Map<String,Int>>

    init {
        tenthCharLiveData.value = "Hi Truecaller"
    }

}