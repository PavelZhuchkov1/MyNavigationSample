package com.example.mynavigationsample.utils.tabs

import androidx.fragment.app.FragmentManager
import com.example.mynavigationsample.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Stack

class TabsManager(
    private val fragmentManager: FragmentManager,
    private val bottomNavigationView: BottomNavigationView
) {

    private val tabsBackStack: Stack<Tab> = Stack<Tab>()

    fun setUpBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            val tab = Tab.values().find { it.itemId == item.itemId }
                ?: return@setOnItemSelectedListener false
            openTab(tab)
            true
        }
        openTab(Tab.MY_BOOKS)
    }

    fun handleBackPressed(): Boolean {
        if (tabsBackStack.size == 1) return false
        tabsBackStack.pop()
        openTab(tabsBackStack.peek())
        bottomNavigationView.selectedItemId = tabsBackStack.peek().itemId
        return true
    }

    private fun openTab(tab: Tab) {
        addTabToBackStack(tab)
        openTabContainerFragment(tab)
    }

    private fun openTabContainerFragment(tab: Tab) {
        val visibleFragment = fragmentManager.fragments.firstOrNull { it.isVisible }
        val newFragment = fragmentManager.findFragmentByTag(tab.tag)
        if (visibleFragment != null && newFragment != null && visibleFragment === newFragment) return

        val transaction = fragmentManager.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.fragment_container,
                TabContainerFragment.newInstance(tab.tag),
                tab.tag
            )
        }
        if (visibleFragment != null) transaction.hide(visibleFragment)
        if (newFragment != null) transaction.show(newFragment)
        transaction.commitNow()
    }

    private fun addTabToBackStack(tab: Tab) {
        tabsBackStack.push(tab)
        if (tabsBackStack.indexOf(tab) != tabsBackStack.lastIndex) {
            tabsBackStack.remove(tab)
        }
    }
}