package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val commands = Day2.exampleData()
    val commands = readLinesFromFile("input_d2.txt")
        .map {
            val instruction = it.split(" ")
            Command(instruction.first(), instruction.last().toInt())
        }
    val finalPosition = commands
        .fold(Position(0, 0)) { position, command ->
            when (command.command) {
                "forward" -> position increaseHorizontal command.value
                "down" -> position changeDepth command.value
                "up" -> position changeDepth -command.value
                else -> throw RuntimeException("Invalid instruction!")
            }
        }
    val answer = finalPosition.mutiply()
    println("Day2 part 1 position: $finalPosition")
    println("Day2 part 1 answer: $answer")
}

data class Command(val command: String, val value: Int)
data class Position(val horizontal: Int, val depth: Int) {
    infix fun increaseHorizontal(horizontal: Int) = Position(this.horizontal + horizontal, this.depth)
    infix fun changeDepth(depth: Int) = Position(this.horizontal, this.depth + depth)
    fun mutiply() = horizontal * depth
}

object Day2 {
    fun exampleData() = """
forward 5
down 5
forward 8
up 3
down 8
forward 2
""".trimIndent()
        .split("\n")
}
