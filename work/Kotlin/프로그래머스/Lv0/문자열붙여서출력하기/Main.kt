package 프로그래머스.Lv0.문자열붙여서출력하기

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 22
private const val OBS = 22
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
const val SPACE: Byte = 32
fun main(args: Array<String>) {
  var b: Byte

  val buf = ByteArray(MAX_LEN * 2) { NL }
  var i = 0
  while (r().also { b = it } > NL) {
    if (b != SPACE) buf[i++] = b
  }

  O.write(buf, 0, i)
  O.flush()
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
