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
        .fold(PositionWithAim(0, 0, 0)) { position, command ->
           when (command.command) {
                "forward" -> position increaseHorizontal command.value
                "down" -> position changeAim command.value
                "up" -> position changeAim -command.value
                else -> throw RuntimeException("Invalid instruction!")
            }
        }
    val answer = finalPosition.mutiply()
    println("Day2 part 2 position: $finalPosition")
    println("Day2 part 2 answer: $answer")
}

data class PositionWithAim(val horizontal: Int, val depth: Int, val aim: Int) {
    infix fun increaseHorizontal(horizontal: Int) =
        PositionWithAim(this.horizontal + horizontal, this.depth + horizontal * this.aim, this.aim)

    infix fun changeAim(aim: Int) = this.copy(aim = this.aim + aim)
    fun mutiply() = horizontal * depth
}
