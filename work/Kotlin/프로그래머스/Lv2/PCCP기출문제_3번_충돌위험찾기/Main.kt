package 프로그래머스.Lv2.PCCP기출문제_3번_충돌위험찾기

import util.validate

class Solution {
  companion object {

    const val SIZE = 100
    const val EMPTY = -1
    const val DANGER = 1
  }

  fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {

    var R = 0
    var C = 0
    for (p in points) {
      val r = --p[0]
      val c = --p[1]
      if (r + 1 > R) R = r + 1
      if (c + 1 > C) C = c + 1
    }

    val X = routes.size
    val M = routes[0].size
    val CAP = C

    fun pos(r: Int, c: Int): Int = r * CAP + c

    val tracker = Array(X) { robot ->
      IntArray((R - 1) * (C - 1) * (M - 1) + 1) { EMPTY }.also { track ->
        val route = routes[robot]
        var ri = 0
        var toR = 0
        var toC = 0
        points[route[ri++] - 1].also {
          track[0] = pos(it[0], it[1])
        }
        points[route[ri] - 1].also {
          toR = it[0]
          toC = it[1]
        }

        for (t in 1 until track.size) {
          val prv = track[t - 1]
          var r = prv / CAP
          var c = prv % CAP
          val dr = r - toR
          val dc = c - toC

          when {
            abs(dr) > 0 -> if (dr >= 0) r-- else r++
            else -> if (dc >= 0) c-- else c++
          }

          track[t] = pos(r, c)

          if (r == toR && c == toC) {
            if (++ri == M) break
            points[route[ri] - 1].also {
              toR = it[0]
              toC = it[1]
            }
          }
        }
      }
    }

    var ans = 0
    var t = 0
    var rmn = X
    val center = IntArray(R * C)

    while (rmn > 1) {
      val ch = center.clone()
      repeat(X) { robot ->
        val track = tracker[robot]
        val pos = track[t]
        when {
          pos == EMPTY -> if (track[t - 1] != EMPTY) rmn--
          else -> if (ch[pos]++ == DANGER) ans++
        }
      }
      t++
    }

    return ans
  }

