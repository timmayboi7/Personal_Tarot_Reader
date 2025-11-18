package com.timmay.tarot.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.timmay.tarot.repo.SpreadRepository

@Composable
fun SpreadPickerScreen(nav: NavController) {
    val spreads = remember { SpreadRepository().all() }
    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
        items(spreads) { s ->
            ElevatedCard(onClick = { nav.navigate("reading/" + s.id) }, modifier = Modifier.padding(8.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text(s.name, style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.height(6.dp))
                    Text(s.positions.joinToString(" • ") { it.label }, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

