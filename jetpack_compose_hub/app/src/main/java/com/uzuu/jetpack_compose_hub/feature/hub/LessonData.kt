package com.uzuu.jetpack_compose_hub.feature.hub

import com.uzuu.jetpack_compose_hub.feature.hub.model.Lesson
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn1_coreAndLayout.Learn1Activity
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.Learn2Activity
import com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.hub5.Hub5Activity
import com.uzuu.jetpack_compose_hub.feature.learn6_navigation.Learn6Activity
import com.uzuu.jetpack_compose_hub.feature.ztest.bottom_navigationQ315.navigationQueen315

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
        ),
        Lesson(
            "Bài 5: List & Page",
        "Thêm sau",
            Hub5Activity::class.java
        ),

        Lesson(
            "Bài 6: Navigation",
            "Thêm sau",
            Learn6Activity::class.java
        ),

        Lesson(
            "tesst",
            "Thêm sau",
            navigationQueen315::class.java
        ),
    )
}