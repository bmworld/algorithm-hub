package 백준.Silver.no1406

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 300_000
const val OBS = 1 shl 12
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val L: Byte = 76
const val D: Byte = 68
const val B: Byte = 66
const val P: Byte = 80
val LOWER_CASE = 97..122
fun getChar(): Byte {
  var b: Byte
  var char: Byte = 0
  while (r().also { b = it } >= 65) char = b
  return char
}

const val EMPTY = -1
const val MAX_LEN = 600_000
fun main() {
  var str = ByteArray(MAX_LEN + 1)
  val prv = IntArray(MAX_LEN + 1) { EMPTY }
  val nxt = IntArray(MAX_LEN + 1) { EMPTY }
  var len = 0
  val EOL = len++
  str[EOL] = 47

  var c: Byte
  while (r().also { c = it } in LOWER_CASE) {
    str[len] = c
    if (len > 1) prv[len] = (len - 1).also { nxt[it] = len }
    len++
  }

  prv[EOL] = (len - 1).also { nxt[it] = EOL }
  var cur = EOL

  repeat(i()) {
    val op = getChar()
    when (op) {
      L -> prv[cur].also { if (it != EMPTY) cur = it }
      D -> nxt[cur].also { if (it != EMPTY) cur = it }
      B -> {
        val p = prv[cur]
        if (p != EMPTY) {
          val pp = prv[p]
          prv[cur] = if (pp != EMPTY) {
            nxt[pp] = cur
            pp
          } else EMPTY
        }
      }
      P -> {
        str[len] = getChar()
        nxt[len] = cur
        prv[cur].also {
          if (it != EMPTY) {
            nxt[it] = len
            prv[len] = it
          }
        }
        prv[cur] = len
        len++
      }
    }
  }

  var out = ByteArray(len)
  var traced = EOL
  var j = len - 1
  while (prv[traced] != EMPTY) {
    val p = prv[traced]
    out[j--] = str[p]
    traced = p
  }

  val offset = j + 1
  O.write(out, offset, len - offset)
  O.flush()
}

//    println("------ [${toChar(op)}] cur = $cur (prv=${prv[cur]}, nxt=${nxt[cur]})")
