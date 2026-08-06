package 프로그래머스.Lv0.문자열돌리기

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10
private const val OBS = 20
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

const val MAX_LEN = 10
const val NL: Byte = 10
fun main(args: Array<String>) {
  var b: Byte

  val buf = ByteArray(MAX_LEN * 2) { NL }
  var i = 0
  while (r().also { b = it } > NL) {
    buf[i] = b
    i += 2
  }

  O.write(buf, 0, i)
  O.flush()
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (53.00ms, 51MB)
 * 테스트 2 〉	통과 (55.50ms, 50.6MB)
 * 테스트 3 〉	통과 (57.88ms, 50.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * fun main(args: Array<String>) {
 *     val s1 = readLine()!!
 *     s1.map(::println)
 * }
 * 테스트 1 〉	통과 (53.71ms, 51MB)
 * 테스트 2 〉	통과 (54.61ms, 51MB)
 * 테스트 3 〉	통과 (54.20ms, 50.9MB)
 * ```
 */
