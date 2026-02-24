package 백준.Bronze.no11719

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val OBS = 1 shl 15
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
fun main() {
  O.write(I.readBytes())
  O.flush()
}
