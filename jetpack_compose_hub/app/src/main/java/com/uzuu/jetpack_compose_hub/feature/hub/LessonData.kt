package com.uzuu.jetpack_compose_hub.feature.hub

import com.uzuu.jetpack_compose_hub.feature.hub.model.Lesson
import com.uzuu.jetpack_compose_hub.feature.learn1_coreAndLayout.Learn1Activity
import com.uzuu.jetpack_compose_hub.feature.learn2_widgetAndModifier.Learn2Activity

object LessonData {
    // Danh sách các bài học - THÊM BÀI MỚI VÀO ĐÂY
    val lessons = listOf(
        Lesson(
            "Bài 1: Column / Row / Box (Core and layout)",
            "Layout cơ bản trong Compose",
            Learn1Activity::class.java
        ),

        Lesson(
            "Bài 2: Widget & modifier",
            "TextField[OutlinedTextField], Button[Icon, FilledTonalButton], Image[Asycn]",
            Learn2Activity::class.java
        )
    )
}