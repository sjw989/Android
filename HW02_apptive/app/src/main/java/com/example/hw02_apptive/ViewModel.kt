package com.example.hw02_apptive
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val _id = MutableLiveData<String>()
    private val _pw = MutableLiveData<String>()

    val id : MutableLiveData<String>
        get() = _id

    val pw : MutableLiveData<String>
        get() = _pw

    init{
        _id.value = ""
        _pw.value = ""
    }

    fun updateValue(id : String, pw : String ){
        _id.value = id
        _pw.value = pw
    }

}
