package 백준.Bronze.no1145

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 20
private const val OBS = 100
private const val EOF = -1
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0

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
  var v = num
  var end = WS - 1
  do {
    WB[end--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}

fun main() {
  val a = IntArray(5)
  var i = 0
  repeat(5) {
    a[i++] = i()
  }

  var min = Int.MAX_VALUE
  for (i in 0..2) for (j in i + 1..3) {
    val l1 = lcm(a[i], a[j])
    for (k in j + 1..4) {
      val l2 = lcm(l1, a[k])
      if (l2 < min) min = l2
    }
  }
  w(min)
  O.flush()
}

fun gcd(
  a: Int,
  b: Int,
): Int = if (b == 0) a else gcd(b, a % b)

fun lcm(
  a: Int,
  b: Int,
): Int = a * b / gcd(a, b)