  fun abs(x: Int) = if (x >= 0) x else -x
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.31ms, 60.6MB)
 * 테스트 2 〉	통과 (0.33ms, 60.5MB)
 * 테스트 3 〉	통과 (0.23ms, 58.7MB)
 * 테스트 4 〉	통과 (0.45ms, 60.5MB)
 * 테스트 5 〉	통과 (0.60ms, 60.7MB)
 * 테스트 6 〉	통과 (2.07ms, 61.1MB)
 * 테스트 7 〉	통과 (6.42ms, 70.9MB)
 * 테스트 8 〉	통과 (27.60ms, 123MB)
 * 테스트 9 〉	통과 (108.57ms, 245MB)
 * 테스트 10 〉	통과 (27.28ms, 124MB)
 * 테스트 11 〉	통과 (50.29ms, 126MB)
 * 테스트 12 〉	통과 (48.71ms, 126MB)
 * 테스트 13 〉	통과 (108.28ms, 246MB)
 * 테스트 14 〉	통과 (264.00ms, 557MB)
 * 테스트 15 〉	통과 (256.10ms, 557MB)
 * 테스트 16 〉	통과 (272.80ms, 511MB)
 * 테스트 17 〉	통과 (273.18ms, 589MB)
 * 테스트 18 〉	통과 (285.78ms, 698MB)
 * 테스트 19 〉	통과 (225.11ms, 525MB)
 * 테스트 20 〉	통과 (245.31ms, 551MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
 *                 val list = arrayListOf<MutableMap<String, Int>>()
 *
 *         routes.map {
 *             route(points, it)
 *         }.forEach {
 *
 *             it.forEachIndexed { index, xy ->
 *                 if (list.size < index + 1) {
 *                     list.add(index, mutableMapOf())
 *                 }
 *
 *                 if (list[index][xy.toString()] != null) {
 *                     list[index][xy.toString()] = 1
 *                 } else {
 *                     list[index][xy.toString()] = 0
 *                 }
 *             }
 *
 *         }
 *
 *         var answer: Int = 0
 *         list.forEach {
 *             it.forEach { map ->
 *                 answer += map.value
 *             }
 *         }
 *
 *         return answer
 *     }
 *
 *
 *     private fun route(points: Array<IntArray>, routes: IntArray): ArrayList<XY> {
 *
 *         val list = arrayListOf<XY>()
 *
 *         val from = points[routes[0] - 1]
 *         var x = from[0]
 *         var y = from[1]
 *         list.add(XY(x, y))
 *         for (i: Int in 0..routes.size - 2) {
 *
 *             val to = points[routes[i + 1] - 1]
 *             while (x != to[0]) {
 *                 if (x < to[0]) {
 *                     x += 1
 *                 } else {
 *                     x -= 1
 *                 }
 *                 list.add(XY(x, y))
 *             }
 *
 *             while (y != to[1]) {
 *                 if (y < to[1]) {
 *                     y += 1
 *                 } else {
 *                     y -= 1
 *                 }
 *                 list.add(XY(x, y))
 *             }
 *         }
 *         return list
 *     }
 *
 *     data class XY(
 *         val x: Int,
 *         val y: Int
 *     ) {
 *         override fun toString(): String {
 *             return "[$x, $y]"
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (4.36ms, 61.5MB)
 * 테스트 2 〉	통과 (3.59ms, 60.4MB)
 * 테스트 3 〉	통과 (3.91ms, 61MB)
 * 테스트 4 〉	통과 (5.73ms, 59.6MB)
 * 테스트 5 〉	통과 (5.88ms, 59MB)
 * 테스트 6 〉	통과 (6.79ms, 59.7MB)
 * 테스트 7 〉	통과 (11.54ms, 62.7MB)
 * 테스트 8 〉	통과 (47.34ms, 90.2MB)
 * 테스트 9 〉	통과 (46.94ms, 91.6MB)
 * 테스트 10 〉	통과 (33.82ms, 76.9MB)
 * 테스트 11 〉	통과 (40.89ms, 79.8MB)
 * 테스트 12 〉	통과 (53.18ms, 90MB)
 * 테스트 13 〉	통과 (64.98ms, 92.8MB)
 * 테스트 14 〉	통과 (163.47ms, 168MB)
 * 테스트 15 〉	통과 (177.40ms, 169MB)
 * 테스트 16 〉	통과 (198.12ms, 171MB)
 *
 *
 *
 * [RIVAL 2]
 * class Solution {
 * data class Pos(val x: Int, val y: Int, val time: Int)
 *
 *     fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
 *
 *         var answer: Int = 0
 *         val map = HashMap<Pos, Int>()
 *
 *         for (route in routes) {
 *             var time = 0
 *             val startPos = Pos(points[route[0]-1][0], points[route[0]-1][1], time)
 *             map[startPos] = map.getOrDefault(startPos, 0) + 1
 *             for (index in 0..route.size-2) {
 *                 var curX = points[route[index]-1][0]
 *                 var curY = points[route[index]-1][1]
 *                 val endX = points[route[index+1]-1][0]
 *                 val endY = points[route[index+1]-1][1]
 *
 *                 while (curX != endX) {
 *                     time += 1
 *                     if (curX < endX) {
 *                         curX++
 *                     } else {
 *                         curX--
 *                     }
 *                     val pos = Pos(curX, curY, time)
 *                     map[pos] = map.getOrDefault(pos, 0) + 1
 *                 }
 *
 *                 while (curY != endY) {
 *                     time += 1
 *                     if (curY < endY) {
 *                         curY++
 *                     } else {
 *                         curY--
 *                     }
 *                     val pos = Pos(curX, curY, time)
 *                     map[pos] = map.getOrDefault(pos, 0) + 1
 *                 }
 *             }
 *         }
 *
 *
 *         return map.entries.filter { it.value > 1 }.count()
 *     }
 * }
 * 테스트 1 〉	통과 (0.56ms, 60.8MB)
 * 테스트 2 〉	통과 (0.56ms, 60.3MB)
 * 테스트 3 〉	통과 (0.41ms, 59.6MB)
 * 테스트 4 〉	통과 (0.81ms, 58.8MB)
 * 테스트 5 〉	통과 (0.66ms, 60.4MB)
 * 테스트 6 〉	통과 (2.07ms, 60.9MB)
 * 테스트 7 〉	통과 (4.61ms, 59.7MB)
 * 테스트 8 〉	통과 (37.88ms, 70.8MB)
 * 테스트 9 〉	통과 (27.03ms, 70.2MB)
 * 테스트 10 〉	통과 (24.97ms, 66.3MB)
 * 테스트 11 〉	통과 (23.46ms, 67MB)
 * 테스트 12 〉	통과 (41.56ms, 71.3MB)
 * 테스트 13 〉	통과 (25.54ms, 72.5MB)
 * 테스트 14 〉	통과 (213.32ms, 151MB)
 * 테스트 15 〉	통과 (211.76ms, 150MB)
 * 테스트 16 〉	통과 (196.76ms, 152MB)
 * ```
 */
fun main() {
  val s = Solution()

  validate(s.solution(
    arrayOf(
      intArrayOf(3, 2),
      intArrayOf(6, 4),
      intArrayOf(4, 7),
      intArrayOf(1, 4),
    ),
    arrayOf(
      intArrayOf(4, 2),
      intArrayOf(1, 3),
      intArrayOf(2, 4),
    )
  ), 1)

  validate(s.solution(
    arrayOf(
      intArrayOf(3, 2),
      intArrayOf(6, 4),
      intArrayOf(4, 7),
      intArrayOf(1, 4),
    ),
    arrayOf(
      intArrayOf(4, 2),
      intArrayOf(1, 3),
      intArrayOf(4, 2),
      intArrayOf(4, 3),
    )
  ), 9)

  validate(s.solution(
    arrayOf(
      intArrayOf(2, 2),
      intArrayOf(2, 3),
      intArrayOf(2, 7),
      intArrayOf(6, 6),
      intArrayOf(5, 2),
    ),
    arrayOf(
      intArrayOf(2, 3, 4, 5),
      intArrayOf(1, 3, 4, 5),
    )
  ), 0)

}

//          println("[$robot] path[$t] = ${path[t]}($r, $c) -> $toR, $toC")
//      println("[$t] rmn=$rmn, ans=$ans, \n -> ${ch.contentToString()}")
