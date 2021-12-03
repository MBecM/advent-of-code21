package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val measurements = Day1.exampleData()
    val measurements = readLinesFromFile("input_d1.txt")
        .map(String::toInt)

    val answer = measurements.windowed(3) {
        it.sum()
    }.windowed(2) {
        it.first() < it.last()
    }.fold(0) { acc, valid ->
        if (valid) acc + 1 else acc
    }
    println("Day1 part 2 answer: $answer")
}