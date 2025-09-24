package ru.annakondr.dnevnix.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class NavigationManager (val backStack: NavBackStack<NavKey>) {
    fun back() {
        when (backStack[backStack.size - 1]) {
            PetScreen, CoachScreen, DiaryScreen -> toHomeScreen()
            else -> backStack.removeLastOrNull()
        }
    }

    fun route(path: NavKey) {
        backStack.add(path)
    }

    fun toHomeScreen() {
        backStack.clear()
        backStack.add(DiaryScreen)
    }

    fun currentDestination(): NavKey {
        return backStack[backStack.size - 1]
    }
}