package 백준.Silver.no1181

import java.io.BufferedInputStream
import java.util.*

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val wBuf = StringBuilder(64)
val CPT =
    Comparator<String> { a, b ->
      val d = a.length - b.length
      if (d != 0) d else a.compareTo(b)
    }

fun main() {
  val n = readInt()
  val set = TreeSet(CPT)
  var totalLen = n
  repeat(n) {
    wBuf.clear()
    var curLen = 0
    var c = IN.read()
    while (c <= 32) c = IN.read()
    while (c in 97..122) {
      val toChar = c.toChar()
      wBuf.append(toChar)
      c = IN.read()
      curLen++
    }
    if (set.add(wBuf.toString())) totalLen += curLen + 1
  }

  val r = StringBuilder(totalLen)
  for (s in set) r.appendLine(s)
  print(r)
}

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in 48..57) {
    n = n * 10 + (c - 48)
    c = IN.read()
  }
  return n
}
