package 프로그래머스.스택_큐.다리를지나는트럭

import util.validate

class Solution {

  fun solution(L: Int, W: Int, a: IntArray): Int {
    val size = a.size
    val times = IntArray(size)
    val rooms = IntArray(size)

    var t = L
    var w = 0
    var l = 0
    for (r in 0 until size) {
      w += a[r]
      println("[fr] [$l -> $r] v=${a[r]}")

      val room = if (w <= W) 0 else {
        var rmn = L - (t - times[l] + 1)
        println("1 rmn = ${rmn}")
        while (w > W || l < r && t - times[l] + 1 >= L) {
          rmn += 1 + rooms[l]
          w -= a[l++]
        }
        println("2 rmn = ${rmn}, $l, $r")
        rmn - 1
      }

      t += 1 + room.also { if (r > 0) rooms[r - 1] = it }
      times[r] = t

      println("[to] [$l -> $r] room=$room, w=$w, t=$t\n")
    }


    return t
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

//  validate(
//    s.solution(2, 10, intArrayOf(7, 4, 5, 6)), 8
//  )
//
//  validate(
//    s.solution(100, 100, intArrayOf(10)), 101
//  )
//
//  validate(
//    s.solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10)), 110
//  )
//
//  validate(s.solution(4, 3, intArrayOf(2, 1, 1, 1)), 10)
//  validate(s.solution(2, 3, intArrayOf(1, 2, 1, 1)), 6)
//  validate(s.solution(5, 5, intArrayOf(5, 1, 1, 1, 1)), 14)
//  validate(s.solution(3, 6, intArrayOf(1, 2, 3, 1, 1)), 8)
  validate(s.solution(3, 4, intArrayOf(2, 2, 2, 2, 2, 2)), 11)
  validate(s.solution(4, 5, intArrayOf(1, 4, 1, 1, 1)), 11)

}
//      println("[$l ~ $r] rooms[$r]=${rooms[r]} -> t = $t")
//      println("[$l ~ $r] $w vs $W")
