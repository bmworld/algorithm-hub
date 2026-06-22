package 프로그래머스.Lv1.삼총사

import util.validate

class Solution {

  fun solution(a: IntArray): Int {
    var ans = 0
    for (i in 0 until a.size)
      for (j in i + 1 until a.size)
        for (k in j + 1 until a.size) {
          if (a[i] + a[j] + a[k] == 0) ans++
        }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.01ms, 58.9MB)
 * 테스트 3 〉	통과 (0.02ms, 57.7MB)
 * 테스트 4 〉	통과 (0.02ms, 59.2MB)
 * 테스트 5 〉	통과 (0.02ms, 58MB)
 * 테스트 6 〉	통과 (0.02ms, 59.1MB)
 * 테스트 7 〉	통과 (0.02ms, 58.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(number: IntArray): Int {
 *         var answer: Int = 0
 *
 *         var threeMusketeersCount = 0
 *
 *         for ((i,n) in number.withIndex()) {
 *             for ((j, m) in number.withIndex()) {
 *                 for ((k, l) in number.withIndex()) {
 *                     if ((i < j) && (j < k)) {
 *                         if (n + m + l == 0) {
 *                             threeMusketeersCount++
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return threeMusketeersCount
 *     }
 * }
 * 테스트 1 〉	통과 (0.06ms, 58.4MB)
 * 테스트 2 〉	통과 (0.02ms, 59.9MB)
 * 테스트 3 〉	통과 (0.02ms, 58.1MB)
 * 테스트 4 〉	통과 (0.04ms, 59.3MB)
 * 테스트 5 〉	통과 (0.03ms, 58.7MB)
 * 테스트 6 〉	통과 (0.04ms, 59MB)
 * 테스트 7 〉	통과 (0.07ms, 59.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(-2, 3, 0, 2, -5)), 2)
  validate(s.solution(intArrayOf(-3, -2, -1, 0, 1, 2, 3)), 5)
  validate(s.solution(intArrayOf(-1, 1, 1, -1)), 0)
}
