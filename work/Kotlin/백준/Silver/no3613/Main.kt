package 백준.Silver.no3613

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 8
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
  var b: Byte

  val typed = ByteArray(MAX_LEN)
  var len = 0

  var error = false
  var javaFormat = false
  var cppFormat = false
  var cppTrigger = false

  while (r().also { b = it } >= 10) {
    if (b == NL) break
    if (error) continue
    when {
      len == 0 && (b in JAVA_IDF || b == CPP_IDF) || (cppTrigger && b == CPP_IDF)
      -> error = true
      b in JAVA_IDF -> {
        typed[len++] = CPP_IDF
        typed[len++] = (b + CASE_SPACING).toByte()
        javaFormat = true
      }
      b == CPP_IDF -> {
        cppTrigger = true
        cppFormat = true

      }
      else -> {
        typed[len++] = (b - if (cppTrigger) {
          cppTrigger = false
          CASE_SPACING
        } else 0).toByte()
      }
    }
  }

  if (error || cppTrigger || javaFormat && cppFormat) O.write(ERROR)
  else O.write(typed, 0, len)

  O.flush()
}

/**

### CASE: 첫 IDF (CPP, JAVA)
_a
Aa

### CASE: 끝 IDF (CPP)
a_

### CASE: 중복 IDF (CPP)
a__a


### CASE: 혼합 IDF
aA_a
a_Aa
a_bB

### CASE: 정상
a
aAA
a_a_a

 */
