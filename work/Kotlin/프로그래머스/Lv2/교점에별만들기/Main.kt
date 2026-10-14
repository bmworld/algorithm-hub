package 프로그래머스.Lv2.교점에별만들기

import util.validate

class Solution {
  companion object {

    const val PAIR = 2
  }

  fun solution(line: Array<IntArray>): Array<String> {
    val N = line.size
    val MAX_SIZE = N * (N - 1) / PAIR
    val points = LongArray(MAX_SIZE * PAIR)

    var minX = Long.MAX_VALUE
    var maxX = Long.MIN_VALUE

    var maxY = Long.MIN_VALUE
    var minY = Long.MAX_VALUE

    var pairs = 0
    for (i in 0 until N - 1) {
      val l1 = line[i]
      val a1 = l1[0].toLong()
      val b1 = l1[1].toLong()
      val c1 = l1[2].toLong()

      for (j in i + 1 until N) {
        val l2 = line[j]
        val a2 = l2[0].toLong()
        val b2 = l2[1].toLong()
        val c2 = l2[2].toLong()

        val xDnmntr = a1 * b2 - a2 * b1
        if (xDnmntr == 0L) continue
        val xNmrtr = b1 * c2 - b2 * c1
        if (xNmrtr % xDnmntr != 0L) continue

        val x = xNmrtr / xDnmntr

        val yNmrtr = a1 * x + c1
        if (b1 != 0L && yNmrtr % b1 != 0L) continue

        val y = -(yNmrtr / b1)

        points[pairs] = x.also {
          if (it < minX) minX = it
          if (it > maxX) maxX = it
        }
        points[pairs + 1] = y.also {
          if (it < minY) minY = it
          if (it > maxY) maxY = it
        }

        pairs += PAIR
      }
    }

    val R = (maxY - minY + 1).toInt()
    val C = (maxX - minX + 1).toInt()
    val canvas = Array(R) { CharArray(C) { '.' } }
    repeat(pairs / PAIR) {
      val i = it * PAIR
      val x = (points[i] - minX).toInt()
      val y = (-points[i + 1] + maxY).toInt()
      canvas[y][x] = '*'
    }

    return Array(R) { String(canvas[it]) }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.27ms, 60MB)
 * 테스트 2 〉	통과 (3.79ms, 62.3MB)
 * 테스트 3 〉	실패 (런타임 에러)
 * 테스트 4 〉	통과 (6.32ms, 64.8MB)
 * 테스트 5 〉	실패 (런타임 에러)
 * 테스트 6 〉	통과 (1.27ms, 60.9MB)
 * 테스트 7 〉	통과 (3.25ms, 61.9MB)
 * 테스트 8 〉	통과 (0.23ms, 59.8MB)
 * 테스트 9 〉	통과 (8.16ms, 64.7MB)
 * 테스트 10 〉	통과 (8.32ms, 65.9MB)
 * 테스트 11 〉	통과 (8.67ms, 67.3MB)
 * 테스트 12 〉	통과 (10.93ms, 67.5MB)
 * 테스트 13 〉	통과 (10.05ms, 69MB)
 * 테스트 14 〉	통과 (9.63ms, 67.7MB)
 * 테스트 15 〉	통과 (9.27ms, 67.7MB)
 * 테스트 16 〉	통과 (8.68ms, 66.1MB)
 * 테스트 17 〉	통과 (11.78ms, 68.2MB)
 * 테스트 18 〉	통과 (10.56ms, 67.7MB)
 * 테스트 19 〉	통과 (8.31ms, 67.2MB)
 * 테스트 20 〉	통과 (10.37ms, 64.1MB)
 * 테스트 21 〉	통과 (10.10ms, 66.9MB)
 * 테스트 22 〉	통과 (0.16ms, 61.4MB)
 * 테스트 23 〉	통과 (0.17ms, 60.7MB)
 * 테스트 24 〉	통과 (0.18ms, 59.7MB)
 * 테스트 25 〉	통과 (0.18ms, 59.7MB)
 * 테스트 26 〉	통과 (0.25ms, 59.2MB)
 * 테스트 27 〉	실패 (런타임 에러)
 * 테스트 28 〉	실패 (런타임 에러)
 * 테스트 29 〉	통과 (0.17ms, 58.9MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(2, -1, 4),
      intArrayOf(-2, -1, 4),
      intArrayOf(0, -1, 1),
      intArrayOf(5, -8, -12),
      intArrayOf(5, 8, 12),
    )),
    arrayOf(
      "....*....",
      ".........",
      ".........",
      "*.......*",
      ".........",
      ".........",
      ".........",
      ".........",
      "*.......*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(0, 1, -1),
      intArrayOf(1, 0, -1),
      intArrayOf(1, 0, 1),
    )),
    arrayOf(
      "*.*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(1, -1, 0),
      intArrayOf(2, -1, 0),
    )),
    arrayOf(
      "*"
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(1, -1, 0),
      intArrayOf(2, -1, 0),
      intArrayOf(4, -1, 0),
    )),
    arrayOf(
      "*"
    )
  )
}

// 가장 바깥쪽(경계) 좌표(LONG) -200000<=x<=200000, -19_999_900_000 <=y<= 19999900000
// 100000x + y + 100000
// 99999x + y - 100000

// println(
//          "[$i <-> $j] x=$x ($xNmrtr / $xDnmntr),  y=$y ($yNmrtr / $b1)")
//println("intersections = ${points.contentToString()}, rangeX = $minX ~ $maxX, rangeY = $minY ~ $maxY")
