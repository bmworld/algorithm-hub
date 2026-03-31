package 백준.Silver.no6603

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 10
const val OBS = 1 shl 17
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

const val MAX = 12
const val NL = 10
const val GOAL = 6
fun main() {
  val cnds = IntArray(MAX)
  val ch = IntArray(MAX)

  fun dfs(dep: Int, stt: Int, end: Int) {
    if (dep == GOAL) {
      repeat(GOAL) {
        w(ch[it], it + 1 == GOAL)
      }
      return
    }

    for (i in stt..end - (GOAL - 1) + dep) {
      ch[dep] = cnds[i]
      dfs(dep + 1, i + 1, end)
    }
  }

  var cnt = 0
  while (i().also { if (cnt == 0) cnt = it } > 0) {
    repeat(cnt) {
      cnds[it] = i()
    }

    dfs(0, 0, cnt - 1)
    O.write(NL)
    cnt = 0
  }

  O.flush()
}
