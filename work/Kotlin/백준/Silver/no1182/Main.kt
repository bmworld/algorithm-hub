package 백준.Silver.no1182

import java.io.BufferedOutputStream
import java.io.DataInputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 4
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
private val WB = ByteArray(WS)
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
  O.write(WB, ++pos, WS - pos)
}

const val INIT_CNT = 1
fun main() {
  val N = i()
  val S = i()
  val a = IntArray(N) { i() }
  val m1 = HashMap<Int, Int>()
  val m2 = HashMap<Int, Int>()
  m1[0] = INIT_CNT
  m2[0] = INIT_CNT

  fun dfs(
    l: Int,
    r: Int,
    acc: Int,
    cnter: HashMap<Int, Int>,
  ) {
    repeat(r - l + 1) {
      val i = l + it
      val v = a[i]
      val sum = v + acc
      cnter[sum] = (cnter[sum] ?: 0) + INIT_CNT
      dfs(i + 1, r, sum, cnter)
    }
  }

  val m = N / 2
  dfs(0, m, 0, m1)
  dfs(m + 1, N - 1, 0, m2)

  var cnt = 0
  for (e1 in m1) {
    val sum1 = e1.key
    val cnt1 = e1.value
    for (e2 in m2) {
      val sum2 = e2.key
      val cnt2 = e2.value
      if (sum1 + sum2 == S) cnt += cnt1 * cnt2
    }
  }

  w(cnt)
  O.flush()
}
