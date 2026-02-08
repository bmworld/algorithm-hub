package 백준.Gold.no5904

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 8
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
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

const val m = 109
const val o = 111
const val MAX_DEP = 27

fun main() {
  val N = i()
  IntArray(MAX_DEP + 1).also {

    fun search(dep: Int, N: Int) {
      if (dep == 0) {
        O.write(if (N == 1) m else o)
        return
      }

      val head = it[dep - 1]
      val mid = dep + 3
      when {
        N <= head -> search(dep - 1, N)
        N <= head + mid -> search(0, N - head)
        else -> search(dep - 1, N - (head + mid))
      }
    }

    it[0] = 3
    for (k in 1..MAX_DEP) {
      val cnt = 2 * it[k - 1] + (k + 3)
      it[k] = cnt
      if (cnt >= N) {
        search(k, N)
        break
      }
    }
  }

  O.flush()
}
