package com.example.swipequiz

data class Question(
    var sentence: String,
    var answer: String,
    var validation: Boolean
) {
    companion object {
        val QUESTION_SENTENCES = arrayOf(
            "Moet Scott een jurkje aan?",
            "Is Pokémon leuk?",
            "Is Scott stiekem een meisje?"
        )

        val QUESTION_ANSWERS = arrayOf(
            "Ja",
            "Ja",
            "Ja"
        )

        val QUESTION_VALIDATIONS = arrayOf(
            true,
            true,
            true
        )
    }
}