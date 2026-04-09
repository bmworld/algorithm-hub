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
  val map = HashMap<String, Int>()
  var totalLen = 0
  repeat(N) {
    val s = s()
    val len = s.length
    if (len < M) return@repeat
    map[s] = 1 + map.getOrDefault(s, 0)
    totalLen += len + 1
  }

  val sorted = map.entries.sortedWith { a, b ->
    when {
      a.value != b.value -> b.value - a.value
      a.key.length != b.key.length -> b.key.length - a.key.length
      else -> a.key.compareTo(b.key)
    }
  }

  val O = StringBuilder(totalLen)
  for (x in sorted) O.appendLine(x.key)
  print(O)
}
