package app.presentation.compose.notecontent.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NotePageContent (
    title: String,
    content: String,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = title,
            onValueChange = onTitleChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            value = content,
            onValueChange = onContentChange,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
    }
}