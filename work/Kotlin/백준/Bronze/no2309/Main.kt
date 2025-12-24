package 백준.Bronze.no2309

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1_000
private const val OBS = 1_000
private val O = BufferedOutputStream(System.`out`, OBS)
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
private val WB = ByteArray(WS + 1).also { it[WS] = 10 }
private fun w(
  num: Int,
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
  pos++
  O.write(WB, pos, WS - pos + 1)
}

private const val TOTAL_CNT = 9
fun main() {

  val a = IntArray(TOTAL_CNT)
  var total = 0
  repeat(TOTAL_CNT) {
    val v = i()
    var i = it
    while (i > 0) {
      val t = a[i - 1]
      if (t > v) a[i--] = t
      else break
    }
    a[i] = v
    total += v
  }

  var found = false
  val ch = BooleanArray(TOTAL_CNT)
  fun dfs(
    dep: Int,
    stt: Int,
    acc: Int,
  ) {
    if (acc < 100 || dep > 2) return
    if (acc == 100) {
      repeat(TOTAL_CNT) {
        if (!ch[it]) w(a[it])
      }
      found = true
      return
    } else if (dep == 2) return

    for (i in stt until stt + TOTAL_CNT - 1) {
      if (found) break
      if (ch[i]) continue
      ch[i] = true
      dfs(dep + 1, stt + 1, acc - a[i])
      ch[i] = false
    }
  }

  dfs(0, 0, total)
  O.flush()
}
