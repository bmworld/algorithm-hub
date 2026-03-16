package 백준.Silver.no1302

import java.io.BufferedInputStream
import java.util.*

const val IBS = 1 shl 16
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
const val MAX_LEN = 1000
val reader = StringBuilder(MAX_LEN)
fun s(): String {
  var b: Byte
  while (r().also { b = it } >= a) {
    reader.append(b.toInt().toChar())
  }

  val s = reader.toString()
  reader.setLength(0)
  return s
}

fun main() {
  val map = HashMap<String, Int>()
  var maxCnt = 0
  repeat(i()) {
    val str = s()
    val cnt = (map[str] ?: 0) + 1
    map[str] = cnt
    if (cnt > maxCnt) maxCnt = cnt
  }

  val cnds = PriorityQueue<String>()
  for (e in map) if (e.value == maxCnt) cnds.add(e.key)

  print(cnds.poll())
}
