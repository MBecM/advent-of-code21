package com.advent

import java.io.File
import java.io.FileNotFoundException
import java.net.URL
import java.nio.file.Files

/**
 * @author Mateusz Becker
 */
fun readLinesFromFile(filename: String) : List<String> {
    val uri = String::javaClass.javaClass.classLoader.getResource(filename) ?: throw FileNotFoundException(filename)
    return File(uri.toURI()).readLines()
}

fun List<Int>.flipBits() = this.map { if (it == 1) 0 else 1 }

fun List<Int>.bytesToInt() =
    this.joinToString(separator = "") { it.toString() }
        .toInt(2)