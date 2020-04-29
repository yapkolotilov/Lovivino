package com.ocean.lovivino.field

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ocean.lovivino.fielddetails.FieldDetailsFragment

class FieldPagerAdapter(
    fragmentManager: FragmentManager,
    val ids: List<Long>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return FieldDetailsFragment(ids[position])
    }

    override fun getCount(): Int {
        return ids.size
    }
}