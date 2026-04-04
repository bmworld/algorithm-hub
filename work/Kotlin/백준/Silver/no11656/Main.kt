package 백준.Silver.no11656

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1_000
const val OBS = 1 shl 14
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

const val a: Byte = 97
const val z: Byte = 122
const val ALPHABETS = 26
const val MAX_LEN = 1_000

fun main() {
  var S = ByteArray(MAX_LEN)
  var SLen = 0

  var b: Byte
  val charPoses = Array(ALPHABETS) { mutableListOf<Int>() }
  while (r().also { b = it } >= a) {
    charPoses[b - a] += SLen
    S[SLen++] = b
  }

  repeat(ALPHABETS) { char ->
    val poses = charPoses[char]
    val len = poses.size
    if (len == 0) return@repeat

    val ranks = IntArray(len)
    repeat(len) { i ->
      val p1 = poses[i]
      var rank = 0
      loop@ for (j in 0 until len) {
        if (i == j) continue
        val p2 = poses[j]
        val maxDelta = SLen - 1 - maxOf(p1, p2)
        for (delta in 1..maxDelta) {
          val c1 = S[p1 + delta]
          val c2 = S[p2 + delta]
          if (c1 == c2) continue
          if (c1 > c2) rank++
          continue@loop
        }

        if (p1 < p2) rank++
      }
      ranks[rank] = p1
    }

    repeat(len) {
      val pos = ranks[it]
      O.write(S, pos, SLen - pos)
      O.write(10)
    }
  }

  O.flush()
}

//println("-- S[$p1] = ${toChar(S[p1])} -> $rank")

/**
IN
ohho

OUT
hho
ho
o
ohho

IN
dijjq

OUT
dijjq
ijjq
jjq
jq
q
 */
