package com.example.timernav.ui.log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.timernav.databinding.FragmentLogBinding
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.timernav.R
import com.google.android.material.tabs.TabLayout

class LogFragment : Fragment() {

    private lateinit var logViewModel: LogViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logViewModel = ViewModelProviders.of(this).get(LogViewModel::class.java)

        val binding = FragmentLogBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        setupViewPager(binding.viewpager)
        val tabs = binding.resultTabs
        tabs.setupWithViewPager(binding.viewpager)

        tabs.tabGravity = TabLayout.GRAVITY_FILL
        tabs.tabMode = TabLayout.MODE_FIXED
        return view
    }

    // Add Fragments to Tabs
    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment(LogUserFragment(), getString(R.string.logfragment_tab1_title))
        adapter.addFragment(LogDateFragment(), getString(R.string.logfragment_tab2_title))
        viewPager.adapter = adapter
    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList : ArrayList<Fragment> = arrayListOf()
        private val mFragmentTitleList : ArrayList<String> = arrayListOf()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }
}


