package 백준.Silver.no18025

import java.io.BufferedInputStream

const val IBS = 1 shl 18
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

const val A: Byte = 65
val nameReader = StringBuilder(10)
fun n(): String {
  var b: Byte
  while (r().also { b = it } >= A) {
    nameReader.append(b.toInt().toChar())
  }

  val name = nameReader.toString()
  nameReader.setLength(0)
  return name
}

const val SEP2 = 1_000
const val SEP1 = 1_000 * SEP2

fun main() {
  val N = i()
  val scores = IntArray(N)
  val names = Array<String>(N) { "" }

  var totalNameLen = 0
  repeat(N) {
    val n = n()
    val k = i()
    val e = i()
    val m = i()
    names[it] = n.also { totalNameLen += it.length + 1 }
    scores[it] = k * SEP1 + e * SEP2 + m
  }

  fun comp(kor: Int, eng: Int, math: Int, name: String, i: Int): Boolean {
    val kem = scores[i]
    val k = kem / SEP1
    if (kor < k) return true
    else if (kor == k) {
      val em = kem % SEP1
      val e = em / SEP2
      if (eng > e) return true
      else if (eng == e) {
        val m = em % SEP2
        if (math < m) return true
        else if (math == m && name >= names[i]) return true
      }
    }

    return false
  }


  fun swap(i: Int, j: Int) {
    val v = scores[i]
    scores[i] = scores[j]
    scores[j] = v

    val n = names[i]
    names[i] = names[j]
    names[j] = n
  }

  fun sort(l: Int, r: Int): Int {
    val m = (l + r) shr 1
    val n = names[m]
    val kem = scores[m]
    val kor = kem / SEP1
    val em = kem % SEP1
    val eng = em / SEP2
    val math = em % SEP2

    swap(m, r)

    var pos = l
    for (i in l until r)
      if (comp(kor, eng, math, n, i)) swap(pos++, i)

    if (!comp(kor, eng, math, n, pos)) swap(pos, r)
    return pos
  }


  fun qs(l: Int, r: Int) {
    if (l >= r) return
    val m = sort(l, r)
    qs(l, m - 1)
    qs(m + 1, r)
  }

  qs(0, N - 1)

  val out = StringBuilder(totalNameLen)
  repeat(N) {
    out.appendLine(names[it])
  }
  print(out)
}
