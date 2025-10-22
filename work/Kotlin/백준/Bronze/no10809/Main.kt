package 백준.Bronze.no10809

import java.io.BufferedInputStream

val IN = BufferedInputStream(System.`in`)
const val a = 97
const val z = 122
const val N = 26
const val EMPTY = -1

fun main() {

  val arr = IntArray(N) { EMPTY }
  var ch = IN.read()
  var pos = 0
  while (ch in a..z) {
    val i = ch - a
    if (arr[i] == EMPTY) arr[i] = pos
    ch = IN.read()
    pos++
  }
  val sb = StringBuilder(78)
  for (i in 0 until N) sb.append(arr[i]).append(' ')
  print(sb)
}
