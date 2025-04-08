package com.example.mad_16424.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mad_16424.ui.theme.components.Menu
import com.example.mad_16424.ui.theme.components.SearchBar

@Composable
fun TopBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Menu()

        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            modifier = Modifier.weight(1f)
        )
    }
}
