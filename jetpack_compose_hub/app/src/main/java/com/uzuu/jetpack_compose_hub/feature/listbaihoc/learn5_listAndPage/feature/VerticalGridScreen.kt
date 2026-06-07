package com.uzuu.jetpack_compose_hub.feature.listbaihoc.learn5_listAndPage.feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Dữ liệu giả lập (Dummy Data)
val colorList = listOf(
    Color.Red, Color.Blue, Color.Green, Color.Yellow,
    Color.Cyan, Color.Magenta, Color.DarkGray, Color.LightGray,
    Color.Black, Color.White, Color(0xFFFF5722), Color(0xFF9C27B0)
)

@Composable
fun ResponsiveColorGrid() {
    // 2. Bắt đầu khối LazyVerticalGrid
    LazyVerticalGrid(
        // Modifier: Chiếm toàn bộ chiều rộng và chiều cao có sẵn của màn hình
        modifier = Modifier.fillMaxSize(),

        // CẤU HÌNH RESPONSIVE: Tự động chia cột, mỗi cột tối thiểu 140.dp
        columns = GridCells.Adaptive(minSize = 140.dp),

        // Padding cho toàn bộ lưới: Cách lề trái/phải 16.dp, trên/dưới 8.dp
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),

        // Khoảng cách giữa các HÀNG (theo chiều dọc)
        verticalArrangement = Arrangement.spacedBy(8.dp),

        // Khoảng cách giữa các CỘT (theo chiều ngang)
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // 3. Khối items: Duyệt qua danh sách colorList
        items(
            items = colorList,
            // KEY: Luôn cung cấp key duy nhất. Ở đây ta dùng chính giá trị màu (hashCode)
            // để Compose biết chính xác item nào là item nào khi danh sách thay đổi.
            key = { color -> color.hashCode() }
        ) { color ->
            // 4. Gọi Composable vẽ từng ô (Item)
            ColorGridItem(color = color)
        }
    }
}

// Composable riêng biệt cho 1 ô lưới (Giúp code sạch và tái sử dụng)
@Composable
fun ColorGridItem(color: Color) {
    // Box để xếp chồng Text lên nền màu
    Box(
        modifier = Modifier
            // fillMaxWidth(): Trong Lazy Grid, item mặc định chỉ wrap content.
            // fillMaxWidth() bắt nó chiếm hết không gian của "cột" mà nó đang đứng.
            .fillMaxWidth()
            // aspectRatio(1f): Bắt buộc ô này luôn là hình vuông (tỉ lệ 1:1)
            .aspectRatio(1f)
            // Bo góc và tô màu nền
            .clip(RoundedCornerShape(12.dp))
            .background(color),
        // Căn giữa nội dung (Text) bên trong Box
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Color",
            color = Color.White,
            fontSize = 18.sp,
            // Thêm bóng đổ nhẹ cho chữ để dễ đọc trên nền sáng
            style = androidx.compose.ui.text.TextStyle(
                shadow = androidx.compose.ui.graphics.Shadow(
                    color = Color.Black.copy(alpha = 0.5f),
                    offset = androidx.compose.ui.geometry.Offset(2f, 2f),
                    blurRadius = 4f
                )
            )
        )
    }
}

@Preview
@Composable
fun Pre() {
    ResponsiveColorGrid()
}