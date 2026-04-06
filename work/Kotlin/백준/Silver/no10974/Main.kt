package 백준.Silver.no10974

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 2
const val OBS = 1 shl 10
val O = BufferedOutputStream(System.out, OBS)
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

  val ch = BooleanArray(N)
  val arr = IntArray(N)

  fun dfs(dep: Int) {
    if (dep == N) {
      repeat(N) { w(arr[it], it + 1 == N) }
      return
    }

    for (i in 0 until N) {
      if (ch[i]) continue
      ch[i] = true
      arr[dep] = i + 1
      dfs(dep + 1)
      ch[i] = false
    }
  }

  dfs(0)
  O.flush()
}
