package 프로그래머스.Lv1.콜라츠추측

import util.validate

class Solution {

  val LIMIT = 500
  fun solution(num: Int): Int {
    var cnt = 0
    var x = num.toLong()
    while (x > 1) {
      x = if (x % 2 == 0L) x / 2 else x * 3 + 1
      if (++cnt > LIMIT) return -1
    }
    return cnt
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.4MB)
 * 테스트 2 〉	통과 (0.01ms, 57.8MB)
 * 테스트 3 〉	통과 (0.01ms, 59.1MB)
 * 테스트 4 〉	통과 (0.01ms, 58.5MB)
 * 테스트 5 〉	통과 (0.03ms, 60.2MB)
 * 테스트 6 〉	통과 (0.01ms, 59.2MB)
 * 테스트 7 〉	통과 (0.03ms, 58.1MB)
 * 테스트 8 〉	통과 (0.01ms, 59.2MB)
 * 테스트 9 〉	통과 (0.01ms, 58.6MB)
 * 테스트 10 〉	통과 (0.02ms, 57.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(num: Int): Int = collatzAlgorithm(num.toLong(),0)
 *
 *     tailrec fun collatzAlgorithm(n:Long, c:Int):Int =
 *         when{
 *             c > 500 -> -1
 *             n == 1L -> c
 *             else -> collatzAlgorithm(if( n%2 == 0L ) n/2 else (n*3)+1, c+1)
 *         }
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.9MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 59.1MB)
 * 테스트 4 〉	통과 (0.01ms, 59MB)
 * 테스트 5 〉	통과 (0.03ms, 58.5MB)
 * 테스트 6 〉	통과 (0.01ms, 58.4MB)
 * 테스트 7 〉	통과 (0.03ms, 58.2MB)
 * 테스트 8 〉	통과 (0.01ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(6), 8)
  validate(s.solution(16), 4)
  validate(s.solution(100), 25)
  validate(s.solution(19024840), 500)
  validate(s.solution(626331), -1)
}
//      println("[$num] cnt=$cnt -> x = $x")
