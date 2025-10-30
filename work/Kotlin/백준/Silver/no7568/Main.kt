package 백준.Silver.no7568

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val CODE_0 = 48
private const val CODE_9 = 57
private const val maxNumSize = 2
private val buf = ByteArray(maxNumSize + 1).also { it[maxNumSize] = ' '.code.toByte() }

fun main() {

  val n = readInt()
  val arr = Array(n) { Person(it, readInt(), readInt()) }
  val ch = arr.copyOf()
  ch.sort()

  var rank = 1
  ch[0].rank = rank
  for (i in 1..<n) {
    val order = i + 1
    val cur = ch[i]
    val pre = ch[i - 1]
    if (cur.w < pre.w && cur.h < pre.h) rank = order
    arr[cur.idx].rank = rank
  }

  for ((idx, p) in arr.withIndex()) writeln(p.rank, idx == n - 1)
  OUT.flush()
}

private class Person(val idx: Int, val w: Int, val h: Int, var rank: Int = 0) : Comparable<Person> {

  override fun compareTo(o: Person): Int {
    val tw = this.w
    val ow = o.w
    return when { // 1. 몸무게 2. 키
      tw == ow -> o.h.compareTo(this.h)
      else -> ow - tw
    }
  }
}

private fun writeln(num: Int, isLast: Boolean) {
  var x = num
  var endIdx = 1
  do {
    buf[endIdx--] = ((x % 10) + CODE_0).toByte()
    x /= 10
  } while (x > 0)
  val stt = endIdx + 1
  val len = maxNumSize - stt + if (isLast) 0 else 1 // 띄어쓰기 포함여부
  OUT.write(buf, stt, len)
}

val IN = BufferedInputStream(System.`in`, 1 shl 20)
val OUT = BufferedOutputStream(System.`out`, 1 shl 20)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n
}
