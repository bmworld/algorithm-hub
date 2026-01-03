import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 16
private const val OBS = 16
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

private const val UP = 0
private const val DOWN = 1
fun main() {

  val N = i()

  var a = 0
  var b = 0

  fun f(
    r: Int,
    c: Int,
    dir: Int,
    cnt: Int,
  ) {
    
    if (cnt <= 1) {
      a = r
      b = c
      return
    }

    var nd = dir
    var nr = r
    var nc = c

    when (dir) {
      UP -> {
        if (r == 1) nd++ else nr--
        nc++
      }

      DOWN -> {
        if (c == 1) nd++ else nc--
        nr++
      }
    }

    f(nr, nc, nd % 2, cnt - 1)
  }

  f(1, 1, UP, N)

  w(a)
  O.write(47)
  w(b)
  O.flush()
}