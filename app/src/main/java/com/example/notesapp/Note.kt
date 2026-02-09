package com.example.notesapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun NoteDesign(title: String="place holder" , content: String="place holder", date: String="place holder" ) {
    Card(onClick = {}, modifier = Modifier
        .wrapContentWidth()
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(CardColors.PINK.color)) {
        Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = date,
                    maxLines = 1,
                    textAlign = TextAlign.End
                )
            }
            Text(
                text = content,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .fillMaxWidth(1f),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun NoteList() {
    Column() {
        NoteDesign("title", "content","2025-12-8")
        NoteDesign("title 2", "content2","2025-12-9")
        NoteDesign("title 3", "content 3","2025-12-10")
        NoteDesign("title 4", "content 4","2025-12-11")
    }
}
@Composable
@Preview (showBackground = true)
fun NotePreview(){
    NoteDesign()
}
