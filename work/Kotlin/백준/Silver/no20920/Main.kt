package 백준.Silver.no20920

import java.io.BufferedInputStream

const val IBS = 1 shl 20
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
  val words = LinkedHashMap<String, Int>()
  var total = 0
  repeat(N) {
    val s = s()
    if (s.length < M) return@repeat
    words[s] = 1 + words.getOrDefault(s, 0).also { if (it == 0) total++ }
  }

  val OB = StringBuilder(total)
  var OBS = 0
  val ranks = Array<String>(total) { "" }
  for ((w1, c1) in words) {
    var rank = 0
    val l1 = w1.length
    for ((w2, c2) in words) {
      if (w1 == w2) continue
      if (c1 > c2) continue
      if (c1 == c2) {
        val l2 = w2.length
        if (l1 > l2) continue
        if (l1 == l2 && w1 < w2) continue
      }
      rank++
    }
    ranks[rank] = w1
    OBS += l1
  }

  for (word in ranks) OB.appendLine(word)
  print(OB)
}
