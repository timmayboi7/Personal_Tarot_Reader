# Tarot Reader

Alternate layout using Room plugin + KSP, DeckRepository pattern, and DrawnCard model.

## Open in Android Studio
1. File > Open... and select this folder.
2. Let Gradle sync (JDK 17).

## Provide your deck data
The project no longer checks in a partial `deck.json`. To run the app you must add your full deck definition:

1. Create `app/src/main/assets/deck.json`.
2. Supply an array of card objects that match `domain/TarotCard` (see `Models.kt`).
3. Optionally drop supporting artwork under `app/src/main/assets/cards/` and reference those paths in `imageAsset`.

Until the deck is added, the UI will surface guidance that a deck file is required.

## Notes
- Shuffle logic lives in `repo/DeckRepository.kt` and returns `DrawnCard` entries.
- `ReadingViewModel` maps to actual card data and feeds the Interpreter.

Generated 2025-11-06T05:40:59.217733
