package com.example.hw02_apptive

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.hw02_apptive.databinding.FragShowBinding
import com.example.hw02_apptive.databinding.FragStartBinding


class ShowFragment : Fragment() {
    lateinit var  navController : NavController// 네비게이션 컨트롤러
    lateinit var bind : FragShowBinding
    val myViewModel : com.example.hw02_apptive.ViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(inflater, R.layout.frag_show, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view) // 네비게이션 컨트롤러 view로 부터 가져오기
        bind.user = myViewModel
        /*
        myViewModel.id.observe(viewLifecycleOwner, Observer {
            bind.tvId.text = myViewModel.id.value.toString()
            Log.e("update id", myViewModel.id.value.toString())
        })
        myViewModel.pw.observe(viewLifecycleOwner, Observer {
            bind.tvPw.text = myViewModel.pw.value.toString()
            Log.e("update pw", myViewModel.pw.value.toString())
        })
         */
        bind.btnGo.setOnClickListener() {
            findNavController().navigate(R.id.action_showFragment_to_lastFragment)
        }

    }




}