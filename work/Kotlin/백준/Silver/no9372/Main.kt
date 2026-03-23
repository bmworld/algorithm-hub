package 백준.Silver.no9372

import java.io.BufferedInputStream

const val IBS = 1 shl 10
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


fun main() {
  fun findRoot(v: Int, tree: IntArray): Int {
    val r = tree[v]
    return if (r == v) v else {
      val nr = findRoot(r, tree)
      tree[v] = nr
      nr
    }
  }

  fun merge(a: Int, b: Int, tree: IntArray) {
    val ar = findRoot(a, tree)
    val br = findRoot(b, tree)
    when {
      ar < br -> tree[br] = ar
      ar > br -> tree[ar] = br
    }
  }

  repeat(i()) {
    var cnt = 0
    val N = i()
    val tree = IntArray(N + 1) { it }

    repeat(i()) {
      val a = i()
      val b = i()
      val ra = findRoot(a, tree)
      val rb = findRoot(b, tree)
      if (ra != rb) {
        merge(ra, rb, tree)
        cnt++
      }
    }

    println(cnt)
  }
}
