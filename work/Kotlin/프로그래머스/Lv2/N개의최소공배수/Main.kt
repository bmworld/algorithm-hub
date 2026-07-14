package 프로그래머스.Lv2.N개의최소공배수

import util.validate

class Solution {

  fun solution(arr: IntArray): Int {
    var ans = arr[0]
    for (i in 1 until arr.size) {
      val b = arr[i]
      ans = ans / getGCD(ans, b) * b
    }
    return ans.toInt()
  }

  fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 60.3MB)
 * 테스트 2 〉	통과 (0.01ms, 59.7MB)
 * 테스트 3 〉	통과 (0.01ms, 59.4MB)
 * 테스트 4 〉	통과 (0.01ms, 59.9MB)
 * 테스트 5 〉	통과 (0.01ms, 59.8MB)
 * 테스트 6 〉	통과 (0.01ms, 59.8MB)
 * 테스트 7 〉	통과 (0.01ms, 60.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr: IntArray): Int {
 *         return arr.fold(1) { acc, n -> acc * n / gcd(acc, n) }
 *     }
 *
 *     private fun gcd(a: Int, b: Int): Int = if (b  == 0) a else gcd(b, a % b)
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.4MB)
 * 테스트 2 〉	통과 (0.02ms, 57.9MB)
 * 테스트 3 〉	통과 (0.02ms, 61MB)
 * 테스트 4 〉	통과 (0.01ms, 60.8MB)
 * 테스트 5 〉	통과 (0.02ms, 60.8MB)
 * 테스트 6 〉	통과 (0.02ms, 60.3MB)
 * 테스트 7 〉	통과 (0.01ms, 57.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(3)), 3)
  validate(s.solution(intArrayOf(
    31, 37, 41, 43, 47,
    53, 59, 61, 67, 71,
    73, 79, 83, 89, 97)), 571_899_163)
  validate(s.solution(intArrayOf(2, 6, 8, 14)), 168)
  validate(s.solution(intArrayOf(1, 2, 3)), 6)
}

//  //  fun getGCD(a: Long, b: Long): Long = if (b == 0L) a else getGCD(b, a % b)
