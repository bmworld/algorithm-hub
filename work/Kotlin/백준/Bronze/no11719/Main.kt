package 백준.Bronze.no11719

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val OBS = 1 shl 7
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
fun main() {
  I.use { i ->
    O.use { o ->
      i.copyTo(o)
      o.flush()
    }
  }
}
