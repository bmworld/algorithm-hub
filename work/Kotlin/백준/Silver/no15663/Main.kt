package 백준.Silver.no15663

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 64
private const val OBS = 10_000
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
  WB[WS] = (if (nl) '\n' else ' ').code.toByte()
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val MAX_NUM = 10_000
private const val NOT_USED = -1

fun main() {
  val n = i()
  val m = i()
  val a = IntArray(n)
  val ch = BooleanArray(n)
  val dupCh = Array(m) { IntArray(MAX_NUM + 1) { NOT_USED } }
  val out = IntArray(m)

  repeat(n) {
    val v = i()
    var j = it
    for (i in it - 1 downTo 0) {
      if (a[i] > v) {
        a[i + 1] = a[i]
        j = i
        continue
      }
      j = i + 1
      break
    }
    a[j] = v
  }

  fun dfs(
    dep: Int,
  ) {
    if (dep == m) {
      repeat(m) { dep ->
        val v = out[dep]
        w(v, dep + 1 >= m)
      }
      return
    }

    repeat(n) { i ->
      if (ch[i]) return@repeat
      val v = a[i]
      if (dupCh[dep][v] != NOT_USED && dupCh[dep][v] != i) return@repeat
      ch[i] = true
      dupCh[dep][v] = i
      out[dep] = v
      dfs(dep + 1)
      ch[i] = false
    }

    repeat(n) { i ->
      dupCh[dep][a[i]] = NOT_USED
    }

  }

  dfs(0)
  O.flush()
}
