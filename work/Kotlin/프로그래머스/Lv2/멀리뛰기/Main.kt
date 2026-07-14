package 프로그래머스.Lv2.멀리뛰기

import util.validate

class Solution {

  val R = 1_234_567
  fun solution(n: Int): Int {
    if (n <= 2) return n

    val fib = IntArray(n + 1).also {
      it[1] = 1
      it[2] = 2
      for (i in 3..n) {
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
 * 테스트 1 〉	통과 (0.01ms, 58.2MB)
 * 테스트 2 〉	통과 (0.01ms, 60.8MB)
 * 테스트 3 〉	통과 (0.01ms, 59.1MB)
 * 테스트 4 〉	통과 (0.01ms, 60.2MB)
 * 테스트 5 〉	통과 (0.01ms, 59.9MB)
 * 테스트 6 〉	통과 (0.01ms, 60.3MB)
 * 테스트 7 〉	통과 (0.02ms, 59.7MB)
 * ```
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Long = getFibonacci(n + 1)
 *     private tailrec fun getFibonacci(currentNumber : Int, acc : Long = 0, prevSum : Long = 1) : Long =
 *         if(currentNumber == 0) acc
 *         else getFibonacci(currentNumber - 1, prevSum, (prevSum + acc) % 1234567)
 * }
 * 테스트 1 〉	통과 (0.02ms, 60MB)
 * 테스트 2 〉	통과 (0.03ms, 60.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.2MB)
 * 테스트 4 〉	통과 (0.02ms, 60.5MB)
 * 테스트 5 〉	통과 (0.02ms, 59MB)
 * 테스트 6 〉	통과 (0.02ms, 61.4MB)
 * 테스트 7 〉	통과 (0.03ms, 60.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1), 1)
  validate(s.solution(2), 2)
  validate(s.solution(3), 3)
  validate(s.solution(4), 5)
  validate(s.solution(2000), 694725)
}
