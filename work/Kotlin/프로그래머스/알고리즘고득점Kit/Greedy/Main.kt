package 프로그래머스.알고리즘고득점Kit.Greedy

import util.validate

class Solution {

  val INF = Int.MAX_VALUE
  fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
    var ans = n - lost.size

    val hasLent = BooleanArray(n + 1)

    var i = 0
    val rSize = reserve.size

    fun tryBorrow(i: Int): Boolean {
      if (hasLent[i]) return false
      hasLent[i] = true
      ans++
      return true
    }

    l@ for (a in lost) {
      var b = INF
      while (i < rSize && reserve[i].also { b = it } < a) {
        if (b == a - 1 && tryBorrow(i)) continue@l
        i++
      }

      if (b == a + 1) tryBorrow(i)
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	실패 (0.17ms, 58.5MB)
 * 테스트 2 〉	실패 (0.16ms, 59.5MB)
 * 테스트 3 〉	실패 (0.23ms, 59.7MB)
 * 테스트 4 〉	실패 (0.17ms, 59.6MB)
 * 테스트 5 〉	실패 (0.19ms, 60.1MB)
 * 테스트 6 〉	실패 (0.19ms, 58.4MB)
 * 테스트 7 〉	실패 (0.16ms, 59.2MB)
 * 테스트 8 〉	실패 (0.16ms, 59.7MB)
 * 테스트 9 〉	통과 (0.17ms, 59.2MB)
 * 테스트 10 〉	실패 (0.22ms, 58.5MB)
 * 테스트 11 〉	통과 (0.18ms, 59.1MB)
 * 테스트 12 〉	통과 (0.17ms, 59.6MB)
 * 테스트 13 〉	실패 (0.18ms, 58.7MB)
 * 테스트 14 〉	실패 (0.19ms, 58.7MB)
 * 테스트 15 〉	통과 (0.20ms, 59MB)
 * 테스트 16 〉	실패 (0.16ms, 58.9MB)
 * 테스트 17 〉	통과 (0.25ms, 58.7MB)
 * 테스트 18 〉	실패 (0.16ms, 57.5MB)
 * 테스트 19 〉	실패 (0.16ms, 59.4MB)
 * 테스트 20 〉	실패 (0.22ms, 59.6MB)
 * 테스트 21 〉	통과 (0.16ms, 59.1MB)
 * 테스트 22 〉	통과 (0.18ms, 59.2MB)
 * 테스트 23 〉	통과 (0.17ms, 58.8MB)
 * 테스트 24 〉	실패 (0.17ms, 59.1MB)
 * 테스트 25 〉	통과 (0.21ms, 58.7MB)
 * 테스트 26 〉	통과 (0.20ms, 58.5MB)
 * 테스트 27 〉	통과 (0.16ms, 58.9MB)
 * 테스트 28 〉	통과 (0.17ms, 58.8MB)
 * 테스트 29 〉	통과 (0.21ms, 58.5MB)
 * 테스트 30 〉	통과 (0.17ms, 58.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, intArrayOf(2, 4), intArrayOf(1, 3, 5)), 5)
  validate(s.solution(5, intArrayOf(2, 4), intArrayOf(3)), 4)
  validate(s.solution(3, intArrayOf(3), intArrayOf(1)), 2)
  validate(s.solution(3, intArrayOf(2), intArrayOf(3)), 3)
  validate(s.solution(10, intArrayOf(2, 5), intArrayOf(3, 4)), 10)
  validate(s.solution(10, intArrayOf(2, 5), intArrayOf(3, 7)), 9)
}
