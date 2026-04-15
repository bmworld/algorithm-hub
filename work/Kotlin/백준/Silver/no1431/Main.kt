package 백준.Silver.no1431

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 10
val O = BufferedOutputStream(System.out, OBS)
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val NL: Byte = 10
const val ZERO: Byte = 48
const val MAX_LEN = 50
fun main() {
  val a = Array(MAX_LEN + 1) { mutableListOf<Pair<Int, ByteArray>>() }

  var b: Byte
  val reader = ByteArray(MAX_LEN)
  var min = MAX_LEN
  var max = 0
  val cnt = i()
  repeat(cnt) {
    var i = 0
    var num = 0
    while (r().also { b = it } >= ZERO) reader[i++] = b.also { if (it in NUM) num += b - ZERO }
    a[i].add(Pair(num, reader.clone()))
    if (i < min) min = i
    if (i > max) max = i
  }

  val out = ByteArray(cnt * (max + 1))
  repeat(max - min + 1) {
    var len = min + it
    val arr = a[len]
    val cnt = arr.size
    if (cnt == 0) return@repeat
    repeat(cnt) { i ->
      var rank = 0
      val (n1, s1) = arr[i]
      repeat(cnt) { j ->
        if (i == j) return@repeat
        val (n2, s2) = arr[j]
        if (n1 < n2) return@repeat
        if (n1 > n2) {
          rank++
          return@repeat
        }

        for (k in 0 until len) {
          val b1 = s1[k]
          val b2 = s2[k]
          if (b1 == b2) continue
          if (b1 > b2) rank++
          break
        }
      }

      val stt = pos(rank, 0, len + 1)
      System.arraycopy(s1, 0, out, stt, len)
      out[stt + len] = NL
    }
    O.write(out, 0, cnt * (len + 1))
  }

  O.flush()
}


fun pos(r: Int, c: Int, CAP: Int): Int = r * CAP + c
