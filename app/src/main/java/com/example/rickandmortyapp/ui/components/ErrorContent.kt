package com.example.rickandmortyapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.domain.error.DomainError

@StringRes
fun DomainError.toMessageRes(): Int = when(this){
    DomainError.NoConnection -> R.string.error_no_connection
    DomainError.NotFound -> R.string.character_not_found
    DomainError.Server -> R.string.error_server
    is DomainError.Unknown -> R.string.error_unknown
}



@Composable
fun ErrorContent(
    @StringRes errorRes: Int,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    onRetry: (() -> Unit)? = null
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(errorRes),
            color = textColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        if(onRetry != null){
            Spacer(Modifier.height(16.dp))
            Button(onClick = { }) {
                Text(text = "Reintentar")
            }
        }
    }

}