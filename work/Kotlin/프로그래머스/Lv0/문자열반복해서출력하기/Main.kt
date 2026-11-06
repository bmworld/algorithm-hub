package 프로그래머스.Lv0.문자열반복해서출력하기

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 10
private const val OBS = 50
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

private fun i(): Int {
  var b: Byte

  var v = 0
  while (r().also { b = it } in NUM) v = v * 10 + b - ZERO
  return v
}

const val MAX_LEN = 10
const val NL: Byte = 10
const val SPACE: Byte = 32
const val ZERO: Byte = 48
private val NUM = 48..57
fun main(args: Array<String>) {
  var b: Byte
  val str = ByteArray(MAX_LEN)
  var i = 0
  while (r().also { b = it } != SPACE) str[i++] = b
  repeat(i()) { O.write(str, 0, i) }
  O.flush()
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (60.05ms, 51.3MB)
 * 테스트 2 〉	실패 (런타임 에러)
 * 테스트 3 〉	통과 (62.08ms, 51.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
