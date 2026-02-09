package com.example.notesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.CardColors
import java.util.Date
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
)

@Composable
fun NoteDesign() {
    Card(onClick = {}, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(CardColors.PINK.color)) {
        Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Title",
                    modifier = Modifier
                        .weight(3f),
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "2025-12-8",
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 1
                )
            }
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book",
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .fillMaxWidth(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun NoteList() {
    Column() {
        NoteDesign()
        NoteDesign()
        NoteDesign()
        NoteDesign()
    }
}
@Composable
@Preview (showBackground = true)
fun NotePreview(){
    NoteDesign()
}

@OptIn(ExperimentalTime::class)
fun data(): List<Note>{

    return listOf<Note>(
        Note(1, "Title 1", "Content 1","2025-12-8" ),
        Note(2, "Title 2", "Content 2", "2025-12-8"),
        Note(3, "Title 3", "Content 3", "2025-12-8"))

}
