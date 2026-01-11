import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 18
private val O = BufferedOutputStream(System.out, OBS)
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


private val SPACES = byteArrayOf(
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
  42, 32, 42, 32, 42, 32, 42, 32, 42, 32,
)

fun main() {
  val n = i()
  var pos = 1
  while (pos <= n * 2) {
    val isOdd = pos % 2 == 1
    val stt = if (isOdd) 0 else 1
    val end = n + stt + if (isOdd) 0 else -1
    O.write(SPACES, stt, end)
    O.write(10)
    pos++
  }
  O.flush()
}