package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val measurements = Day1.exampleData()
    val measurements = readLinesFromFile("input_d1.txt")
        .map(String::toInt)

    val answer = measurements.windowed(2) {
        it.first() < it.last()
    }.fold(0) { acc, valid ->
        if (valid) acc + 1 else acc
    }
    println("Day1 part 1 answer: $answer")
}

object Day1 {
    fun exampleData() = """
    199
    200
    208
    210
    200
    207
    240
    269
    260
    263
""".trimIndent()
        .split("\n")
        .map(String::toInt)
}
