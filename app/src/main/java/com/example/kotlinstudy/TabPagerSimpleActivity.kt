package com.example.kotlinstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TabPagerSimpleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager_simple)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("1번째"))
        tabLayout.addTab(tabLayout.newTab().setText("2번째"))
        tabLayout.addTab(tabLayout.newTab().setText("3번째"))

        viewPager.adapter = ViewPagerAdapter(LayoutInflater.from(this), 3)
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}

class ViewPagerAdapter(val layoutInflater: LayoutInflater, val tabCount: Int) : PagerAdapter() {
    override fun getCount(): Int {
        return tabCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.one_fragment, container, false)
                container.addView(view)
                return view
            }

            1 -> {
                val view = layoutInflater.inflate(R.layout.activity_calculator, container, false)
                container.addView(view)
                return view
            }

            else -> {
                val view = layoutInflater.inflate(R.layout.one_fragment, container, false)
                container.addView(view)
                return view
            }
        }
    }
}