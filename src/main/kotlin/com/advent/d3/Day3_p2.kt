package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val data = Day3.exampleData()
    val data = readLinesFromFile("input_d3.txt")

    tailrec fun extractRating(index: Int, numbers: List<String>, isOxygen: Boolean): String {
        val firstLine = numbers.first()
        val zerosAndOnes = numbers.groupBy { line -> line[index].toString().toInt() }

        val zero = zerosAndOnes[0]!!
        val one = zerosAndOnes[1]!!
        val newNumbers = if (zero.size == one.size || one.size > zero.size) {
            if (isOxygen) one else zero
        } else {
            if (isOxygen) zero else one
        }
        if (index == firstLine.length - 1 || newNumbers.size == 1) {
            return newNumbers.first()
        } else {
            return extractRating(index + 1, newNumbers, isOxygen)
        }
    }

    val oxygenRating = extractRating(0, data, isOxygen = true)
        .map { it.toString().toInt() }
        .bytesToInt()
    val coRating = extractRating(0, data, isOxygen = false)
        .map { it.toString().toInt() }
        .bytesToInt()
    val answer = oxygenRating * coRating

    println("Day3: oxygen generator rating: $oxygenRating, CO2 scrubber rating: $coRating")
    println("Day3 part 2 answer: $answer")
}
