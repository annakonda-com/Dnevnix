package ru.annakondr.dnevnix.ui.entities

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Elephant {
    var mood = mutableIntStateOf(0)
    var money = mutableIntStateOf(0)
    fun FeedElephant(){
            money.intValue = money.intValue - 20
            mood.intValue = mood.intValue + 15

    }
    fun CanFeed(): Boolean{
        return money.intValue - 20 >= 0
    }

}