package com.mysticraccoon.composecamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Badge
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysticraccoon.composecamp.ui.theme.ComposeCampTheme

class M3MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCampTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                   // CrashingLazyColumn()
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart){
                        CasinoBadgeComponentToggle{}
                    }
                }
            }
        }
    }
}


@Composable
fun StackPaddingBox() {

    Surface(color = Color.White) {
        Box(
            modifier = Modifier
                .padding(all = 8.dp)
                .border(width = 4.dp, color = Color.Red)
                .padding(all = 6.dp)
                .border(width = 8.dp, color = Color.Green)
                .padding(16.dp)
                .border(width = 2.dp, color = Color.Blue)
                .padding(24.dp)
                .background(Color.Yellow)
        ) {
            Text(text = "Welcome to Compose Camp!", color = Color.Black, fontSize = 32.sp)
        }
    }
}

@Preview
@Composable
fun PreviewStackPaddingBox() {
    ComposeCampTheme {
        StackPaddingBox()
    }
}

@Composable
fun CrashingLazyColumn() {

    val parentList = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {

//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .verticalScroll(state = rememberScrollState())
//        ) {
//            parentList.forEach { item ->
//                CrashLazyColumnItem(title = item)
//            }
//        }

        LazyColumn() {
            items(parentList) { item ->
                CrashLazyColumnItem(item)
            }
        }
    }

}

@Composable
fun CrashLazyColumnItem(title: String) {

    val itemList = listOf<String>("A", "B", "C", "D", "E", "F", "G", "H")
    Text(text = title, color = Color.Green, fontSize = 24.sp)
    // modifier = Modifier.height(200.dp)

//    LazyColumn(modifier = Modifier.height(200.dp)) {
//        items(itemList) { item ->
//            Text(text = item, color = Color.Red, fontSize = 24.sp)
//        }
//    }

    Column(
        modifier = Modifier
            .height(150.dp)
            .verticalScroll(state = rememberScrollState())
            .fillMaxWidth()
    ) {
        itemList.forEach { item ->
            Text(text = item, color = Color.Red, fontSize = 24.sp)
        }
    }

}

@Preview
@Composable
fun PreviewCrashingLazyColumn() {
    CrashingLazyColumn()
}


@Composable
fun CasinoBadgeComponentToggle(
    onClose: () -> Unit
) {
    val chipList: List<String> = listOf(
        "All",
        "New",
        "Most Played"
    )
    var selected by remember {
        mutableStateOf("")
    }

    var isExpanded by remember {
        mutableStateOf(true)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                top = 12.dp,
                bottom = 12.dp
            )
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(shape = CircleShape, width = 1.dp, color = Color.White)
                .padding(4.dp)
                .clickable {
                    if (isExpanded) {
                        isExpanded = false
                        onClose()
                    } else {
                        isExpanded = true
                    }
                }
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.Close else Icons.Filled.Search,
                contentDescription = "close",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        AnimatedVisibility(visible = isExpanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
            ) {
                chipList.forEach { chip ->
                    CasinoChipToggleable(
                        chipInfo = chip,
                        isSelected = selected == chip,
                        onSelectedChanged = {
                            selected = it
                            isExpanded = false
                        },
                        iconPainter = rememberVectorPainter(image = Icons.Filled.Star),
                        badgeNumber = 25
                    )
                }
            }
        }
    }
}

@Composable
fun CasinoBadgeComponentClickable(
    onClose: () -> Unit
) {
    val chipList: List<String> = listOf(
        "All",
        "New",
        "Most Played"
    )
    var selected by remember {
        mutableStateOf(chipList.firstOrNull().orEmpty())
    }

    var isExpanded by remember {
        mutableStateOf(true)
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                top = 12.dp,
                bottom = 12.dp
            )
            .horizontalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .border(shape = CircleShape, width = 1.dp, color = Color.White)
                .padding(4.dp)
                .clickable {
                    if (isExpanded) {
                        isExpanded = false
                        onClose()
                    } else {
                        isExpanded = true
                    }
                }
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Filled.Close else Icons.Filled.Search,
                contentDescription = "close",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        AnimatedVisibility(visible = isExpanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 12.dp,
                        bottom = 12.dp
                    )
            ) {
                chipList.forEach { chip ->
                    CasinoChipClickable(
                        chipInfo = chip,
                        isSelected = selected == chip,
                        onSelected = {
                            selected = it
                        },
                        iconPainter = rememberVectorPainter(image = Icons.Filled.Star),
                        badgeNumber = 25
                    )
                }
            }
        }
    }
}

@Composable
fun CasinoChipClickable(
    chipInfo: String,
    isSelected: Boolean,
    onSelected: (String) -> Unit,
    iconPainter: Painter?,
    badgeNumber: Int?
) {
    val background = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
    val borderColor = if (isSelected) Color.White else Color.White
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else Color.White
    val badgeBackgroundColor = Color.White

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(CircleShape)
            .border(shape = CircleShape, width = 1.dp, color = borderColor)
            .background(background)
            .clickable {
                onSelected(chipInfo)
            }
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            iconPainter?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = chipInfo,
                style = TextStyle(
                    color = contentColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
            badgeNumber?.let {
                Badge(
                    backgroundColor = badgeBackgroundColor
                ) {
                    Text(
                        text = it.toString(),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CasinoChipToggleable(
    chipInfo: String,
    isSelected: Boolean,
    onSelectedChanged: (String) -> Unit,
    iconPainter: Painter?,
    badgeNumber: Int?
) {
    val background = if (isSelected) Color.White else Color.Black
    val borderColor = if (isSelected) Color.White else Color.White
    val contentColor = if (isSelected) Color.Black else Color.White
    val badgeBackgroundColor = if (isSelected) Color.Black else Color.LightGray

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .clip(CircleShape)
            .border(shape = CircleShape, width = 1.dp, color = borderColor)
            .background(background)
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedChanged(if (isSelected) "" else chipInfo)
                }
            )
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            iconPainter?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    tint = contentColor,
                    modifier = Modifier.size(24.dp)
                )
            }
            Text(
                text = chipInfo,
                style = TextStyle(
                    color = contentColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(8.dp)
            )
            badgeNumber?.let {
                Badge(
                    backgroundColor = badgeBackgroundColor
                ) {
                    Text(
                        text = it.toString(),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(name = "Toggleable")
fun CasinoBadgeComponentToggleablePreview() {
    ComposeCampTheme {
        Surface(color = Color.Black) {
            CasinoBadgeComponentToggle(
                onClose = {}
            )
        }
    }
}

@Composable
@Preview(name = "Clickable")
fun CasinoBadgeComponentClickablePreview() {
    ComposeCampTheme {
        Surface(color = Color.Black) {
            CasinoBadgeComponentClickable(
                onClose = {}
            )
        }
    }
}