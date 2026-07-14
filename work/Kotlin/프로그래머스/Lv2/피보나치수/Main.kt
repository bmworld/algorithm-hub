package 프로그래머스.Lv2.피보나치수

import util.validate

class Solution {

  val R = 1_234_567
  fun solution(n: Int): Int {
    val fib = IntArray(n + 1).also {
      it[1] = 1
      for (i in 2..n) {
        val sum = it[i - 1] + it[i - 2]
        it[i] = if (sum >= R) sum - R else sum
      }
    }
    return fib[n]
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 61.1MB)
 * 테스트 2 〉	통과 (0.01ms, 60.2MB)
 * 테스트 3 〉	통과 (0.01ms, 60.2MB)
 * 테스트 4 〉	통과 (0.01ms, 58MB)
 * 테스트 5 〉	통과 (0.01ms, 60.9MB)
 * 테스트 6 〉	통과 (0.01ms, 60.9MB)
 * 테스트 7 〉	통과 (0.05ms, 60.3MB)
 * 테스트 8 〉	통과 (0.04ms, 60.1MB)
 * 테스트 9 〉	통과 (0.02ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Int {
 *         var ans = Array(n+1) { i -> 0 }
 *         ans[1] = 1
 *         for(i in 2..n) ans[i] = (ans[i-1] + ans[i-2])%1234567
 *         return ans[n]
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.3MB)
 * 테스트 2 〉	통과 (0.01ms, 60MB)
 * 테스트 3 〉	통과 (0.01ms, 60.9MB)
 * 테스트 4 〉	통과 (0.01ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 58.6MB)
 * 테스트 6 〉	통과 (0.02ms, 58MB)
 * 테스트 7 〉	통과 (0.11ms, 60.3MB)
 * 테스트 8 〉	통과 (0.07ms, 59.5MB)
 * 테스트 9 〉	통과 (0.03ms, 59.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3), 2)
  validate(s.solution(5), 5)
  validate(s.solution(100_000), 1_168_141)
}

//        if (it[i] >= R) println("it[i] = ${it[i]}")
