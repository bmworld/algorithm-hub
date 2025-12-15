package 백준.Gold.no14402

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 6_000
private const val OBS = 1_000
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

private const val WS = 10
private val WB = ByteArray(WS + 1)
private fun w(
  num: Int,
  nl: Boolean,
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  WB[WS] = (if (nl) '\n' else ' ').code.toByte()
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  pos++
  O.write(WB, pos, WS - pos + 1)
}

fun main() {

  val end = i()
  val a = IntArray(end)
  repeat(end) {
    a[it] = i()
  }

  val tmp = IntArray(end)
  val best = IntArray(end)
  var bestLen = 0
  fun dfs(
    pos: Int,
    stt: Int,
  ) {
    val remain = end - stt
    if (pos + remain <= bestLen) return

    if (pos > bestLen) {
      bestLen = pos
      var k = 0
      while (k < pos) {
        best[k] = tmp[k]
        k++
      }
    }

    repeat(remain) {
      val i = stt + it
      tmp[pos] = a[i]
      val tv = tmp[pos]
      if (pos > 0 && tv <= tmp[pos - 1]) return@repeat
      dfs(pos + 1, i + 1)
    }
  }

  dfs(0, 0)
  w(bestLen, true)
  repeat(bestLen) {
    w(best[it], false)
  }

  O.flush()
}

// println("-------------- pos=$pos, remain=$remain bestLen=$bestLen")
