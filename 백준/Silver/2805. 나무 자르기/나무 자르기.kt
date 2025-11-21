import java.io.BufferedOutputStream
import java.io.DataInputStream

private val O = BufferedOutputStream(System.`out`, 1 shl 12)
private const val IBS = 1 shl 14
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private const val EOF = -1
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

private fun i(): Int {
  var n = 0
  var c: Byte
  while (r().also { c = it } in 48..57) n = n * 10 + (c - 48)
  return n
}

private const val OBS = 10
private val OB = ByteArray(OBS)

private fun w(
  num: Int,
) {
  var x = num
  var end = OBS - 1
  do {
    OB[end--] = ((x % 10) + 48).toByte()
    x /= 10
  } while (x > 0)
  val stt = end + 1

  O.write(
    OB, stt, OBS - stt
  )
}

fun main() {
  val n = i()
  val t = i()
  val a = IntArray(n)
  var l = 0
  var r = 0
  repeat(n) {
    val v = i()
    a[it] = v
    if (v > r) r = v
  }

  var max = 0
  while (l <= r) {
    var sum = 0L
    val m = (l + r) / 2
    repeat(n) {
      val v = a[it]
      if (v > m) sum += v - m
    }
    if (sum >= t) {
      max = m
      l = m + 1
    } else {
      r = m - 1
    }
  }

  w(max)
  O.flush()
}