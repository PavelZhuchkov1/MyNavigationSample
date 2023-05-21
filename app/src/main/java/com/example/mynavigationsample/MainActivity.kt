package com.example.mynavigationsample

import android.os.Bundle
import androidx.activity.addCallback
import com.example.mynavigationsample.databinding.ActivityMainBinding
import com.example.mynavigationsample.utils.backpressed.BackPressedManager
import com.example.mynavigationsample.utils.tabs.TabsManager
import com.example.mynavigationsample.utils.viewbinding.ViewBindingActivity

class MainActivity : ViewBindingActivity() {

    private val tabsManager by lazy {
        TabsManager(supportFragmentManager, binding.bottomNavigation)
    }

    private val backPressedManager by lazy {
        BackPressedManager(supportFragmentManager, tabsManager, ::finish)
    }

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) {
            backPressedManager.handleBackPressed()
        }
        tabsManager.setUpBottomNavigation()
    }
}