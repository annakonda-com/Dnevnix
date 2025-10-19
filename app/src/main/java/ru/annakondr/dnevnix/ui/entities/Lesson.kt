package ru.annakondr.dnevnix.ui.entities

data class Lesson (val subject: String, var task: String, val done: Boolean = false) {
}