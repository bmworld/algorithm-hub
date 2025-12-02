import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 4
private const val OBS = 4
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
    WB[end--] = ((v % 10) + 48).toByte()
    v /= 10
  } while (v > 0)
  val stt = end + 1
  O.write(WB, stt, -stt + WS)
}


fun main() {
  val n = i()
  val og = IntArray(2).also {
    it[0] = n / 10
    it[1] = n % 10
  }
  val ch = og.copyOf()
  var cnt = 1
  while (true) {
    val a = ch[0]
    val b = ch[1]
    ch[0] = b
    ch[1] = (a + b) % 10
    if (ch[0] == og[0] && ch[1] == og[1]) break
    cnt++
  }

  w(cnt)
  O.flush()
}