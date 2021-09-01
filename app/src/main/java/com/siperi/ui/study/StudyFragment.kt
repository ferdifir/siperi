package com.siperi.ui.study

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.siperi.databinding.FragmentStudyBinding
import com.siperi.service.ui.SectionsPagerAdapter

class StudyFragment : Fragment() {

    private var fragmentStudyFragment: FragmentStudyBinding? = null
    private val binding get() = fragmentStudyFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentStudyFragment = FragmentStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(context as Context, childFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tab.setupWithViewPager(binding.viewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentStudyFragment = null
    }

}