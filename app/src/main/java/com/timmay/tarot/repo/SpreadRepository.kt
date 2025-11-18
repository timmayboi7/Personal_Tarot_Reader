package com.timmay.tarot.repo

import com.timmay.tarot.domain.Spread

class SpreadRepository(
    private val store: SpreadStore = SpreadStore()
) {
    fun all(): List<Spread> = store.all()
    fun byId(id: String): Spread {
        val spreads = all()
        return spreads.firstOrNull { it.id == id } ?: spreads.first()
    }
}
