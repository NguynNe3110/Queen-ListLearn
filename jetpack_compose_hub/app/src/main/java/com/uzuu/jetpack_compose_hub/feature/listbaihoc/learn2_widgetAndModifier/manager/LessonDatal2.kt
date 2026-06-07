package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.manager

import com.uzuu.jetpack_compose_hub.feature.hub.model.Lesson
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.first.Learn2Activity
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.update1.updateScreen
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.lazy_columnChat.ColumnActivityC
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.lazy_columnQueen.ColumnActivityQ
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowChat.RowCActivity
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature.rowQueen.RowQActivity
import com.uzuu.jetpack_compose_hub.feature.ztest.feature.navigationMVVM.NavigationMVVMActivity

object LessonDatal2 {
    // Danh sách các bài học - THÊM BÀI MỚI VÀO ĐÂY
    val lessonsl2 = listOf(
        Lesson(
            "Bài 2.1: LazyColumn Queen",
            "Layout cơ bản trong Compose",
            Learn2Activity::class.java
        ),
        Lesson(
            "Bài 2.2: update",
            "Layout cơ bản trong Compose",
            updateScreen::class.java
        ),
    )
}