package com.example.mynavigationsample.utils.backpressed

import androidx.fragment.app.FragmentManager
import com.example.mynavigationsample.R
import com.example.mynavigationsample.utils.tabs.TabsManager

class BackPressedManager(
    private val fragmentManager: FragmentManager,
    private val tabsManager: TabsManager,
    private val finishActivity: () -> Unit
) {

    fun handleBackPressed() {
        val visibleFragment = fragmentManager.fragments.firstOrNull { it.isVisible }
        val fragment = visibleFragment?.childFragmentManager?.findFragmentById(R.id.tab_fragment_container)
        when {
            fragment != null && fragment is BackPressedListener && fragment.onBackPressed() -> return
            tabsManager.handleBackPressed() -> return
            else -> finishActivity()
        }
    }
}