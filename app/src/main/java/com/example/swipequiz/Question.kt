package com.example.swipequiz

data class Question(
    var sentence: String,
    var answer: String,
    var validation: Boolean
) {
    companion object {
        val QUESTION_SENTENCES = arrayOf(
            "Scott heeft een jurk aan.",
            "Pokémon is leuk.",
            "Scott is een meisje."
        )

        val QUESTION_ANSWERS = arrayOf(
            "Nee",
            "Ja",
            "Nee"
        )

        val QUESTION_VALIDATIONS = arrayOf(
            false,
            true,
            false
        )
    }
}