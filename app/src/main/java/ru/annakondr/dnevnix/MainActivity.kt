package ru.annakondr.dnevnix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.rememberNavBackStack
import ru.annakondr.dnevnix.navigation.BasicTopBar
import ru.annakondr.dnevnix.navigation.CoachScreen
import ru.annakondr.dnevnix.navigation.DiaryScreen
import ru.annakondr.dnevnix.navigation.NavigationBottomBar
import ru.annakondr.dnevnix.navigation.NavigationManager
import ru.annakondr.dnevnix.navigation.NavigationRoot
import ru.annakondr.dnevnix.navigation.PetScreen
import ru.annakondr.dnevnix.ui.screens.DiaryScreenUi
import ru.annakondr.dnevnix.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                DnevnixApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DnevnixApp(){
    val backStack = rememberNavBackStack(DiaryScreen)
    val navigationManager = NavigationManager(backStack)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { NavigationBottomBar(backStack, navigationManager) },
        topBar = { BasicTopBar(stringResource(when (navigationManager.currentDestination()) {
            DiaryScreen -> R.string.diary
            CoachScreen -> R.string.coach
            PetScreen -> R.string.pet
            else -> R.string.dnevnix
        })) },
    ) { innerPadding ->
        NavigationRoot(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            backStack, navigationManager
        )
    }
}
