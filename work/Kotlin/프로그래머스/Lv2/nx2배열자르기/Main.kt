package 프로그래머스.Lv2.nx2배열자르기

import util.validate

class Solution {

  fun solution(n: Int, left: Long, right: Long): IntArray {
    val ans = IntArray((right - left + 1).toInt()) {
      val i = left + it
      val r = i / n
      val c = i % n
      maxOf(r, c).toInt() + 1
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.40ms, 73.5MB)
 * 테스트 2 〉	통과 (2.43ms, 74.5MB)
 * 테스트 3 〉	통과 (2.90ms, 75.3MB)
 * 테스트 4 〉	통과 (0.02ms, 60.6MB)
 * 테스트 5 〉	통과 (0.05ms, 58.5MB)
 * 테스트 6 〉	통과 (2.32ms, 73.6MB)
 * 테스트 7 〉	통과 (2.35ms, 74.2MB)
 * 테스트 8 〉	통과 (2.32ms, 72.8MB)
 * 테스트 9 〉	통과 (2.44ms, 73.1MB)
 * 테스트 10 〉	통과 (2.51ms, 73.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.max
 *
 * class Solution {
 *     fun solution(n: Int, left: Long, right: Long): IntArray {
 *         return (left..right).map { (max(it / n, it % n) + 1).toInt() }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (13.15ms, 75.7MB)
 * 테스트 2 〉	통과 (13.62ms, 78.3MB)
 * 테스트 3 〉	통과 (14.68ms, 79MB)
 * 테스트 4 〉	통과 (5.96ms, 60.3MB)
 * 테스트 5 〉	통과 (5.68ms, 60.9MB)
 * 테스트 6 〉	통과 (14.15ms, 76.3MB)
 * 테스트 7 〉	통과 (14.30ms, 77.1MB)
 * 테스트 8 〉	통과 (13.36ms, 75.9MB)
 * 테스트 9 〉	통과 (21.23ms, 76.6MB)
 * 테스트 10 〉	통과 (13.00ms, 77.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 0, 0), intArrayOf(1))
  validate(s.solution(2, 0, 1), intArrayOf(1, 2))
  validate(s.solution(3, 2, 5), intArrayOf(3, 2, 2, 3))
  validate(s.solution(4, 7, 14), intArrayOf(4, 3, 3, 3, 4, 4, 4, 4))
}

//      println("i = ${i}, $r, $c")
