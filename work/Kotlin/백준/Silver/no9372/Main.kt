package 백준.Silver.no9372

import java.io.BufferedInputStream

const val IBS = 1 shl 17
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}


fun main() {
  val MAX_PATH = 10_000
  val q = IntArray(MAX_PATH)

  repeat(i()) {
    var cnt = 0
    val N = i()
    val graph = Array(N + 1) { mutableListOf<Int>() }

    repeat(i()) {
      val fr = i()
      val to = i()
      val ch = BooleanArray(N + 1)

      var existed = false

      var qh = 0
      var qt = 0
      q[qt++] = fr
      ch[fr] = true

      bfs@ while (qh < qt) {
        val node = q[qh++]
        for (n in graph[node]) {
          if (n == to) {
            existed = true
            break@bfs
          }
          if (ch[n]) continue
          ch[n] = true
          q[qt++] = n
        }
      }

      if (!existed) {
        graph[fr] += to
        graph[to] += fr
        cnt++
      }
    }

    println(cnt)
  }
}
