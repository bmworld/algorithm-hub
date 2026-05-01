package 프로그래머스.카카오2025하반기1차.노란불신호등

const val POS_G = 0
const val POS_Y = 1
const val POS_R = 2

class Solution {

  fun solution(arr: Array<IntArray>): Int {

    var minI = 0
    var minG = arr[minI][POS_G]
    var minY = arr[minI][POS_Y]
    var minR = arr[minI][POS_R]
    var minC = minG + minY + minR
    var lcm = minC

    val signalCnt = arr.size
    for (i in 1 until signalCnt) {
      val sig = arr[i]
      val G = sig[POS_G]
      val Y = sig[POS_Y]
      val R = sig[POS_R]
      val C = R + Y + G
      if (Y < minY && C < minC) {
        minI = i
        minG = G
        minY = Y
        minR = R
        minC = C
      }
      lcm = getLCM(lcm, C)
    }

    var time = minG + 1
    while (time <= lcm) {
      for (t in time until time + minY) {
        var yCnt = 1
        repeat(signalCnt) { i ->
          if (i == minI) return@repeat
          val sig = arr[i]
          val G = sig[POS_G]
          val Y = sig[POS_Y]
          val R = sig[POS_R]
          val C = R + Y + G
          val pos = (t - 1) % C

          if (pos in G until G + Y) yCnt++
        }
        if (yCnt == signalCnt) return t
      }

      time += minC
    }

    return -1
  }
}

fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
fun getLCM(a: Int, b: Int): Int = a * b / getGCD(a, b)


fun main() {
  val s = Solution()

  check(s.solution(arrayOf(intArrayOf(10, 1, 1), intArrayOf(2, 1, 2))) == 23)
  check(s.solution(arrayOf(intArrayOf(2, 1, 2), intArrayOf(3, 1, 3))) == 18)
  check(s.solution(arrayOf(intArrayOf(2, 1, 2), intArrayOf(5, 1, 1))) == 13)
  check(s.solution(arrayOf(intArrayOf(2, 3, 2), intArrayOf(3, 1, 3), intArrayOf(2, 1, 1))) == 11)
  check(s.solution(arrayOf(intArrayOf(3, 3, 3), intArrayOf(5, 4, 2), intArrayOf(2, 1, 2))) == 193)
  check(s.solution(arrayOf(intArrayOf(1, 1, 4), intArrayOf(2, 1, 3), intArrayOf(3, 1, 2),
    intArrayOf(4, 1, 1))) == -1)

  check(s.solution(arrayOf(intArrayOf(3, 1, 3), intArrayOf(4, 1, 4)))
    .also { println("it1 = ${it}") } == 32)
}

//    println("---- [minI=${minI}] $minG + $minY + $minR = $minC //// maxTime = $lcm")
// println("----- [$time] $t ---> [$i] $pos")
