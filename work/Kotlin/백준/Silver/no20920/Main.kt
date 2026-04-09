package 백준.Silver.no20920

import java.io.*

const val IBS = 1 shl 20
const val OBS = 1 shl 20
val O = BufferedWriter(OutputStreamWriter(System.out), OBS)
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

const val a: Byte = 97
val reader = StringBuilder()
fun s(): String {
  var b: Byte
  while (r().also { b = it } >= a) reader.append(b.toInt().toChar())
  val s = reader.toString()
  reader.setLength(0)
  return s
}

fun main() {
  val N = i()
  val M = i()
  val map = HashMap<String, Int>()
  var total = 0
  var maxCnt = 0
  var OBS = 0
  repeat(N) {
    val s = s()
    val len = s.length
    if (len < M) return@repeat
    val cnt = 1 + map.getOrDefault(s, 0).also { if (it == 0) total++ }
    map[s] = cnt
    if (cnt > maxCnt) maxCnt = cnt
    OBS += len + 1
  }

  val note = Array(maxCnt + 1) { mutableListOf<String>() }
  for ((w, cnt) in map) note[cnt] += w

  val ranks = Array<String>(total) { "" }
  repeat(maxCnt) {
    val cnt = maxCnt - it
    if (cnt == 0) return@repeat
    val strs = note[cnt]

    val len = strs.size
    for (i in 0 until len) {
      var rank = 0
      val w1 = strs[i]
      val l1 = w1.length
      for (j in 0 until len) {
        if (i == j) continue
        val w2 = strs[j]
        val l2 = w2.length
        if (l1 > l2) continue
        if (l1 == l2 && w1 < w2) continue
        rank++
      }
      ranks[rank] = w1
    }

    repeat(len) {
      O.write(ranks[it])
      O.write(10)
    }
  }

  O.flush()
}
