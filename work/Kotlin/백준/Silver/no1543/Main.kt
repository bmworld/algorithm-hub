package 백준.Silver.no1543

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 2_501
const val OBS = 1 shl 4
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
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

const val MAX_DOC_LEN = 2_500
const val MAX_WORD_LEN = 50
const val NL: Byte = 10
fun main() {
  var b: Byte
  val doc = ByteArray(MAX_DOC_LEN)
  var docLen = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    doc[docLen++] = b
  }

  val word = ByteArray(MAX_WORD_LEN)
  var wordLen = 0
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    word[wordLen++] = b
  }

  var cnt = 0
  var i = 0
  repeat(docLen) {
    val b = doc[it]
    if (b == word[i]) {
      if (++i == wordLen) {
        cnt++
        i = 0
      }
    } else {
      var found = false
      search@ while (i > 0) {
        if (b == word[--i]) {
          found = true
          var wi = i - 1
          var di = it - 1
          while (i > 0 && wi >= 0 && di >= 0) if (word[wi--] != doc[di--]) continue@search
          break@search
        }
      }
      if (found) i++
    }
  }

  w(cnt)
  O.flush()
}

/**
IN
c  cc cc    ccc
c cc
OUT
1

IN
> c  c     cc
>  cc
OUT
1

IN
aab
ab
OUT
1

IN
aaab
ab
OUT
1


IN
aaaaaaaaaaaab
aaaaaaaab
OUT
1

IN
ababc
abc
OUT
1

IN
abababababababababc
ababababababababc
OUT
1
 */
