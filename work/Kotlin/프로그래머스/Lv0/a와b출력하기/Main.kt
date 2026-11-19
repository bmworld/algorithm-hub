package 프로그래머스.Lv0.a와b출력하기

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 4
private const val OBS = 1 shl 5
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

private val aEqual = "a = ".toByteArray()
private val bEqual = "b = ".toByteArray()
private val NL = 10
fun main(args: Array<String>) {
  val a = i()
  val b = i()

  O.write(aEqual)
  w(a)
  O.write(NL)
  O.write(bEqual)
  w(b)
  O.flush()
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (140.50ms, 51.3MB)
 * 테스트 2 〉	통과 (127.60ms, 51MB)
 * 테스트 3 〉	통과 (142.12ms, 51.4MB)
 * 테스트 4 〉	통과 (136.34ms, 50.8MB)
 * 테스트 5 〉	통과 (158.01ms, 50.8MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
