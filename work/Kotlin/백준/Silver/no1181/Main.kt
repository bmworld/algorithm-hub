package 백준.Silver.no1181

import java.io.BufferedInputStream

fun main() {
  val n = readInt()
  val sb = StringBuilder(50)
  val set = sortedSetOf<Word>()
  var totalLen = 0
  repeat(n) {
    sb.clear()
    var curLen = 0
    var c = IN.read()
    while (c <= 32) c = IN.read()
    while (c in 97..122) {
      val toChar = c.toChar()
      sb.append(toChar)
      c = IN.read()
      curLen++
    }
    if (set.add(Word(sb.toString()))) totalLen += curLen + 1
  }
  val r = StringBuilder(totalLen)
  for (wrd in set) {
    r.appendLine(wrd.str)
  }
  print(r)
}

val IN = BufferedInputStream(System.`in`)

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

class Word(val str: String) : Comparable<Word> {
  fun getLen() = str.length

  override fun compareTo(o: Word): Int {
    val curLen = this.getLen()
    val othLen = o.getLen()
    return when {
      curLen == othLen -> this.str.compareTo(o.str)
      else -> curLen - othLen
    }
  }
}
