package 백준.Silver.no8979

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 14
const val OBS = 1 shl 4
val O = BufferedOutputStream(System.`out`, OBS)
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
}

fun main() {

  val N = i()
  val K = i()
  val NATIONS = Array(N + 1) { IntArray(3) }
  repeat(N) {
    val medal = NATIONS[i()]
    medal[0] = i()
    medal[1] = i()
    medal[2] = i()
  }

  val target = NATIONS[K]
  val g = target[0]
  val s = target[1]
  val b = target[2]

  var rank = 1
  repeat(N) {
    val nation = it + 1
    val medal = NATIONS[nation]
    val gold = medal[0]
    if (gold > g) rank++
    else if (gold == g) {
      val slvr = medal[1]
      if (slvr > s) rank++
      else if (slvr == s) {
        val brnz = medal[2]
        if (brnz > b) rank++
      }
    }
  }
  w(rank)
  O.flush()
}

//    println("$nation -> $rank")
