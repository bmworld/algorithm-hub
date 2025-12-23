import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 36
private const val OBS = 36
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

private const val MAX_LEN = 50
fun main() {
  var n = i()
  val b = i()
  val W = ByteArray(MAX_LEN)
  var i = MAX_LEN - 1
  while (n != 0) {
    val r = n % b
    W[i--] = (r + if (r < 10) 48 else 55).toByte()
    n /= b
  }
  i++
  
  O.write(W, i, MAX_LEN - i)
  O.flush()
}