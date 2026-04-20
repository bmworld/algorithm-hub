package 백준.Gold.no3671

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 11
const val OBS = 1 shl 8
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
val WB = ByteArray(WS + 1).also { it[WS] = 10 }
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
  O.write(WB, ++pos, WS - pos + 1)
}


const val MAX = 9_999_999
const val MAX_LEN = 7
fun main() {
  val primes = BooleanArray(MAX + 1) { it > 1 }.also {
    var d = 3
    while (d <= MAX / d) {
      for (i in d * d..MAX step d * 2) it[i] = false
      d += 2
    }
  }

  fun isPrime(v: Int): Boolean = when {
    v <= 1 -> false
    v <= 3 -> true
    v % 2 == 0 -> false
    else -> primes[v]
  }

  val ans = HashSet<Int>()
  val a = IntArray(MAX_LEN)
  val used = BooleanArray(MAX_LEN)
  fun dfs(dep: Int, len: Int, v: Int) {
    for (i in 0 until len) if (!used[i]) {
      val nv = v * 10 + a[i]
      if (nv == 0) continue

      used[i] = true
      if (isPrime(nv)) ans.add(nv)

      dfs(dep + 1, len, nv)
      used[i] = false
    }
  }

  repeat(i()) {
    ans.clear()
    var len = 0
    var b: Byte
    while (r().also { b = it } in NUM) a[len++] = b - 48
    dfs(0, len, 0)
    w(ans.size)
  }

  O.flush()
}

//      println("[$dep] [$i] $v -> $nv")
