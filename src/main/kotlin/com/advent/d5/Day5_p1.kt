package com.advent

import kotlin.math.absoluteValue

/**
 * @author Mateusz Becker
 */
fun main() {
    val data = Day5.exampleData()
    val size = 10

//        val data = readLinesFromFile("input_d5.txt")
//val size  = 1000

    data class Coord(val x: Int, val y: Int)
    data class Line(val coordStart: Coord, val coordEnd: Coord){
//    data class Line(val coordStart: Coord, val coordEnd: Coord){
//        companion object {
                fun normalize(): Line {
                    var x1 = coordStart.x
                    var x2 = coordEnd.x
                    var y1 = coordStart.y
                    var y2 = coordEnd.y
                    if(x1 > x2) {
                        x1 = x2
                        x2 = coordStart.x
                    }
                    if(y1> y2) {
                        y1 = y2
                        y2 =coordStart.y
                    }
    return Line(Coord(x1,y1),Coord(x2,y2))
                }
//        }
    }

    val lines = data
        .asSequence()
        .flatMap {
            it
                .split(" -> ")
                .asSequence()
                .flatMap { coords ->
                    coords.split(",").zipWithNext { x, y -> Coord(x.toInt(), y.toInt()) }
                }.zipWithNext { coordStart, coordEnd -> Line(coordStart, coordEnd) }
//            split.last().split(",")
//        Line(split.fi)
        }.toList()

    val horAndVerLines = lines.filter { l ->
        l.coordStart.x == l.coordEnd.x || l.coordStart.y == l.coordEnd.y
                ||
                (l.coordEnd.x - l.coordStart.x).absoluteValue == (l.coordEnd.y - l.coordStart.y).absoluteValue
    }

//    println(lines)
    println(horAndVerLines)
//    println(horAndVerLines.size)

    val map = Array(size) { Array(size) { 0 } }
//    for(x in 0 until size){
//        for (y in  0 until size){
//            print(map[x][y])
//        }
//        println()
//    }

//    horAndVerLines.forEach { l ->
////        println("robie line $l")
//        if (l.coordStart.x == l.coordEnd.x) {
////            println("dla x ${l.coordStart.x}")
//            val range = if(l.coordStart.y > l.coordEnd.y) l.coordStart.y downTo l.coordEnd.y else l.coordStart.y..l.coordEnd.y
//            for (i in range ) {
////                println("ustawiam y ${i}")
//                map[l.coordStart.x][i]++
//            }
//        }
//        if (l.coordStart.y == l.coordEnd.y) {
////            println("dla y ${l.coordStart.y}")
//            val range = if(l.coordStart.x > l.coordEnd.x) l.coordStart.x downTo l.coordEnd.x else l.coordStart.x..l.coordEnd.x
//            for (i in range) {
////                println("ustawiam x ${i}")
//                map[i][l.coordStart.y]++
//            }
//        }
//    }

    horAndVerLines.forEach { l ->
        println(l)
        val rangeX = if(l.coordStart.x > l.coordEnd.x) l.coordStart.x downTo l.coordEnd.x else l.coordStart.x..l.coordEnd.x
        val rangeY = if(l.coordStart.y > l.coordEnd.y) l.coordStart.y downTo l.coordEnd.y else l.coordStart.y..l.coordEnd.y
        for(x in rangeX){
//        for(x in l.coordStart.x..l.coordEnd.x){
            for (y in rangeY){
//            for (y in l.coordStart.y..l.coordEnd.y){
    println("$x,$y")
                map[x][y]++
            }
        }
    }

    var answer = 0
//    for (x in size - 1 downTo  0) {
    for (x in 0 until size) {
//        for (y in size - 1 downTo  0) {
        for (y in 0 until size) {
            val i = map[x][y]
            print(i)
            if(i>1) answer++
        }
        println()
    }
    println(answer)
}

object Day5 {
    fun exampleData() = """
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
""".trimIndent()
        .split("\n")
}
