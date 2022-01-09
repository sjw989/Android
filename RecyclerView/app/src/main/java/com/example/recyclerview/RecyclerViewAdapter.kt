package com.example.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val info : List<Pair<String,String>>)
                        : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> (){

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        // 설정된 레이아웃 findViewById
        var tv_name : TextView = itemView.findViewById(R.id.tv_name)
        var tv_phone : TextView = itemView.findViewById(R.id.tv_phone)
        fun setInfo(name : String, phone : String){
            Log.e("setInfo","run")
            tv_name.text = name
            tv_phone.text = phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 리사이클러뷰에 어떤 레이아웃을 넣을 것인지 설정
        // 설정한 view를 인자로 ViewHolder 객체 생성
        Log.e("onCreateViewHolder","run")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 생성된 ViewHolder 객체 값을 설정
        holder.setInfo(info[position].first, info[position].second)
        Log.e("onBindViewHolder","run")
    }

    override fun getItemCount(): Int {
        // 인자로 넘겨받은 리스트의 사이즈를 리턴
        Log.e("getItemCount","run")
        return info.size
    }

}