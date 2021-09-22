package org.techtown.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainFragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main2, container, false)

        val btn5 = view.findViewById<Button>(R.id.button5)
        btn5.setOnClickListener(View.OnClickListener {
            val tv5 = view.findViewById<TextView>(R.id.textView5)
            tv5.setText("abcd")
        })

        return view
    }


}