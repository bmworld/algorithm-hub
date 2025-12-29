import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 256
private const val OBS = 64
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
  pos++
  O.write(WB, pos, WS - pos)
}

private const val C: Byte = 99
private const val D: Byte = 100
private const val L: Byte = 108
private const val N: Byte = 110
private const val S: Byte = 115
private const val Z: Byte = 122
private const val J: Byte = 106
private const val EQ: Byte = 61
private const val HP: Byte = 45
private const val END: Byte = 10
private const val MAX_LEN = 100

fun main() {
  var totalCnt = 0
  var c: Byte

  val str = ByteArray(MAX_LEN)
  var l = 0
  var r = 0

  while (r().also { c = it } >= END) {
    val diff = r - l
    if (c == END) {
      totalCnt += diff
      break
    }

    str[r] = c
    var done = false
    var nextStep = false
    var cnt = 0

    when (diff) {
      0 -> if (!isStep1(c)) {
        cnt += 1
        done = true
      } else {
        nextStep = true
      }

      1 -> {
        val prev = str[r - diff]
        if (isStep2(prev, c)) {
          done = true
          cnt += 1
        } else if (prev == D && c == Z) {
          nextStep = true
        } else if (isStep1(c)) {
          cnt += diff
        } else {
          done = true
          cnt += diff + 1
        }
      }

      2 -> {
        if (isStep3(str[r - diff], str[r - diff + 1], c)) {
          done = true
          cnt += 1
        } else if (isStep1(c)) {
          cnt += diff
        } else {
          done = true
          cnt += diff + 1
        }
      }
    }

    r++
    totalCnt += cnt
    if (done) l = r
    else if (!nextStep) l += diff
  }

  w(totalCnt)
  O.flush()
}

fun isStep1(v1: Byte): Boolean {
  return when (v1) {
    C, D, L, N, S, Z -> true
    else -> false
  }
}

fun isStep2(
  v1: Byte,
  v2: Byte,
): Boolean {
  return v1 == C && (v2 == EQ || v2 == HP) || v1 == D && (v2 == HP) || v1 == L && (v2 == J) || v1 == N && (v2 == J) || v1 == S && (v2 == EQ) || v1 == Z && (v2 == EQ)
}


fun isStep3(
  v1: Byte,
  v2: Byte,
  v3: Byte,
): Boolean {
  return v1 == D && v2 == Z && v3 == EQ
}