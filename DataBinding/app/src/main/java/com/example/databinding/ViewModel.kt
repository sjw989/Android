package com.example.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    private val _age = MutableLiveData<String>()

    val name : MutableLiveData<String>
        get() = _name
    val age : MutableLiveData<String>
        get() = _age

    init{
        _name.value = "init"
        _age.value = "0"
    }

    fun update(name : String, age : String){
        _name.value = name
        _age.value = age
    }

}