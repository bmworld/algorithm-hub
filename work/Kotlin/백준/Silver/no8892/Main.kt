package 백준.Silver.no8892

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 16
const val OBS = 1 shl 8
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

val NUM = 48..57
fun i(): Int {
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

const val NL: Byte = 10
const val IMPOSSIBLE = 48
const val MAX_WORD_LEN = 10_000

fun main() {
  var b: Byte
  val reader = ByteArray(MAX_WORD_LEN)
  val WORDS = mutableListOf<ByteArray>()

  repeat(i()) {
    val cnt = i()
    repeat(cnt) {
      var len = 0
      while (r().also { b = it } >= NL) {
        if (b == NL) break
        reader[len++] = b
      }
      WORDS += reader.copyOfRange(0, len)
    }

    var found = false
    search@ for (i in 0 until cnt) {
      for (j in 0 until cnt) {
        if (i == j) continue
        var isPalindrome = true

        val a = WORDS[i]
        val b = WORDS[j]
        val aLen = a.size
        val bLen = b.size
        val len = aLen + bLen
        for (li in 0 until len / 2) {
          val l = if (li < aLen) a[li] else b[li - aLen]
          val ri = len - 1 - li
          val r = if (ri < aLen) a[ri] else b[ri - aLen]
          if (l != r) {
            isPalindrome = false
            break
          }
        }
        if (isPalindrome) {
          found = true
          O.write(a)
          O.write(b)
          break@search
        }
      }
    }

    if (!found) O.write(IMPOSSIBLE)
    O.write(10)
    WORDS.clear()
  }

  O.flush()
}

//      println("[$dep] $i -> pos=$pos, len=$len")
//        println("${toChar(reader[l])} ($l) vs ${toChar(reader[r])}($r)")
