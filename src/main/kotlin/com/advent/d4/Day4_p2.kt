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

    println(chosenNumbers)
    chosenNumbers
        .flatMap { number ->
            index[number] ?: emptyList()
        }
        .takeWhile { cell ->
            cell.marked = true
            val board = boards[cell.boardIndex]!!
            if (!updateBoard(board, cell, row = true)) {
                val allFinished = boards.filterValues { t -> !t.finished }
                    .isEmpty()
                if(allFinished) {
                    val sumUnmarked = board.cells
                        .filterNot { it.marked }
                        .sumBy { it.value }
                    println("Day4 part 2 answer: ${sumUnmarked * cell.value}")
                    return@takeWhile false
                }
            }
            if (!updateBoard(board, cell, row = false)) {
                val allFinished = boards.filterValues { t -> !t.finished }
                    .isEmpty()
                if(allFinished) {
                    val sumUnmarked = board.cells
                        .filterNot { it.marked }
                        .sumBy { it.value }
                    println("Day4 part 2 answer: ${sumUnmarked * cell.value}")
                    return@takeWhile false
                }
            }
            return@takeWhile true
        }
}