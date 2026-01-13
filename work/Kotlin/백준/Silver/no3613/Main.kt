package 백준.Silver.no3613

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 10
private const val OBS = 1 shl 10
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

private const val MAX_LEN = 200
private const val NL: Byte = 10
private val JAVA_IDF = 65..90
private const val CPP_IDF: Byte = 95
private val ERROR = byteArrayOf(69, 114, 114, 111, 114, 33)
private const val CASE_SPACING = 32
fun main() {
  val typed = ByteArray(MAX_LEN)
  var b: Byte
  var trigger = false
  var error = false
  var changed = 0
  var len = 0
  while (r().also { b = it } >= 10) {
    if (b == NL) break
    when {
      len == 0 && (b in JAVA_IDF || b == CPP_IDF) -> error = true
      b in JAVA_IDF -> {
        typed[len++] = CPP_IDF
        typed[len++] = (b + CASE_SPACING).toByte()
        changed++
      }
      b == CPP_IDF -> {
        trigger = true
        changed++
      }
      else -> {
        typed[len++] = (b - if (trigger) {
          trigger = false
          CASE_SPACING
        } else 0).toByte()
      }
    }
  }

  if (error || changed == 0) O.write(ERROR)
  else O.write(typed, 0, len)

  O.flush()
}

/**

### CASE: CPP
--- 첫
_a

--- 끝
a_

--- 연속
a__a



### CASE: JAVA
--- 첫
Asb

--- 혼합
aH_a
a_aH



### CASE: 정상
asd
aAAAA
a_a_a_a

 */
