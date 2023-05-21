package com.example.mynavigationsample.utils.tabs

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mynavigationsample.R
import com.example.mynavigationsample.utils.viewbinding.ViewBindingFragment
import com.example.mynavigationsample.databinding.FragmentTabContainerBinding
import com.example.mynavigationsample.mybooks.MyBooksFragment
import com.example.mynavigationsample.profile.ProfileFragment
import com.example.mynavigationsample.showcase.ShowcaseFragment

class TabContainerFragment : ViewBindingFragment() {

    companion object {
        private const val ARG_TAG = "arg_tag"

        fun newInstance(tag: String) = TabContainerFragment().apply {
            arguments = bundleOf(ARG_TAG to tag)
        }
    }

    private val binding by viewBinding(FragmentTabContainerBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tag = arguments?.getString(ARG_TAG) ?: error("tag is null")
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            childFragmentManager.commit {
                replace(R.id.tab_fragment_container, createTabFragmentByTag(tag))
                addToBackStack(tag)
            }
        }
    }

    private fun createTabFragmentByTag(tag: String): Fragment {
        return when (tag) {
            Tab.MY_BOOKS.tag -> MyBooksFragment()
            Tab.SHOWCASE.tag -> ShowcaseFragment()
            Tab.PROFILE.tag -> ProfileFragment()
            else -> error("unknown tag")
        }
    }
}