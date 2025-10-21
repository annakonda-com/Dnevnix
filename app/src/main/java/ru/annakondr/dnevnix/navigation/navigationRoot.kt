package ru.annakondr.dnevnix.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import ru.annakondr.dnevnix.ui.entities.Lesson
import ru.annakondr.dnevnix.ui.screens.CoachScreenUi
import ru.annakondr.dnevnix.ui.screens.DiaryScreenUi
import ru.annakondr.dnevnix.ui.screens.LessonScreenUi
import ru.annakondr.dnevnix.ui.screens.PetScreenUi

@Serializable
data object DiaryScreen : NavKey

@Serializable
data object CoachScreen : NavKey

@Serializable
data object PetScreen : NavKey

@Serializable
data class LessonScreen (val lesson: Lesson) : NavKey

@Composable
fun NavigationRoot(
    modifier: Modifier,
    backStack: NavBackStack,
    navigationManager: NavigationManager
) {
    NavDisplay(
        backStack = backStack,
        onBack = {navigationManager.back()},
        entryProvider = { key ->
            when (key) {
                is DiaryScreen -> NavEntry(key) {
                    DiaryScreenUi(modifier, navigationManager)
                }

                is CoachScreen -> NavEntry(key) {
                    CoachScreenUi(modifier)
                }

                is PetScreen -> NavEntry(key) {
                    PetScreenUi(modifier, navigationManager)
                }
                is LessonScreen -> NavEntry(key) {
                    LessonScreenUi(modifier, navigationManager, key.lesson)
                }

                else -> throw RuntimeException("Invalid NavKey")

            }
        })


}
