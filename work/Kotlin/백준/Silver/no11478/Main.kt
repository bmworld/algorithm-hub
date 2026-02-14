package 백준.Silver.no11478

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
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

const val MAX_LEN = 1000
const val NL: Byte = 10

fun main() {
  var b: Byte
  var a = ""
  while (r().also { b = it } >= NL) {
    if (b == NL) break
    a += b.toInt().toChar().toString()
  }

  val ch = HashSet<String>()
  var cnt = 0
  val strLen = a.length
  for (len in 1..strLen) {
    repeat(strLen - len + 1) { l ->
      val str = a.substring(l, l + len)
      if (isUnique(ch, str)) cnt++
    }
  }

  w(cnt)
  O.flush()
}

fun isUnique(ch: HashSet<String>, str: String): Boolean {
  val bool = !ch.contains(str)
  if (bool) ch.add(str)
  return bool
}
