package com.example.mynavigationsample.book

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.example.mynavigationsample.R
import com.example.mynavigationsample.databinding.BookFragmentBinding
import com.example.mynavigationsample.utils.backpressed.BackPressedListener
import com.example.mynavigationsample.utils.viewbinding.ViewBindingFragment

class BookFragment : ViewBindingFragment(), BackPressedListener {

    companion object {
        private const val ARG_NUMBER = "arg_number"

        fun newInstance(number: Int) = BookFragment().apply {
            arguments = bundleOf(ARG_NUMBER to number)
        }
    }

    private val binding by viewBinding(BookFragmentBinding::inflate)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val number = arguments?.getInt(ARG_NUMBER)
        binding.textNumber.text = "Book ${number.toString()}"
        binding.backStackEntryCount.text = "backStackEntryCount = ${parentFragmentManager.backStackEntryCount}"
        binding.openBookButton.setOnClickListener {
            parentFragmentManager.commit {
                add(R.id.tab_fragment_container, newInstance(number!! + 1), tag)
                addToBackStack(tag)
            }
        }
    }

    override fun onBackPressed(): Boolean {
        parentFragmentManager.popBackStack()
        return true
    }
}