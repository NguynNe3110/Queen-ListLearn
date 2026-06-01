package com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.hub5

import com.uzuu.jetpack_compose_hub.feature.hub.model.Lesson
import com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn2_widgetAndModifier.Learn2Activity
import com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.columnChat.ColumnActivityC
import com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.columnQueen.ColumnActivityQ
import com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowChat.RowCActivity
import com.uzuu.jetpack_compose_hub.feature.learn5_listAndPage.feature.rowQueen.RowQActivity

object LessonData {
    // Danh sách các bài học - THÊM BÀI MỚI VÀO ĐÂY
    val lessons = listOf(
        Lesson(
            "Bài 5.1.1: LazyColumn Queen",
            "Layout cơ bản trong Compose",
            ColumnActivityQ::class.java
        ),

        Lesson(
            "Bài 5.1.2: LazyColumn Chat",
            "Layout cơ bản trong Compose",
            ColumnActivityC::class.java
        ),

        Lesson(
            "Bài 5.2.1: LazyRow Queen",
            "TextField[OutlinedTextField], Button[Icon, FilledTonalButton], Image[Asycn]",
            RowQActivity::class.java
        ),

        Lesson(
            "Bài 5.2.2: LazyRow Chat",
            "TextField[OutlinedTextField], Button[Icon, FilledTonalButton], Image[Asycn]",
            RowCActivity::class.java
        ),

        Lesson(
            "Bài 5.3: Column",
        "TextField[OutlinedTextField], Button[Icon, FilledTonalButton], Image[Asycn]",
            Learn2Activity::class.java
        ),
    )
}