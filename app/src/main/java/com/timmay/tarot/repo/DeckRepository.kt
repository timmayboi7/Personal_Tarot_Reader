package com.timmay.tarot.repo

import com.timmay.tarot.domain.DrawnCard
import com.timmay.tarot.domain.TarotRng
import kotlin.random.Random

class DeckRepository(
    private val cardStore: CardStore = CardStore()
) {
    fun shuffled(seed: Long, reversalRate: Double = 0.45): List<DrawnCard> {
        val rng: Random = TarotRng.random(seed)
        val deck = cardStore.all().toMutableList()
        for (i in deck.lastIndex downTo 1) {
            val j = rng.nextInt(i + 1)
            val tmp = deck[i]
            deck[i] = deck[j]
            deck[j] = tmp
        }
        return deck.mapIndexed { idx, card ->
            DrawnCard(cardId = card.id, isReversed = rng.nextDouble() < reversalRate, positionIndex = idx)
        }
    }
}

