package 백준.Silver.no1244

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
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

const val WS = 10
val WB = ByteArray(WS + 1)
fun w(
  num: Int,
  end: Boolean = false
) {
  WB[WS] = if (end) 10 else 32

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
  O.write(WB, ++pos, WS - pos + 1)
}


fun main() {
  val N = i()
  val switch = BooleanArray(N + 1).also {
    repeat(N) { i ->
      it[i + 1] = i() == 1
    }
  }

  repeat(i()) {
    val sex = i()
    val v = i()
    when (sex) {
      1 -> for (i in v..N step v) {
        switch[i] = !switch[i]
      }
      else -> {
        switch[v] = !switch[v]
        for (r in v + 1..N) {
          val delta = r - v
          val l = v - delta
          if (l < 1) break
          val lb = switch[l]
          val rb = switch[r]
          if (lb != rb) break
          switch[l] = !lb
          switch[r] = !rb
        }
      }
    }
  }

  repeat(N) {
    val i = it + 1
    w(if (switch[i]) 1 else 0, i % 20 == 0)
  }

  O.flush()
}
