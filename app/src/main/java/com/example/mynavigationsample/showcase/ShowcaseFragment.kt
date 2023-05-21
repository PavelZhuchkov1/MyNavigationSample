package com.example.mynavigationsample.showcase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import com.example.mynavigationsample.R
import com.example.mynavigationsample.utils.viewbinding.ViewBindingFragment
import com.example.mynavigationsample.book.BookFragment
import com.example.mynavigationsample.databinding.FragmentShowcaseBinding

class ShowcaseFragment : ViewBindingFragment() {

    private val binding by viewBinding(FragmentShowcaseBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.openBookButton.setOnClickListener {
            parentFragmentManager.commit {
                add(R.id.tab_fragment_container, BookFragment.newInstance(0), tag)
                addToBackStack(tag)
            }
        }
    }
}