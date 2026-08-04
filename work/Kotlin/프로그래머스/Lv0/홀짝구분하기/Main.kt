package 프로그래머스.Lv0.홀짝구분하기

import java.io.BufferedOutputStream
import java.io.DataInputStream


private const val IBS = 1_500
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
private val I = DataInputStream(System.`in`)
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
  pos++
  O.write(WB, pos, WS - pos)
}

val EVEN = " is even".toByteArray()
val ODD = " is odd".toByteArray()

fun main(args: Array<String>) {
  val x = i()

  w(x)
  O.write(if (x % 2 == 0) EVEN else ODD)
  O.flush()
}

/**
 * ```
 * [ME]
 * v1
 * 테스트 1 〉	통과 (58.97ms, 51.3MB)
 * 테스트 2 〉	통과 (69.50ms, 51MB)
 * 테스트 3 〉	통과 (61.76ms, 51.3MB)
 * 테스트 4 〉	통과 (58.73ms, 51.2MB)
 *
 * v2:
 * 테스트 1 〉	통과 (53.28ms, 51.8MB)
 * 테스트 2 〉	통과 (63.95ms, 51.4MB)
 * 테스트 3 〉	통과 (58.78ms, 51.4MB)
 * 테스트 4 〉	통과 (60.24ms, 51MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
//  val s = Solution()
//  validate(s.solution(-4, 7, true), 3)
//  validate(s.solution(-4, 7, false), -11)
}
