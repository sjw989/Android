package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.user = ViewModel()

        binding.btn.setOnClickListener{
            binding.user?.update(binding.editName.text.toString(),
                                binding.editAge.text.toString())
            Log.e("myLog", binding.editName.text.toString())
        }
    }
}