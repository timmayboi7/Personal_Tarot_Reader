package com.timmay.tarot.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.ZoneId
import com.timmay.tarot.domain.TarotRng
import com.timmay.tarot.repo.CardStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeScreen(nav: NavController) {
    val zone = remember { ZoneId.systemDefault() }
    val dailySeed = remember { TarotRng.dailySeed(zone) }
    var daily by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) {
        val result = withContext(Dispatchers.IO) {
            runCatching {
                val cards = CardStore().all()
                val idx = kotlin.random.Random(dailySeed).nextInt(cards.size)
                cards[idx].name
            }
        }
        result.onSuccess { cardName ->
            daily = cardName
            error = null
        }.onFailure { throwable ->
            daily = null
            error = throwable.message ?: "Add app/src/main/assets/deck.json"
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Daily Card", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(6.dp))
            when {
                error != null -> Text(
                    text = error ?: "Deck missing",
                    style = MaterialTheme.typography.bodyMedium
                )
                daily != null -> Text(daily ?: "", style = MaterialTheme.typography.titleLarge)
                else -> CircularProgressIndicator()
            }
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = { nav.navigate("spread_picker") },
                enabled = error == null
            ) { Text("Start a reading") }
            if (error != null) {
                Spacer(Modifier.height(12.dp))
                Text(
                    "Add app/src/main/assets/deck.json to enable readings.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
