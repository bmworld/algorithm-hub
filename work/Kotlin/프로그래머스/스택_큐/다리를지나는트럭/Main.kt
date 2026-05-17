package 프로그래머스.스택_큐.다리를지나는트럭

import util.validate

class Solution {

  val MAX = 10_000
  fun solution(L: Int, W: Int, a: IntArray): Int {
    val size = a.size
    val wByTime = IntArray(L * W + 1 + 1)

    var ans = L
    var w = 0
    var i = 0
    var l = 0
    var r = l
    var truck = a[i]

    while (true) {
      if (r - l >= L) w -= wByTime[l++]
      else r++
      println("[$l, $r] => w=$w, cur=$i")

      if (w + truck <= W) {
        w += truck.also { wByTime[r] = it }
        println("[+] r=$r, truck = $truck, w=$w next = $i")
        if (++i < size) truck = a[i]
        else {
          ans = r + L
          break
        }
      }

    }


    return ans.also { println("ans = ${it}") }
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
//  validate(s.solution(3, 6, intArrayOf(1, 2, 3, 1, 1)), 8)
//  validate(s.solution(3, 4, intArrayOf(2, 2, 2, 2, 2, 2)), 11)
//  validate(s.solution(4, 5, intArrayOf(1, 4, 1, 1, 1)), 11)
  validate(s.solution(5, 5, intArrayOf(5, 1, 1, 1, 1)), 14)

}
