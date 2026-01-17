package 백준.Silver.no1446

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

private const val IBS = 1 shl 8
private const val OBS = 1 shl 6
private val O = BufferedOutputStream(System.out, OBS)
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

fun main() {
  val N = i()
  val D = i()
  val r = Array<Road?>(N) { null }
  val d = IntArray(D + 1) { it }

  var len = 0
  repeat(N) {
    val fr = i()
    val to = i()
    val dist = i()
    if (to > D || to - fr <= dist) return@repeat
    var i = len
    var override = false
    while (i > 0) {
      val prv = r[i - 1]!!
      val prvFr = prv.fr
      if (fr < prvFr) r[i--] = prv
      else if (fr == prvFr && to == prv.to) {
        if (dist >= prv.dist) return@repeat
        else {
          i--
          override = true
          break
        }
      } else if (fr < prvFr) r[i--] = prv
      else break
    }
    r[i] = Road(fr, to, dist)
    if (!override) len++
  }

  for (i in 0 until len) {
    println("---road = ${r[i]}")
  }

  var ri = 0
  var nextRoad = r[ri]!! // 처음부터,,,,,,,, prv.to next.fr 연결될 수 있는건 한방에 연결해야함.......
  var bestRoad: Road? = null
  repeat(D) { cur ->
    val str = d[cur]
    var min = str
    println("[$cur --> road.fr=${nextRoad.fr}] --- str=$str")
    if (nextRoad.fr == cur) {
      val (fr, to, w) = nextRoad
      val reduced = to - fr - w

      val goShort = if (bestRoad != null) {
        if (bestRoad!!.to <= fr) {
          str - reduced
        } else {
          val bestFr = bestRoad!!.fr
          val diff = fr - bestFr
          println("d[$bestFr] ${d[bestFr]} + $diff - $reduced")
          d[bestFr] + diff - reduced
        }
      } else -reduced

      if (str > goShort) {
        min = goShort
        bestRoad = nextRoad
      }

      println("-------------- dist[$cur] = $str vs $goShort")
      if (ri + 1 < len) nextRoad = r[++ri]!!
    }

    d[cur + 1] = min + 1
  }


  w(d[D])
  O.flush()
}

data class Road(
  val fr: Int,
  val to: Int,
  val dist: Int
)

/**
// TEST
1 100
0 100 99
-> 99

//
1 100
0 100 101
-> 101

// 범위 밖
2 100
101 104 54
0 102 50


//
2 100
1 10 1
1 10 5



 */
