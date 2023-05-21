package com.example.mynavigationsample.utils.viewbinding

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

abstract class ViewBindingActivity : AppCompatActivity() {

    private var bindingInflate: ((inflater: LayoutInflater) -> ViewBinding)? = null
    private var _binding: ViewBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflate?.invoke(layoutInflater)
        _binding?.let { setContentView(it.root) }
    }

    @Suppress("UNCHECKED_CAST")
    fun <VB : ViewBinding> viewBinding(
        inflate: (inflater: LayoutInflater) -> VB
    ): ReadOnlyProperty<Activity, VB> {
        bindingInflate = inflate
        return ReadOnlyProperty { _, _ ->
            _binding!! as VB
        }
    }
}