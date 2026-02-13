package com.example.notesapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun NoteDesign( title: String,
                content: String,
                date: Date = Date(),
                onClick: () -> Unit) {

    Card( modifier = Modifier
        .wrapContentWidth()
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(horizontal = 8.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(Binding.bindingColors().color),
        shape = RoundedCornerShape(10.dp)) {
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
                    text = SimpleDateFormat("HH:mm:aa", Locale.ENGLISH).format(date),
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
