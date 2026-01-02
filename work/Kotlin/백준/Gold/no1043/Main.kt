package 백준.Gold.no1043

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 13
private const val OBS = 128
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = BufferedInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
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

private const val WS = 10
private val WB = ByteArray(WS)
private fun w(
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
  val M = i()
  val map = HashMap<Int, Boolean>()
  repeat(i()) {
    map[i()] = true
  }

  val parties = Array(M) { IntArray(N + 1) }
  val partyIdxs = IntArray(M)
  var chi = 0

  repeat(M) { i ->
    var spreadTruth = false
    val cnt = i()
    parties[i][0] = cnt
    repeat(cnt) { j ->
      val v = i()
      if (map[v] == true) spreadTruth = true
      parties[i][j + 1] = v
    }

    if (spreadTruth) {
      repeat(cnt) { j ->
        map[parties[i][j + 1]] = true
      }
    } else {
      partyIdxs[chi++] = i
    }
  }

  var result = 0
  repeat(chi) {
    val party = parties[partyIdxs[it]]
    val cnt = party[0]
    var spreadTruth = false
    for (i in 1..cnt) {
      if (map[party[i]] == true) {
        spreadTruth = true
        break
      }
    }
    if (!spreadTruth) result++
  }

  w(result)
  O.flush()
}

//  for (e in map) {
//    println("entry = ${e.key} ${e.value}")
//  }
//
//  repeat(M) { i ->
//    val cnt = parties[i][0]
//    repeat(cnt) { j ->
//      println("parties[$i][${j + 1}]=${parties[i][j + 1]}")
//    }
//  }
