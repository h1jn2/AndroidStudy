package com.example.kotlinstudy

import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // 탭 레이아웃에 탭을 추가
        tabLayout.addTab(tabLayout.newTab().setText("1번째"))
        tabLayout.addTab(tabLayout.newTab().setText("2번째"))
        tabLayout.addTab(tabLayout.newTab().setText("3번째"))

        viewPager.adapter = FragmentAdapter(this, 3)
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

class FragmentAdapter(fragmentActivity: FragmentActivity, val tabCount: Int) :
    FragmentStateAdapter(fragmentActivity) {
//    override fun getCount(): Int {
//        return tabCount
//    }
//
//    override fun getItem(position: Int): Fragment {
//        when (tabCount) {
//            0 -> return FragmentOne()
//            1 -> return FragmentOne()
//            else -> return FragmentOne()
//        }
//    }

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when (tabCount) {
            0 -> return FragmentOne()
            1 -> return FragmentOne()
            else -> return FragmentOne()
        }
    }
}