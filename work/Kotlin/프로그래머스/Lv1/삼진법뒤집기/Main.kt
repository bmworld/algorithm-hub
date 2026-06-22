package 프로그래머스.Lv1.삼진법뒤집기

import util.validate

class Solution {

  val MAX_DIGITS = 17
  val base = 3
  fun solution(n: Int): Int {
    var ans = 0
    val a = IntArray(MAX_DIGITS)
    var x = n
    var len = 0
    while (x > 0) {
      println("[${len}] x = $x -> ${x % base}")
      a[len++] = x % base
      x /= base
    }

    var pow = 1
    repeat(len) {
      ans += a[len - (it + 1)] * pow
      pow *= base
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (4.19ms, 59.8MB)
 * 테스트 2 〉	통과 (3.96ms, 60.1MB)
 * 테스트 3 〉	통과 (3.69ms, 59.1MB)
 * 테스트 4 〉	통과 (4.07ms, 58.6MB)
 * 테스트 5 〉	통과 (3.77ms, 59.9MB)
 * 테스트 6 〉	통과 (3.68ms, 58.1MB)
 * 테스트 7 〉	통과 (3.94ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Int {
 *         return n.toString(3).reversed().toInt(3)
 *     }
 * }
 * 테스트 1 〉	통과 (5.29ms, 60.1MB)
 * 테스트 2 〉	통과 (5.18ms, 60.3MB)
 * 테스트 3 〉	통과 (5.69ms, 60.8MB)
 * 테스트 4 〉	통과 (5.40ms, 60.7MB)
 * 테스트 5 〉	통과 (6.10ms, 59.7MB)
 * 테스트 6 〉	통과 (6.19ms, 60.8MB)
 * 테스트 7 〉	통과 (5.16ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(45), 7)
  validate(s.solution(125), 229)
  validate(s.solution(100_000_000), 56_480_240)
}
