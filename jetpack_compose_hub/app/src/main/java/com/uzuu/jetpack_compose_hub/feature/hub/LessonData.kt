package com.uzuu.jetpack_compose_hub.feature.hub

import com.uzuu.jetpack_compose_hub.feature.hub.model.Lesson
import com.uzuu.jetpack_compose_hub.feature.learn1_coreAndLayout.Learn1Activity

object LessonData {
    // Danh sách các bài học - THÊM BÀI MỚI VÀO ĐÂY
    val lessons = listOf(
        Lesson(
            "Bài 1: Column / Row / Box",
            "Layout cơ bản trong Compose",
            Learn1Activity::class.java
        )
    )
}