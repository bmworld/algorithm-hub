package 백준.Gold.no10830

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import kotlin.math.sqrt

const val IBS = 1 shl 10
const val OBS = 1 shl 10
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
fun i(): Long {
  var v = 0L
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
  end: Boolean
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

const val CAP = 1_000
fun main() {
  val N = i().toInt()
  var B = i() + 1
  var a = IntArray(N * N) { i().toInt() }
  fun pos(r: Int, c: Int): Int = r * N + c

  fun mutlr(a: IntArray, b: IntArray): IntArray {
    var mutiplied = IntArray(N * N)
    repeat(N) { r ->
      repeat(N) { c ->
        var sum = 0
        repeat(N) { i -> sum += (a[pos(r, i)] * b[pos(i, c)]) % CAP }
        mutiplied[pos(r, c)] = sum % CAP
      }
    }
    return mutiplied
  }

  fun pow(a: IntArray, exp: Long): IntArray {
    return if (exp <= 1L) a
    else if (exp % 2 == 0L) {
      pow(mutlr(a, a), exp / 2)
    } else {
      mutlr(a, pow(a, exp - 1))
    }
  }

  val ans = pow(a, B)
  repeat(N) { r ->
    repeat(N) { c ->
      w(ans[pos(r, c)], c + 1 == N)
    }
  }
  O.flush()
}

// ======================================================================
fun printArr(arr: IntArray, uniqueKey: Long) {
  val N = sqrt(arr.size.toDouble()).toInt()
  println("--------- [$uniqueKey]")
  repeat(N) { r ->
    repeat(N) { c ->
      println("a[pos($r, $c)] = ${arr[r * N + c]}")
    }
  }
}
