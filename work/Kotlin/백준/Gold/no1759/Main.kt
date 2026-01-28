package 백준.Gold.no1759

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 6
private const val OBS = 1 shl 14
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

fun char(): Int {
  var b: Byte
  var char = 0
  while (r().also { b = it } >= a) char = b - a
  return char
}

const val INT = 1
const val ALPH_SIZE = 26
const val a = 97
const val e = 101
const val i = 105
const val o = 111
const val u = 117
const val VOWEL = 1 shl (a - a) or (1 shl (e - a)) or (1 shl (i - a)) or (1 shl (o - a)) or (1 shl (u - a))
const val CONSONANT = ((1 shl ALPH_SIZE) - 1) xor VOWEL
fun main() {
  val L = i()
  val C = i()
  val pw = ByteArray(L + 1).also { it[L] = 10 }
  val char = ByteArray(C)

  var cnds = 0
  repeat(C) {
    cnds = cnds or (INT shl char())
  }

  repeat(C) {
    val r = cnds.countTrailingZeroBits()
    char[it] = (r + a).toByte()
    cnds = pop(cnds, r)
  }

  fun dfs(
    dep: Int,
    flag: Int,
  ) {
    if (dep == L) {
      if (
        (flag and VOWEL).countOneBits() >= 1
        && (flag and CONSONANT).countOneBits() >= 2
      ) O.write(pw)
      return
    }

    for (i in dep until C) {
      val c = char[i]
      val mask = 1 shl (c - a)
      val usedOrDESC = mask.countLeadingZeroBits() >= flag.countLeadingZeroBits()
      if (usedOrDESC) continue

      pw[dep] = c
      dfs(dep + 1, flag or mask)
    }
  }

  dfs(0, 0)
  O.flush()
}

fun pop(
  bit: Int,
  pos: Int
): Int = bit xor (INT shl pos)
