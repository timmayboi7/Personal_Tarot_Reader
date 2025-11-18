package com.timmay.tarot.repo

import com.timmay.tarot.domain.Spread
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream

class SpreadStore {
    private val json = Json { ignoreUnknownKeys = true }

    fun all(): List<Spread> {
        val stream: InputStream = SpreadStore::class.java.classLoader!!
            .getResourceAsStream("assets/spreads.json")
            ?: error("spreads.json not found")
        val text = stream.reader().readText()
        return json.decodeFromString(text)
    }
}
