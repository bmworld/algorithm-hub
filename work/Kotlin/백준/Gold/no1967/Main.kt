package 백준.Gold.no1967

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 17
const val OBS = 1 shl 3
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
val WB = ByteArray(WS)
fun w(
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

const val SEP = 1_000
fun main() {
  val N = i()
  val root = 1
  val tree = Array(N + 1) { mutableListOf<Int>() }

  repeat(N - 1) {
    val p = i()
    val c = i()
    val w = i()
    tree[p] += c * SEP + w
  }

  var dimaeter = 0
  fun dfs(p: Int): Int {

    val cs = tree[p]
    val cnt = cs.size
    if (cnt == 0) return 0

    var top1 = 0
    var top2 = 0
    repeat(cnt) {
      val e = cs[it]
      val c = e / SEP
      val ccw = dfs(c)
      val w = e % SEP + ccw
      if (w > top1) {
        top2 = top1
        top1 = w
      } else if (w > top2) top2 = w
    }
    dimaeter = maxOf(dimaeter, top1 + top2)
    return top1
  }

  dfs(root)

  w(dimaeter)
  O.flush()
}

//  println("tops = $top1, $top2")
//  println("--- [$p] > $c ($w) > ${tree[c].size} ($ccw)")

/**
IN
4
1 2 17
1 3 53
1 4 44
OUT
97

IN
6
1 2 4
1 3 8
1 5 4
2 6 12
3 4 1
OUT
25

IN
6
1 2 59
1 3 65
2 4 46
2 5 67
2 6 20
OUT 191
 */
