package com.example.hw02_apptive

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels

import androidx.navigation.fragment.findNavController
import com.example.hw02_apptive.databinding.FragLastBinding
import com.example.hw02_apptive.databinding.FragShowBinding


class LastFragment : Fragment() {
    private var _binding : FragLastBinding? = null // 뷰바인딩
    private val binding get() = _binding!! // 뷰바인딩
    val myViewModel : com.example.hw02_apptive.ViewModel by activityViewModels()
    var a : ArrayList<Int> = arrayListOf(1,2,3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragLastBinding.inflate(inflater,container,false) // 뷰바인딩


        for(n in a){
            Log.d("Int : ", n.toString());
        }
        a[1] = 3
        for(n in a){
            Log.d("Int : ", n.toString());
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnChange.setOnClickListener(){
            myViewModel.updateValue(binding.etvChange.text.toString(), "")
        }

    }
}