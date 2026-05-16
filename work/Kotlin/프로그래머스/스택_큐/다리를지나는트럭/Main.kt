package 프로그래머스.스택_큐.다리를지나는트럭

import util.validate

class Solution {

  fun solution(L: Int, W: Int, a: IntArray): Int {
    val len = a.size
    val rooms = IntArray(len)

    var time = 0
    var w = 0

    var l = 0
    for (r in 0 until len) {
      w += a[r]
      while (w > W) w -= a[l++]

      val room = when {
        r == 0 -> L
        l == r -> L - 1
        else -> maxOf(0, L - (r - l + 1) - rooms[l])
      }
      rooms[r] = room.also { time += it + 1 }
    }
    return time
  }
}

/**
 * ```
 * ME:
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()

  validate(
    s.solution(2, 10, intArrayOf(7, 4, 5, 6)), 8
  )

  validate(
    s.solution(100, 100, intArrayOf(10)), 101
  )

  validate(
    s.solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)), 110
  )

  validate(
    s.solution(10, 10, intArrayOf(3, 1, 4, 1, 5, 6)), 32
  )

}
//      println("[$l ~ $r] rooms[$r]=${rooms[r]} -> t = $t")
