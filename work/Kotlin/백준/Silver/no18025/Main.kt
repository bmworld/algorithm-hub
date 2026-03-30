package 백준.Silver.no18025

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 1 shl 16
val I = BufferedInputStream(System.`in`)
val O = BufferedOutputStream(System.`out`, OBS)
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
  val a = Array<Pair<String, Int>>(N) { Pair("", 0) }

  repeat(N) {
    val n1 = n()
    val k1 = i()
    val e1 = i()
    val m1 = i()
    val cur = Pair(n1, k1 * SEP1 + e1 * SEP2 + m1)

    var i = it
    for (j in it - 1 downTo 0) {
      val (n2, e) = a[j]
      val k2 = e / SEP1
      if (k1 < k2) break
      else if (k1 == k2) {
        val em = e % SEP1
        val e2 = em / SEP2
        if (e1 > e2) break
        else if (e1 == e2) {
          val m2 = em % SEP2
          if (m1 < m2) break
          else if (m1 == m2 && n1 >= n2) break
        }
      }
      a[j + 1] = a[j]
      i = j
    }

    a[i] = cur
  }

  repeat(N) {
    O.write(a[it].first.toByteArray())
    O.write(10)
  }

  O.flush()
}

/**
# IN
12
Junkyu 50 60 100
Sangkeun 80 60 50
Sunyoung 80 70 100
Soong 50 60 90
Haebin 50 60 100
Kangsoo 60 80 100
Donghyuk 80 60 100
Sei 70 70 70
Wonseob 70 70 90
Sanghyun 70 70 80
nsj 80 80 80
Taewhan 50 60 90

# OUT
Donghyuk
Sangkeun
Sunyoung
nsj
Wonseob
Sanghyun
Sei
Kangsoo
Haebin
Junkyu
Soong
Taewhan

# IN
5
C 22 100 100
B 22 22 1
A 100 1 1
D 22 100 33
d 22 100 33
# OUT
A
B
C
D
d
 */
