package 프로그래머스.Lv2.두큐합같게만들기

import util.validate

class Solution {

  fun solution(q1: IntArray, q2: IntArray): Int {
    val N = q1.size

    var s1 = 0L
    for (x in q1) s1 += x
    var s2 = 0L
    for (x in q2) s2 += x

    var i1 = 0
    var i2 = 0

    while (i1 < N && i2 < N) {

      when {
        s1 > s2 -> {
          val x = q1[i1++]
          s1 -= x
          s2 += x
        }
        s1 < s2 -> {
          val x = q2[i2++]
          s1 += x
          s2 -= x
        }
        else -> break
      }
    }

    var moved = i1 + i2
    return if (moved < 2 * (N - 1)) moved else -1
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1)),
    2
  )

  validate(
    s.solution(intArrayOf(1, 2, 1, 2), intArrayOf(1, 10, 1, 2)),
    7
  )

  validate(
    s.solution(intArrayOf(1, 1), intArrayOf(1, 5)),
    -1
  )
}
