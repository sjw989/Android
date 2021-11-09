package com.example.hw02_apptive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hw02_apptive.databinding.FragStartBinding
import com.example.hw02_apptive.ViewModel as ViewModel1


class StartFragment : Fragment() {
    lateinit var  navController : NavController// 네비게이션 컨트롤러
    val myViewModel : com.example.hw02_apptive.ViewModel by activityViewModels()
    private var _binding : FragStartBinding? = null // 뷰바인딩
    private val binding get() = _binding!! // 뷰바인딩
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragStartBinding.inflate(inflater,container,false) // 뷰바인딩
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view) // 네비게이션 컨트롤러 view로 부터 가져오기


        binding.btnLogin.setOnClickListener(){
            myViewModel.updateValue(binding.etvID.text.toString(),binding.etvPW.text.toString())
            findNavController().navigate(R.id.action_startFragment_to_showFragment)
        }





    }

}