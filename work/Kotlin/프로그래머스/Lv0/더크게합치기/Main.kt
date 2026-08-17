package 프로그래머스.Lv0.더크게합치기

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 8
  }

  fun solution(a: Int, b: Int): Int {
    val buf = IntArray(MAX_LEN)
    var len = 0
    var x = a
    var aLen = 0
    while (x > 0) {
      buf[len++] = x % 10
      x /= 10
      aLen++
    }

    var y = b
    while (y > 0) {
      buf[len++] = y % 10
      y /= 10
    }

    var ba = 0
    for (i in len - 1 downTo 0) ba = ba * 10 + buf[i]

    var ab = 0
    repeat(aLen) { j ->
      ab = ab * 10 + buf[aLen - (j + 1)]
    }
    for (j in len - 1 downTo aLen) ab = ab * 10 + buf[j]

    return maxOf(ba, ab)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 60.4MB)
 * 테스트 2 〉	통과 (0.02ms, 60.6MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	통과 (0.01ms, 59.1MB)
 * 테스트 5 〉	통과 (0.02ms, 58.2MB)
 * 테스트 6 〉	통과 (0.01ms, 60.6MB)
 * 테스트 7 〉	통과 (0.01ms, 60.8MB)
 * 테스트 8 〉	통과 (0.01ms, 61.6MB)
 * 테스트 9 〉	통과 (0.01ms, 59.9MB)
 * 테스트 10 〉	통과 (0.01ms, 60.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.*
 *
 * class Solution {
 *     fun solution(a: Int, b: Int): Int {
 *         return max("$a$b".toInt(), "$b$a".toInt())
 *     }
 * }
 * 테스트 1 〉	통과 (2.84ms, 60.4MB)
 * 테스트 2 〉	통과 (2.49ms, 59.2MB)
 * 테스트 3 〉	통과 (2.30ms, 60.5MB)
 * 테스트 4 〉	통과 (2.42ms, 60.4MB)
 * 테스트 5 〉	통과 (2.23ms, 59.9MB)
 * 테스트 6 〉	통과 (2.82ms, 60.4MB)
 * 테스트 7 〉	통과 (2.57ms, 60.4MB)
 * 테스트 8 〉	통과 (2.98ms, 61.8MB)
 * 테스트 9 〉	통과 (2.35ms, 61.1MB)
 * 테스트 10 〉	통과 (2.83ms, 60.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1234, 5678), 56781234)
  validate(s.solution(9, 91), 991)
  validate(s.solution(12, 3), 312)
}
