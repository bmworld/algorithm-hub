package 백준.Silver.no5637

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 12
const val OBS = 100
val O = BufferedOutputStream(System.`out`, OBS)
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

const val MAX_LEN = 100
const val HYPHEN: Byte = 45
const val E: Byte = 69
const val N: Byte = 78
const val D: Byte = 68

const val A: Byte = 65
const val Z: Byte = 90
const val CASE_OFFSET: Byte = 32
val UPPWER_CASE = A..Z
val LOWER_CASE = A + CASE_OFFSET..Z + CASE_OFFSET
fun main() {
  val word = ByteArray(MAX_LEN)
  var maxLen = 0

  val tmp = ByteArray(MAX_LEN)
  var len = 0

  var b: Byte
  while (r().also { b = it } >= 10.toByte()) {
    when {
      b in UPPWER_CASE || b in LOWER_CASE || b == HYPHEN -> tmp[len++] = b
      else -> {
        if (len == 5
          && tmp[0] == E
          && tmp[1] == HYPHEN
          && tmp[2] == N
          && tmp[3] == HYPHEN
          && tmp[4] == D
        ) break
        else if (maxLen < len) {
          maxLen = len
          repeat(len) {
            word[it] = toLowerCase(tmp[it])
          }
        }
        len = 0
      }
    }
  }

  O.write(word, 0, maxLen)
  O.flush()
}

fun toLowerCase(b: Byte): Byte = (if (b in UPPWER_CASE) b + CASE_OFFSET else b).toByte()
