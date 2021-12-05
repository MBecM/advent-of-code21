package com.advent

/**
 * @author Mateusz Becker
 */
fun main() {
//    val data = Day4.exampleData()
    val data = readLinesFromFile("input_d4.txt")
    val chosenNumbers = data.first()
        .split(",")
        .map { it.toInt() }

    fun extractBoardCells(boardIndex: Int, boardRows: List<String>): List<Cell> {
        return boardRows.flatMapIndexed { y, row ->
            row.trim()
                .split("\\s+".toRegex())
                .map { it.toInt() }
                .mapIndexed { x, value -> Cell(x, y, value, boardIndex, marked = false) }
        }
    }

    val boards = mutableMapOf<Int, Board>()
    val index = data.drop(2)
        .asSequence()
        .filter { it.isNotBlank() }
        .windowed(5, 5)
        .flatMapIndexed { index, boardRows ->
            val cells = extractBoardCells(index, boardRows)
            boards[index] = Board(MutableList(5) { 0 }, MutableList(5) { 0 }, cells, finished = false)
            cells
        }
        .groupBy(Cell::value)

    fun updateBoard(board: Board, cell: Cell, row: Boolean): Boolean {
        val countingList = if (row) board.rows else board.columns
        val i = if (row) cell.row else cell.column
        val count = countingList[i]
        if (count < 4) {
            countingList[i]++
        } else {
            board.finished = true
            println("Board ${board} finished")
            return false
        }
        return true
    }

    fun validateBoard(board: Board, number: Int) {
        if (board.finished) {
            val sumUnmarked = board.cells
                .filterNot { it.marked }
                .sumBy { it.value }
            println("Day4 part 1 answer: ${sumUnmarked * number}")
        }
    }

    println(chosenNumbers)
    chosenNumbers
        .flatMap { number ->
            index[number] ?: emptyList()
        }
        .takeWhile { cell ->
            cell.marked = true
            val board = boards[cell.boardIndex]!!
            if (!updateBoard(board, cell, row = true)) {
                validateBoard(board, cell.value)
                return@takeWhile false
            }
            if (!updateBoard(board, cell, row = false)) {
                validateBoard(board, cell.value)
                return@takeWhile false
            }
            return@takeWhile true
        }
}

data class Board(
    val rows: MutableList<Int>,
    val columns: MutableList<Int>,
    val cells: List<Cell>,
    var finished: Boolean
)

data class Cell(val column: Int, val row: Int, val value: Int, val boardIndex: Int, var marked: Boolean)

object Day4 {
    fun exampleData() = """
7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7
""".trimIndent()
        .split("\n")
}
