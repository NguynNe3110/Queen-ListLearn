package com.uzuu.jetpack_compose_hub.feature.hub.model

// Data class đại diện cho 1 bài học
data class Lesson(
    val title: String,
    val description: String,
    val activityClass: Class<*>
)
