package org.techtown.sampleviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var vpAdapter: FragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpAdapter = CustomPagerAdapter(this)
        viewpager.adapter = vpAdapter

        dots_indicator.setViewPager2(viewpager)

    }

    class CustomPagerAdapter(fm : FragmentActivity): FragmentStateAdapter(fm){
        private val PAGENUMBER = 4
        override fun getItemCount(): Int {
            return PAGENUMBER
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> TestFragment.newInstances(R.raw.img00, "page00")
                1 -> TestFragment.newInstances(R.raw.img01, "page01")
                2 -> TestFragment.newInstances(R.raw.img02, "page02")
                3 -> TestFragment.newInstances(R.raw.img03, "page03")
                else -> TestFragment.newInstances(R.raw.img00, "page00")
            }
        }

    }
}