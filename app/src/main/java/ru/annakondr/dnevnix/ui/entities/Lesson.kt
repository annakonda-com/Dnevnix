package ru.annakondr.dnevnix.ui.entities

import kotlinx.serialization.Serializable

@Serializable
data class Lesson (val subject: String, var task: String, var ownTask: String? = null, val done: Boolean = false,
    var time: Int = 0) {
}