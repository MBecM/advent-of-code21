package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val data = Day3.exampleData()
    val data = readLinesFromFile("input_d3.txt")

    val gammaRateBits = data
        .fold(MutableList(data.first().length) { 0 }) { list, s ->
            s.forEachIndexed { i, c ->
                list[i] += c.toString().toInt()
            }
            list
        }
        .map { if (it > data.size / 2) 1 else 0 }
    val epsilonRateBits = gammaRateBits.flipBits()
    val gammaRate = gammaRateBits.bytesToInt()
    val epsilonRate = epsilonRateBits.bytesToInt()

    val answer = gammaRate * epsilonRate
    println("Day3 part 1 answer: $answer")
}

object Day3 {
    fun exampleData() = """
00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010
""".trimIndent()
        .split("\n")
}
